package com.uninet.xiaoyou.wirelessstore;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;


public class TaskHandler {
	private static volatile  TaskHandler th;
	private static volatile Handler mhandler;
	private static String taskname = "TASK_NAME";
	
	private TaskHandler() {
		TaskThread tt = new TaskThread(taskname);
		tt.start();
		mhandler = new Handler(tt.getLooper(),tt);
	}
	
	public static TaskHandler GetTaskHandler(){
		if(th == null){
			th =  new TaskHandler();
			Log.d(SmbOpApi.TAG,"new TaskHandler");
		}
		Log.d(SmbOpApi.TAG,"return TaskHandler");
		return th;
	}
	
	public Handler GetMessageHander(){
		return mhandler;
	}
	
	class TaskThread extends HandlerThread implements Callback{

		public TaskThread(String name) {
			super(name);
		}

		@Override
		public boolean handleMessage(Message arg0) {
			arg0.getCallback();
			return false;
		}
	}
}