package com.uninet.xiaoyou.wirelessstore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BackupDatabaseHelper extends SQLiteOpenHelper {
	
	public BackupDatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	void CreateTable(String tablename, String Fields){
			getWritableDatabase().execSQL(
				"create table if not exists" 
				+ " "
				+ tablename 
				+ "("
				+ Fields
				+ ")");
	}

	/**
	 * @param tablename tablename
	 * @param sqlcmd1 tablename(sqlcmd1)
	 * @param sqlcmd2 为 values(sqlcmd2)
	 */
	void Inser(String tablename, String sqlcmd1, String sqlcmd2){
	
		Log.d("abc","insert sql = " + "insert into"	+ " "+ tablename + "(" + sqlcmd1 + ")" + " values" +"(" + sqlcmd2 + ")");
		
		getWritableDatabase().execSQL(
				"insert into"
				+ " "
				+ tablename
				+ "(" 
				+ sqlcmd1
				+ ")"
				+ " values"
				+"("
				+ sqlcmd2
				+ ")");
	}

	/**
	 * @param tablename tablename
	 * @param sqlcmd2 为values(sqlcmd2)
	 */
	void Inser(String tablename, String sqlcmd2){
		
		Log.d("abc","insert sql = " + "insert into"	+ " "+ tablename + " values" +"(" + sqlcmd2 + ")");
		
		getWritableDatabase().execSQL(
				"insert into"
				+ " "
				+ tablename
				+ " values"
				+"("
				+ sqlcmd2
				+ ")");
	}
	/**
	 * @param tablename tablename
	 * @param condition query condition: where condition 
	 */
	boolean Query(String tablename,String condition){
		String sql = 
				"select * from"
				+ " "
			    + tablename
			    + " "
			    + "where"
			    + " "
			    + condition;
		Cursor cursor = getWritableDatabase().rawQuery(sql, null);
		cursor.moveToFirst();
		
		return  (cursor.getCount()>0) ? true: false;
	}
	
	String[] QueryField(String tablename, String Field){
		String sql = 
				"select " + Field + " from"
				+ " "
			    + tablename;
		Cursor cursor = getWritableDatabase().rawQuery(sql, null);
		cursor.moveToFirst();
		
		String[] columeRecord = new String[cursor.getCount()];
		int index= 0;
		do{
			columeRecord[index++] = cursor.getString(0);
		}while(cursor.moveToNext());
		
		return  columeRecord;
	}
	
	void DeletRecord(String tablename,String condition){
		String sql = 
				"delete from"
				+ " "
			    + tablename
			    + " "
			    + "where"
			    + " "
			    + condition;
		getWritableDatabase().execSQL(sql);
	}
	
	void UpdateFieldValue(String tablename, String condition){
		String sql = 
				"update "
			    + tablename
			    + " set "
			    + condition;
		getWritableDatabase().execSQL(sql);
	}
}
