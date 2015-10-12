package com.uninet.xiaoyou.wirelessstore;

import android.os.Environment;

public class Init {
	public static TaskHandler th = TaskHandler.GetTaskHandler();
	public static Object obLock = new Object();
	
	public static String getStoragePrefix(){
		String prefix = Environment.getExternalStorageDirectory().getParent();
		android.util.Log.d("abc","prefix = " + prefix);
		return prefix;
	} 
}