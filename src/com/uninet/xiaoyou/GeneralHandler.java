package com.uninet.xiaoyou;

import com.uninet.xiaoyou.SyncLock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class GeneralHandler extends Handler{
	
	final public static int MSG_SHOW_TOAST = 1004;
	
	private static GeneralHandler gh;
	private static SyncLock sl = new SyncLock(1);
	private static Context mcontext;
		
	public static GeneralHandler GetExchangeHandlerInstance(Context context){
		mcontext = context;
		if(gh == null){
			gh = new GeneralHandler();
		}
		return gh;
	}

	public void setContext(Context context){
		mcontext = context;
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch(msg.what){
		
		case MSG_SHOW_TOAST:
			Toast.makeText(mcontext, (String)msg.obj, Toast.LENGTH_SHORT).show();
			break;
		default:
			Log.d("abc","GeneralHandler InVaild msg...");
			break;
		}
		super.handleMessage(msg);
	}
	
	public static Message MsgElement(int what, Object obj, int arg1){
		Message msg  = new Message();
		msg.what = what;
		msg.obj = obj;
		msg.arg1 = arg1;
		return msg;
	}
	
	public static Message MsgElement(int what, Object obj){
		Message msg  = new Message();
		msg.what = what;
		msg.obj = obj;
		return msg;
	}
}