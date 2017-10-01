package com.mayulive.swiftkeyexi.database;




import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Roughy on 2/4/2017.
 */


//An ArrayList that also updates the corresponding table
	//In the database, the order of items really doesn't matter.
	//Extending arraylist is bad practice, but a million times simpler.

public class TableList<T extends DatabaseItem> extends ArrayList<T>
{

	private boolean mBatchEdit = false;
	private DatabaseMode mMode = DatabaseMode.IMMEDIATE;

	private DatabaseWrapper mDbWrap = null;
	private TableInfo mInfo = null;

	private ArrayList<PendingOperation> mPendingOperations = new ArrayList<>();


	public enum DatabaseMode
	{
		IMMEDIATE, DELAYED, MANUAL;

		public boolean addToPending()
		{
			return this == DELAYED;
		}

		public boolean performDbOp()
		{
			return this != MANUAL;
		}
	}

	public enum DatabaseOperation
	{
		ADD, REMOVE, UPDATE, CLEAR, UPDATE_ALL
	}

	private class PendingOperation
	{
		DatabaseOperation operation;
		T item;

		PendingOperation(DatabaseOperation operation, @Nullable T item)
		{
			this.operation = operation;
			this.item = item;
		}
	}

	public TableList()
	{

	}

	public TableList(DatabaseWrapper db, TableInfo info)
	{
		mDbWrap = db;
		mInfo = info;

		populateFromDb();
	}

	public boolean isConnected()
	{
		return mInfo != null && mDbWrap != null;
	}

	public void setDatabaseMode(DatabaseMode mode)
	{
		mMode = mode;
	}

	public void clearPendingOperations()
	{
		mPendingOperations.clear();
	}

	//Chuck anything already in the database, write current content instead.
	public void replaceTableWithContents()
	{
		//Ignore all modes and whatnot
		clearPendingOperations();
		dbClear();

		for (DatabaseItem item : this)
		{
			item.set_id(-1);
		}

		DatabaseMethods.addAllItems(mDbWrap, mInfo, this);
	}

	public void processPendingOperations() throws IllegalStateException
	{
		if (mInfo != null && mDbWrap != null)
		{
			this.startBatchEdit();

			for (PendingOperation operation : mPendingOperations)
			{
				performDbOperation(operation.operation, operation.item, true);
			}

			mPendingOperations.clear();
			this.endBatchEdit();
		}
		else
		{
			throw new IllegalStateException("Attempted to process pending operations queue on a table with no db or no tableinfo");
		}
	}

	public void performDbOperation(DatabaseOperation operation, int index, boolean force) throws IllegalStateException
	{
		performDbOperation(operation, get(index), force);
	}

	public void performDbOperation(DatabaseOperation operation, T item, boolean force) throws IllegalStateException
	{

		if (mMode.addToPending() && !force)
		{
			switch(operation)
			{
				case ADD:
				{
					mPendingOperations.add(new PendingOperation(DatabaseOperation.ADD, item));
					break;
				}
				case REMOVE:
				{
					mPendingOperations.add(new PendingOperation(DatabaseOperation.REMOVE, item));
					break;
				}
				case UPDATE:
				{
					mPendingOperations.add(new PendingOperation(DatabaseOperation.UPDATE, item));
					break;
				}
				case CLEAR:
				{
					mPendingOperations.add(new PendingOperation(DatabaseOperation.CLEAR, null));
					break;
				}
				case UPDATE_ALL:
				{
					mPendingOperations.add(new PendingOperation(DatabaseOperation.UPDATE_ALL, null));
					break;
				}
			}
		}
		else if (force || mMode.performDbOp())
		{
			if (mInfo != null && mDbWrap != null)
			{
				switch(operation)
				{
					case ADD:
					{
						dbAdd(item);
						break;
					}
					case REMOVE:
					{
						dbRemove(item);
						break;
					}
					case UPDATE:
					{
						dbUpdate(item);
						break;
					}
					case CLEAR:
					{
						dbClear();
						break;
					}
					case UPDATE_ALL:
					{
						dbUpdateAll();
						break;
					}
				}
			}
		}
	}

	public void update(T item)
	{
		performDbOperation(DatabaseOperation.UPDATE, item, false);
	}


	public String getTableName()
	{
		if (mInfo == null)
		{
			return "null";
		}

		return mInfo.tableName;
	}

	public DatabaseWrapper getDatabaseWrapper()
	{
		return mDbWrap;
	}

	public TableInfo getTableInfo()
	{
		return mInfo;
	}

	@Override
	public void clear()
	{
		super.clear();
		dbClear();
	}


	//////////////////////////////////////////////////
	//Check connection and mode before calling these
	//////////////////////////////////////////////////

	private void dbClear()
	{
		DatabaseMethods.clearTable(mDbWrap, mInfo);
	}

	private void dbAdd(T item)
	{
		DatabaseMethods.addItem(mDbWrap, item, mInfo);
	}

	private void dbRemove(T item)
	{
		DatabaseMethods.deleteItem(mDbWrap, item, mInfo);
	}

	//Bad things will probably happen if item is not part of table
	//Not sure if I should even allow this.
	//Could check first, but would be somewhat expensive.
	private int dbUpdate(T item)
	{
		DatabaseMethods.updateItem(mDbWrap, item, mInfo, true);
		return 0;
	}

	private void dbUpdateAll()
	{
		DatabaseMethods.updateAllItems(mDbWrap, this, getTableInfo(), false);
	}


	////////////////////////////////////////////////////
	////////////////////////////////////////////////////


	public void removeFromDb()
	{
		super.clear();

		if (mInfo != null)
		{
			//This should be a new, empty able
			mDbWrap.deleteTable(mInfo.tableName);
			mInfo = null;
			mDbWrap = null;
		}

	}

	//The TableInfo should include the table name, which should not have been created yet.
	public void addToDb(DatabaseWrapper db, TableInfo info)
	{
		mDbWrap = db;
		mInfo = info;

		mDbWrap.createTable(mInfo.tableName, mInfo.tableDefinition);

		//This should be a new, empty table
		DatabaseMethods.addAllItems(mDbWrap, mInfo, this);
	}

	//Info and db not set.
	public void populateFromDb(DatabaseWrapper db, TableInfo info)
	{
		if (mInfo != null)
		{
			throw new IllegalStateException("Attempted to populate TableList that was already populated");
		}

		mDbWrap = db;
		mInfo = info;

		populateFromDb();
	}

	//Info and db already set, just grabs items
	public void populateFromDb()
	{
		if (mInfo != null)
		{
			super.clear();
			ArrayList<T> dbItems = (ArrayList<T>)DatabaseMethods.getAllItems(mDbWrap, mInfo);
			super.addAll(dbItems);
		}
	}

	@Override
	public void add(int index, T item)
	{
		performDbOperation(DatabaseOperation.ADD, item, false);
		super.add(index,item);
	}

	@Override
	public boolean add(T item)
	{
		performDbOperation(DatabaseOperation.ADD, item, false);
		return super.add(item);
	}

	public void moveToTop(T item)
	{
		if (size() > 1)
		{
			super.remove(item);
			super.add(0,item);
		}
	}

	@Override
	public boolean remove(Object item)
	{
		boolean removeReturn = super.remove(item);
		if (removeReturn)
		{
			performDbOperation(DatabaseOperation.REMOVE, (T)item, false);
		}
		return removeReturn;

	}

	@Override
	public T remove(int index)
	{
		T removedItem = super.remove(index);
		performDbOperation(DatabaseOperation.REMOVE, removedItem, false);
		return removedItem;
	}

	public void updateAll()
	{
		performDbOperation(DatabaseOperation.UPDATE_ALL, null, false);
	}

	//////////////

	public void startBatchEdit()
	{
		if (mBatchEdit)
		{
			throw new IllegalStateException("Attempted to begin batch edit when editing while already in progress");
		}

		if (mInfo != null)
		{
			mDbWrap.beginTransaction();
		}

		mBatchEdit = true;
	}


	public void endBatchEdit()
	{
		if (!mBatchEdit)
		{
			throw new IllegalStateException("Attempted to end batch edit when none was in progresss");
		}

		if (mInfo != null)
		{
			mDbWrap.setTransactionSuccessful();
			mDbWrap.endTransaction();
		}


		mBatchEdit = false;
	}
}
