package com.uninet.xiaoyou.remotecontrol.remote;



import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.ircomm.RemoteComm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class StudyActivity extends Activity implements OnClickListener{
	 StudyHandler studyHandler;
	 StudyThread sThread;
	 final static String TAG = "StudyActivity";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_studying);
		DisplayMetrics dm = new DisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		
		
		
		Button studyExit = (Button) findViewById(R.id.button_study_exit);
		studyExit.setOnClickListener(this);
		studyExit.setWidth((screenWidth) / 4);
		studyExit.setHeight((screenHeight) / 10);
		studyHandler = new StudyHandler();  
         
	}
	
	
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		  sThread = new StudyThread();  
          sThread.start();  
          Log.v(TAG, "start");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
     
		Log.v(TAG, "stop");
	
	}



	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
	
		case R.id.button_study_exit:
		RemoteComm.remoteLearnStop();
		Value.isStudying = false;
		 Bundle bundle =  new Bundle(); 
		 bundle.putInt("status", -1);  
		setResult(RESULT_CANCELED, getIntent().putExtras(bundle)); 
		Log.v(TAG, "onclick quit");
	//	sThread.stop();
			finish();
			break;
		}
	}
	
	  @SuppressLint("HandlerLeak")
	private class StudyHandler  extends Handler{ 
		  
		  
	    
	        @Override  
	        public void handleMessage(Message msg) {  
	            // TODO Auto-generated method stub  
	            super.handleMessage(msg);  
	            Bundle bundle = msg.getData();  
	            int status = bundle.getInt("status");
	          if (status==-1){
	        	  RemoteComm.remoteLearnStop();
	      		Value.isStudying = false;
	      		setResult(RESULT_CANCELED, getIntent().putExtras(bundle)); 
	      		 Log.v(TAG, "remote error");
	      		finish();
	          }else if(status==0){
	        	  RemoteComm.remoteLearnStop();
		      		Value.isStudying = false;
		      		setResult(RESULT_CANCELED, getIntent().putExtras(bundle)); 
		      		 Log.v(TAG, "remote timeout");
		      		finish();  
	          }
		      		else if(status==-2){
			        	  RemoteComm.remoteLearnStop();
				      		Value.isStudying = false;
				      		setResult(RESULT_CANCELED, getIntent().putExtras(bundle)); 
				      		 Log.v(TAG, "remote not prepared");
				      		finish();  
	          }else {
	        	  Value.isStudying = true;
	        	  RemoteComm.remoteLearnStop();
	        	  setResult(RESULT_OK, getIntent().putExtras(bundle)); 
	        	  Log.v(TAG, "remote successed");
	        	finish();  
	          }
	        }  
	    }  
	    private class StudyThread extends Thread{ 
	    	int status;
	        @Override  
	        public void run() {  
	        	
	        	long   start   =   System.currentTimeMillis(); 
	            status=	RemoteComm.learnRemoteLoop(30);  
	        	long   end   =   System.currentTimeMillis(); 
	        	long time = end-start;
	        	Log.v(TAG, "study time is " + time);
	        	if (time <1000){
	        	
	        

		        	   start   =   System.currentTimeMillis(); 
		            status=	RemoteComm.learnRemoteLoop(30);  
		        	   end   =   System.currentTimeMillis(); 
		        		if ((end-start) <1000){
		        			Log.v(TAG, "learn failed");
				        	status = -2;	
			        	}
	        	}
	        		
	            Message msg = new Message();  
	            Bundle bundle = new Bundle();  
	            bundle.putInt("status", status);  
	            msg.setData(bundle);  
	            StudyActivity.this.studyHandler.sendMessage(msg);  
	              
	        }  
	    }  
	
}