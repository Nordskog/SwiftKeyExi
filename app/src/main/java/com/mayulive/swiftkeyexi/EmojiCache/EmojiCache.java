package com.mayulive.swiftkeyexi.EmojiCache;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.mayulive.swiftkeyexi.ExiModule;
import com.mayulive.swiftkeyexi.main.emoji.data.DB_EmojiItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


/**
 * Created by Roughy on 12/31/2016.
 */

public class EmojiCache
{

	private static String LOGTAG = ExiModule.getLogTag(EmojiCache.class);

	//Color emoji and bw emoji.
	public static final int EMOJI_TYPE_COUNT = 2;

	//One for each emoji type. This is for single-unit-width items,
	//shared between panels
	private static EmojiCacheMap[] mEmojiTypeCaches = new EmojiCacheMap[EMOJI_TYPE_COUNT];

	//For each panel, ignores styling
	private static Map<Object, EmojiCacheMap> mEmojiPanelCaches = new HashMap<>();

	//Items added in order they are added to the cache so we can ditch older items
	//as we run low on memory. This is a global list for all panels and shared emoji.
	private static LinkedList<EmojiCacheItem> mGlobalCacheList = new LinkedList<>();

	//Keep track of cache memory usage
	private static long mMemoryLimit = ( (Runtime.getRuntime().maxMemory() ) ) / 8;	// 1/8th of available memory
	private static long mMemoryUsage = 0;			//Updated when item is added, estimate.

	//Created on first call
	private static Handler mMainHandler = null;

	//Simpler doing syncing
	private static ArrayList<EmojiRenderInterface> mRenderInputQueue = new ArrayList<>();	//Main thread
	private static ArrayList<EmojiRenderInterface> mRenderQueue = new ArrayList<>();		//Render thread
	private static boolean mRenderInProgress = false;
	private static boolean mCancelRequested = false;

	private static void requestCancelRenderThread()
	{
		if (mRenderInProgress)
		{
			mCancelRequested = true;
		}
	}

	static
	{
		for (int i = 0; i < mEmojiTypeCaches.length; i++)
		{
			mEmojiTypeCaches[i] = new EmojiCacheMap();
		}
	}


	//Murder everything.
	//Remember to destroy any views before doing this,
	//to keep them from holding onto old items.
	public static void clearCache()
	{
		clearQueue();

		//Theme may have changed, clear view pool too
		EmojiCacheRenderer.clearViewPool();

		for (EmojiCacheMap map : mEmojiTypeCaches )
		{
			map.clear();
		}

		for (EmojiCacheMap map : mEmojiPanelCaches.values() )
		{
			map.clear();
		}

		mGlobalCacheList.clear();
		mMemoryUsage = 0;
	}

	//Only clear caches that are mapped with a key.
	//Leave shared cache alone.
	//TODO unify mapped/panel naming
	public static void clearMappedCaches()
	{
		clearQueue();
		for (EmojiCacheMap map : mEmojiPanelCaches.values() )
		{
			map.clear();
		}
	}

	public static void clearCache(Object panelKey)
	{
		clearQueue();
		mEmojiPanelCaches.remove(panelKey);
	}

	public static void clearQueue()
	{
		requestCancelRenderThread();
		synchronized (mRenderInputQueue)
		{
			mRenderInputQueue.clear();
		}
	}

	public static void clearIfWidthTooSmall(Object panelKey, int newWidthUnits)
	{
		EmojiCacheMap map = mEmojiPanelCaches.get(panelKey);

		if (map != null)
		{
			Iterator< Map.Entry<String, EmojiCacheItem>> it = map.entrySet().iterator();

			while (it.hasNext() )
			{
				Map.Entry<String, EmojiCacheItem> entry = it.next();
				if (entry.getValue().needsUpdate(newWidthUnits))
				{
					it.remove();
				}
			}
		}
	}

	private static void addToQueue(EmojiRenderInterface renderObject)
	{
		synchronized (mRenderInputQueue)
		{
			mRenderInputQueue.add(renderObject);
		}
	}

	private static boolean renderInProgress()
	{
		return mRenderInProgress;
	}

	private static void setRenderThreadState(boolean running)
	{
		mRenderInProgress = running;
	}

	public static void processQueue()
	{
		if (!renderInProgress())
			AsyncTask.execute(renderTask);
	}

	public static void singleQueue(Context context, String string, float textSize, Object panelKey, int styleIndex, int itemWidth, boolean singleLine)
	{
		EmojiCacheItem cacheItem = EmojiCache.getCacheItem(string,panelKey,styleIndex);

		if (cacheItem.getStatus() == EmojiCacheItem.CacheItemStatus.UNDEFINED)
		{
			cacheItem.setStatus(EmojiCacheItem.CacheItemStatus.RENDERING);
			EmojiRenderInterface renderObject = new EmojiRenderInterface
					(
							context,
							cacheItem,
							string,
							textSize,
							styleIndex,
							itemWidth,
							singleLine,
							panelKey);

			mRenderInputQueue.add(renderObject);
		}
	}

	//Mass-add a bunch of a render requests. You must manually call processQueue() afterwards
	public static void batchQueue(Context context, ArrayList<DB_EmojiItem> strings, float textSize, Object panelKey, int styleIndex, int itemWidth, boolean singleLine)
	{
		//When to sync ...
		synchronized (mRenderInputQueue)
		{
			for (DB_EmojiItem item : strings)
			{
				//Don't bother caching if it doesn't contain bitmap characters
				if (item.get_type() != DB_EmojiItem.EmojiType.CONTAINS_EMOJI)
					continue;

				EmojiCacheItem cacheItem = EmojiCache.getCacheItem(item.get_text(),panelKey,styleIndex);

				if (cacheItem.getStatus() == EmojiCacheItem.CacheItemStatus.UNDEFINED)
				{
					cacheItem.setStatus(EmojiCacheItem.CacheItemStatus.RENDERING);
					EmojiRenderInterface renderObject = new EmojiRenderInterface
							(
									context,
									cacheItem,
									item.get_text(),
									textSize,
									styleIndex,
									itemWidth,
									singleLine,
									panelKey);

					mRenderInputQueue.add(renderObject);
				}
			}
		}
	}

	private static void updateQueueFromInput()
	{
		synchronized (mRenderInputQueue)
		{
			mRenderQueue.clear();
			mRenderQueue.addAll(mRenderInputQueue);
			mRenderInputQueue.clear();
		}
	}

	private static Runnable renderTask = new Runnable()
	{
		@Override
		public void run()
		{
			setRenderThreadState(true);
			updateQueueFromInput();

			while(mRenderQueue.size() > 0)
			{

				for (EmojiRenderInterface renderObject : mRenderQueue)
				{
					if (mCancelRequested)
					{
						//Active queue is cleared here,
						//requester responsible for clearing input queue.
						mRenderQueue.clear();
						mCancelRequested = false;
						break;
					}
					renderObject.run();
					onRenderComplete(renderObject);

					//Maybe move outside of inner loop?
					//Then we might end up processing a ton of items before checking size though.
					updateAndPruneGlobalCacheList(renderObject.item);
				}

				updateQueueFromInput();
			}
			setRenderThreadState(false);

		}
	};

	private static EmojiCacheMap getPanelMap(Object key)
	{
		EmojiCacheMap map = mEmojiPanelCaches.get(key);
		if (map == null)
		{
			map = new EmojiCacheMap();
			mEmojiPanelCaches.put(key,map);
		}

		return map;

	}


	public static EmojiCacheItem getCacheItem(String key, Object panelKey, int style)
	{
		EmojiCacheItem returnItem = null;

		//Check shared type map
		returnItem = mEmojiTypeCaches[style].get(key);
		if (returnItem == null)
		{
			//Nothing? Check panel map
			EmojiCacheMap map = getPanelMap(panelKey);
			returnItem = map.get(key);
			if (returnItem == null)
			{
				//Nothing, returned an empty item.
				//The caller is responsible for requesting a render.
				returnItem =  new EmojiCacheItem();
				map.put(key,returnItem);
			}
		}

		return returnItem;
	}

	public static void immediateRender(EmojiRenderInterface renderObject, String text, EmojiCacheItem item, Object panelKey)
	{
		renderObject.run();
		onRenderComplete(renderObject);
	}

	//Add to global linked list and note added memory usage.
	//Prune old items if memory usage exceeds limit
	private static void updateAndPruneGlobalCacheList(EmojiCacheItem item)
	{
		mMemoryUsage += item.memorySize;
		mGlobalCacheList.addLast(item);

		freeMemory();

	}

	//Prune old items until below memory limit
	private static void freeMemory()
	{
		while(mMemoryUsage > mMemoryLimit )
		{
			EmojiCacheItem oldest = mGlobalCacheList.pop();

			if (oldest == null)
			{
				Log.e(LOGTAG, "Emptied cache list but still above limit. Usage: "+mMemoryUsage+", limit: "+mMemoryLimit);

				//Somehow didn't reset the memory usage var? Strange, investigate.
				mMemoryUsage = 0;
				break;
			}

			mMemoryUsage -= oldest.memorySize;

			//Mark item as undefined and null its bitmap.
			//When next requested it will be re-rendered.
			oldest.bitmap = null;
			oldest.setStatus(EmojiCacheItem.CacheItemStatus.UNDEFINED);
		}
	}

	public static void requestRender(EmojiRenderInterface renderObject)
	{
		renderObject.item.setStatus(EmojiCacheItem.CacheItemStatus.RENDERING);
		addToQueue(renderObject);
		processQueue();
	}

	//We don't know if we can place the item in the shared type cache map
	//until we have rendered it. Be default they will always be added to the
	//panel map. Upon completion, it may be moved to the shared type map,
	//or we may replace it with an existing item.
	protected static void onRenderComplete (final EmojiRenderInterface renderObject)
	{
		//May be replaced by an existing item, but we call the listeners on the input item
		EmojiCacheItem returnItem = renderObject.item;

		renderObject.item.setStatus(EmojiCacheItem.CacheItemStatus.READY);

		if (renderObject.item.isSingleWidth)
		{
			//Remove from panel
			getPanelMap(renderObject.panelKey).remove(renderObject.text);

			//Check for existing
			EmojiCacheItem existingItem = mEmojiTypeCaches[renderObject.item.getStyle()].get(renderObject.text);
			if (existingItem == null)
			{
				returnItem = renderObject.item;
				mEmojiTypeCaches[renderObject.styleId].put(renderObject.text,renderObject.item);
			}
			else
			{
				//Ditch the item we just rendered
				returnItem = existingItem;
			}
		}
			//Else don't need to do anything

		final EmojiCacheItem finalItem = returnItem;

		//synchronized (finalItem.status)
		{
			if (renderObject.item.mRenderListeners.size() > 0)
			{
				//Call listeners on main thread
				Runnable mainThreadListenerCaller = new Runnable()
				{

					@Override
					public void run()
					{
						renderObject.item.onRenderComplete(finalItem);
					}
				};

				if (mMainHandler == null)
				{
					mMainHandler = new Handler(renderObject.context.getMainLooper());
				}

				mMainHandler.post(mainThreadListenerCaller);
			}
		}
	}


	protected static class EmojiCacheMap extends HashMap<String, EmojiCacheItem>
	{

	}

	//Implementing a body in the interface definition is supported in java 1.8,
	//but not for us. Cancelled bool is set manually rather than by calling a method.
	public interface OnRenderCompleteListener
	{
		void onRenderComplete(EmojiCacheItem item);
	}


}
