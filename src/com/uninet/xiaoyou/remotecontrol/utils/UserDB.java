package com.uninet.xiaoyou.remotecontrol.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Iterator;


import com.uninet.xiaoyou.remotecontrol.data.Value;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class UserDB extends SQLiteOpenHelper {
	
	final static String TAG = "UserDB";

	final static String USER_TAB	="user_tab";

	private static final int DB_VERSION = 1;
	
	@SuppressLint("SdCardPath")
	private static String DB_PATH = "/data/data/com.uninet.xiaoyou/databases/";
	
	
	// private static String DB_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()
	// + "/arthurcn/drivertest/packfiles/";
	
	private static String DB_NAME = "UserDB.db";
	private static String ASSETS_NAME = "UserDB.db";
	
	private SQLiteDatabase myUserDB;
	private final Context myContext;
	


	public UserDB(Context context, String name, CursorFactory factory, int version) {

		super(context, name, null, version);
		this.myContext = context;
	}
	
	public UserDB(Context context, String name, int version){
		this(context,name,null,version);
	}
	
	public UserDB(Context context, String name){
		this(context,name,DB_VERSION);
	}
	
	public UserDB (Context context) {
		this(context, DB_PATH + DB_NAME);
	}
	
	public void createDataBase() throws IOException{
		boolean dbExist = checkDataBase();
		
		if (dbExist==false){
		
	
		try {
			File dir = new File(DB_PATH);
			if(!dir.exists()){
			dir.mkdirs();
			}
			File dbf = new File(DB_PATH + DB_NAME);
			if(dbf.exists()){
			dbf.delete();
		}
		//	SQLiteDatabase.openOrCreateDatabase(dbf, null);
		
			copyDataBase();
			//copyBigDataBase();
			} catch (IOException e) {
			throw new Error("ERROR");
			}
		}
		
	}


	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		String myPath = DB_PATH + DB_NAME;
		try{ 
		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
		//database does't exist yet.
		}
		if(checkDB != null){
		checkDB.close();
		Log.v(TAG, "db  close");
	
		}
		return checkDB != null ? true : false;
	}
	
	
	public UserDB open(){
		String myPath = DB_PATH + DB_NAME;
	//	Log.v(TAG, "鏁版嵁搴撳凡缁�..");
		
		myUserDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	//	Log.v(TAG, "鏁版嵁搴撴墦寮�);
		return this;
	
	}
	
	/**
	* Copies your database from your local assets-folder to the just created empty database in the
	* system folder, from where it can be accessed and handled.
	* This is done by transfering bytestream.
	* */
	private void copyDataBase() throws IOException{
		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(ASSETS_NAME);
		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
		myOutput.write(buffer, 0, length);
		}
		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	

	@Override
	public synchronized void close() {
		if(myUserDB != null){
			myUserDB.close();
	//	System.out.println("鍏抽棴鎴愬姛1");
		}
		super.close();
	//	System.out.println("鍏抽棴鎴愬姛2");
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	
	
	public void getUserKeyValue(){
		
			Cursor c = myUserDB.query(Value.USERTAB, null, null ,null, null, null, null);
			c.moveToFirst();
			do{
				Value.keyRemoteTab.put(c.getString(1), c.getString(2));
			}while(c.moveToNext());
	
			
		
		c.close(); 

	}
	
	

	public  void saveAllKeyTabValue(){
		 ContentValues cv = new ContentValues();
		Log.v(TAG, "saveAllKeyTabValue start");
		Iterator<String> iterator = Value.keyRemoteTab.keySet().iterator();
		while(iterator.hasNext()) {
			String keyName = iterator.next();
			cv.put(Value.USER_NAME,keyName );
		//	Log.v(TAG, "key name --->"+ keyName);
		    cv.put(Value.USER_DATA,Value.keyRemoteTab.get(keyName) );
		//    Log.v(TAG, "key data --->"+ Value.keyRemoteTab.get(keyName));
		    myUserDB.update(Value.USERTAB, cv, "name=?", new String[] {keyName});
		}
	
	}
	

	public  void saveSingleKeyTabValue(String keyName,String data){
		  ContentValues values = new ContentValues();
		      values.put(Value.USER_NAME, keyName);
	
		      values.put(Value.USER_DATA,data );

		  	myUserDB.update(Value.USERTAB, values, "name=?", new String[] {keyName});

		  
	}
	
	
	
}