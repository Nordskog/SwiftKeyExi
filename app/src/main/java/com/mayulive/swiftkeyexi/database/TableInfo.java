package com.mayulive.swiftkeyexi.database;

public class TableInfo
{
	public String[] projection;
	public String tableName;
	public String tableDefinition;
	public DatabaseItem item;
	
	public TableInfo (DatabaseItem item, String[] projection, String tableDefinition, String tableName)
	{
		this.item = item;
		this.projection = projection;
		this.tableName = tableName;
		this.tableDefinition = tableDefinition;
	}

}
