package com.mayulive.swiftkeyexi.main.dictionary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mayulive.swiftkeyexi.util.view.HeaderFooterRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Roughy on 1/10/2017.
 */

//For xposed
public abstract class CandidatesRecyclerAdapter extends HeaderFooterRecyclerAdapter<CandidatesRecyclerAdapter.CandidateItemViewHolder>
{

	private ArrayList<Object> mCandidates = new ArrayList<Object>();

	private ArrayList<OnItemClickListener> mClickListeners = new ArrayList<>();


	public CandidatesRecyclerAdapter(Context context)
	{
		super(context);
	}

	@Override
	public CandidateItemViewHolder onCreateView(ViewGroup parent, int type)
	{
		return new CandidateItemViewHolder(this, getCandidateViewInstance() );
	}

	public ArrayList<Object> getArray()
	{
		return mCandidates;
	}

	@Override
	public void onBindView(CandidateItemViewHolder holder, int position)
	{
		holder.setText(this,mCandidates.get(position));
	}

	@Override
	public int getCount()
	{
		return mCandidates.size();
	}


	public static class CandidateItemViewHolder extends HeaderFooterRecyclerAdapter.HFRecyclerViewHolder
	{
		public View view;
		public Object source = null;

		public CandidateItemViewHolder(View itemView)
		{
			super(itemView);
			this.view = itemView;
		}

		public CandidateItemViewHolder(final CandidatesRecyclerAdapter containingAdapter, View itemView)
		{
			super(itemView);
			this.view = itemView;

			//Log.e("###", "Setting click listener on: "+view.toString());

			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					//Log.e("###", "Clicky!!!");
					containingAdapter.onItemClick(CandidateItemViewHolder.this);
				}
			});
		}

		public void setText(CandidatesRecyclerAdapter containingAdapter, final Object source)
		{
				//Log.e("###", "Set source 0");
				containingAdapter.setCandidateViewText(view, source);
				this.source = source;
				//Log.e("###", "Set source 1");
		}
	}


	public void setOnItemClickListener(OnItemClickListener listener)
	{
		mClickListeners.add(listener);
	}

	public void removeOnItemClickListener(OnItemClickListener listener)
	{
		mClickListeners.remove(listener);
	}


	public interface OnItemClickListener
	{
		void onItemClick(CandidateItemViewHolder holder);
	}

	private void onItemClick(CandidateItemViewHolder holder)
	{
		for (OnItemClickListener listener : mClickListeners)
		{
			listener.onItemClick(holder);
		}
	}


	public abstract View getCandidateViewInstance();
	public abstract View setCandidateViewText(View view, Object source);
}
