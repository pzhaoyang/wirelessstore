package com.uninet.xiaoyou.remotecontrol.remote;








import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.ircomm.KeyTreate;
import com.uninet.xiaoyou.remotecontrol.ircomm.RemoteComm;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;


import android.widget.Button;





public class TVActivity extends Activity implements OnClickListener,
		OnLongClickListener {

	
private String TAG = "TVActivity";

	//	private Spinner codeSp = null;
//	ArrayAdapter<String> codeAdapter;
//	private ArrayList<String> codes = new ArrayList<String>();
//	TextView keyValueIndex;
//	String TVCodeIndex;
//	HashMap<String , KeyValue> keyTab = new HashMap<String , KeyValue>();   
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tv);
		
		
	
		
		
	//	TVCodeIndex = MyRemoteIndexStore.getUserDB(getApplicationContext(),"mTVIndex");
	
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;

		Button tv_key0 = (Button) findViewById(R.id.tv_key0);
		tv_key0.setOnClickListener(this);
		
		tv_key0.setWidth((screenWidth) / 4);
		tv_key0.setHeight((screenHeight) / 10);
	
		

		Button tv_key1 = (Button) findViewById(R.id.tv_key1);
		tv_key1.setOnClickListener(this);
		tv_key1.setWidth((screenWidth) / 4);
		tv_key1.setHeight((screenHeight) / 10);
	//	tv_key1.getBackground().setAlpha(50);
		
		
		Button tv_key2 = (Button) findViewById(R.id.tv_key2);
		tv_key2.setOnClickListener(this);
		tv_key2.setWidth((screenWidth) / 4);
		tv_key2.setHeight((screenHeight) / 10);
	//	tv_key2.getBackground().setAlpha(50);
		
		
		Button tv_key3 = (Button) findViewById(R.id.tv_key3);
		tv_key3.setOnClickListener(this);
		tv_key3.setWidth((screenWidth) / 4);
		tv_key3.setHeight((screenHeight) / 10);
	//	tv_key3.getBackground().setAlpha(50);
		
		Button tv_key4 = (Button) findViewById(R.id.tv_key4);
		tv_key4.setOnClickListener(this);
		tv_key4.setWidth((screenWidth) / 4);
		tv_key4.setHeight((screenHeight) / 10);
		

		Button tv_key5 = (Button) findViewById(R.id.tv_key5);
		tv_key5.setOnClickListener(this);
		tv_key5.setWidth((screenWidth) / 4);
		tv_key5.setHeight((screenHeight) / 10);
		

		Button tv_key6 = (Button) findViewById(R.id.tv_key6);
		tv_key6.setOnClickListener(this);
		tv_key6.setWidth((screenWidth) / 4);
		tv_key6.setHeight((screenHeight) / 10);
	

		Button tv_key7 = (Button) findViewById(R.id.tv_key7);
		tv_key7.setOnClickListener(this);
		tv_key7.setWidth((screenWidth) / 4);
		tv_key7.setHeight((screenHeight) / 10);
		

		Button tv_key8 = (Button) findViewById(R.id.tv_key8);
		tv_key8.setOnClickListener(this);
		tv_key8.setWidth((screenWidth) / 4);
		tv_key8.setHeight((screenHeight) / 10);
		

		Button tv_key9 = (Button) findViewById(R.id.tv_key9);
		tv_key9.setOnClickListener(this);
		tv_key9.setWidth((screenWidth) / 4);
		tv_key9.setHeight((screenHeight) / 10);
		
		
		Button tv_10 = (Button) findViewById(R.id.tv_key10);
		tv_10.setOnClickListener(this);
		tv_10.setWidth((screenWidth) / 4);
		tv_10.setHeight((screenHeight) / 10);
	

		Button tv_power = (Button) findViewById(R.id.tv_power);
		tv_power.setOnClickListener(this);
		tv_power.setWidth((screenWidth) / 4);
		tv_power.setHeight((screenHeight) / 10);
	

		Button tv_av = (Button) findViewById(R.id.tv_av);
		tv_av.setOnClickListener(this);
		tv_av.setWidth((screenWidth) / 4);
		tv_av.setHeight((screenHeight) / 10);
	

		Button tv_mute = (Button) findViewById(R.id.tv_mute);
		tv_mute.setOnClickListener(this);
		tv_mute.setWidth((screenWidth) / 4);
		tv_mute.setHeight((screenHeight) / 10);
	

		Button tv_back = (Button) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(this);
		tv_back.setWidth((screenWidth) / 4);
		tv_back.setHeight((screenHeight) / 10);
	

		Button tv_chadd = (Button) findViewById(R.id.tv_chadd);
		tv_chadd.setOnClickListener(this);
		tv_chadd.setWidth((screenWidth) / 4);
		tv_chadd.setHeight((screenHeight) / 10);
		

		Button tv_chsub = (Button) findViewById(R.id.tv_chsub);
		tv_chsub.setOnClickListener(this);
		tv_chsub.setWidth((screenWidth) / 4);
		tv_chsub.setHeight((screenHeight) / 10);
	

		Button tv_voladd = (Button) findViewById(R.id.tv_voladd);
		tv_voladd.setOnClickListener(this);
	//	tv_voladd.setOnLongClickListener(this);
		tv_voladd.setWidth((screenWidth) / 4);
		tv_voladd.setHeight((screenHeight) / 10);
		

		Button tv_volsub = (Button) findViewById(R.id.tv_volsub);
		tv_volsub.setOnClickListener(this);
		tv_volsub.setWidth((screenWidth) / 4);
		tv_volsub.setHeight((screenHeight) / 10);
		
//		Button tv_extend = (Button) findViewById(R.id.tv_extend);
//		tv_extend.setOnClickListener(this);
//		tv_extend.setWidth((screenWidth) / 4);
//		tv_extend.setHeight((screenHeight) / 10);

		Button tv_ok = (Button) findViewById(R.id.tv_ok);
		tv_ok.setOnClickListener(this);
		tv_ok.setWidth((screenWidth) / 4);
		tv_ok.setHeight((screenHeight) / 10);
	

		Button tv_menu = (Button) findViewById(R.id.tv_menu);
		tv_menu.setOnClickListener(this);
		tv_menu.setWidth((screenWidth) / 4);
		tv_menu.setHeight((screenHeight) / 10);
		
		Button tv_up = (Button) findViewById(R.id.tv_up);
		tv_up.setOnClickListener(this);
		tv_up.setWidth((screenWidth) / 4);
		tv_up.setHeight((screenHeight) / 10);
		Button tv_down = (Button) findViewById(R.id.tv_down);
		tv_down.setOnClickListener(this);
		tv_down.setWidth((screenWidth) / 4);
		tv_down.setHeight((screenHeight) / 10);
		Button tv_left = (Button) findViewById(R.id.tv_left);
		tv_left.setOnClickListener(this);
		tv_left.setWidth((screenWidth) / 4);
		tv_left.setHeight((screenHeight) / 10);
		Button tv_right = (Button) findViewById(R.id.tv_right);
		tv_right.setOnClickListener(this);
		tv_right.setWidth((screenWidth) / 4);
		tv_right.setHeight((screenHeight) / 10);
		
		
//		mRmtDB = new RemoteDB(this);
//		mRmtDB.open();
//	//	codes=mRmtDB.getCodeAll(Value.DeviceType.TYPE_TV);
//		mRmtDB.close();
//		codeAdapter =new  ArrayAdapter<String>(this, R.layout.option_item, codes);
//
//		codeSp = (Spinner) findViewById(R.id.tv_code);
//		codeSp.setAdapter(codeAdapter);
//		codeSp.setOnItemSelectedListener(this);
//		codeSp.setSelection(0);
		
//		 keyValueIndex = (TextView) findViewById(R.id.tv_showkey);
//		 keyValueIndex.setTextSize(24);
//		 keyValueIndex.setWidth((screenWidth) / 4);
//		 keyValueIndex.setHeight((screenHeight) / 10);

	}
	@Override
	public void onStart()
	{
		super.onStart();
	
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
			return super.onKeyDown(keyCode, event);
		
	}

	public void onClick(View _view) {
		switch (_view.getId()) {
		case R.id.tv_key0:
			Value.currentKey = "tv_key0";
			break;
		case R.id.tv_key1:
		
			Value.currentKey = "tv_key1";
			break;
		case R.id.tv_key2:
			
			Value.currentKey = "tv_key2";
			break;
		case R.id.tv_key3:
			
			Value.currentKey = "tv_key3";
			break;
		case R.id.tv_key4:
			
			Value.currentKey = "tv_key4";
			break;
		case R.id.tv_key5:
			
			Value.currentKey = "tv_key5";
			break;
		case R.id.tv_key6:
			
			Value.currentKey = "tv_key6";
			break;
		case R.id.tv_key7:
			
			Value.currentKey = "tv_key7";
			break;
		case R.id.tv_key8:
			
			Value.currentKey = "tv_key8";
			break;
		case R.id.tv_key9:
		
			Value.currentKey = "tv_key9";
			break;
		case R.id.tv_key10:
			
			Value.currentKey = "tv_key10";
			break;
		case R.id.tv_power:
		
			Value.currentKey = "tv_power";
			break;
		case R.id.tv_av:
		
			Value.currentKey = "tv_av";
			break;
		case R.id.tv_mute:
		
			Value.currentKey = "tv_mute";
			break;
		case R.id.tv_back:
			
			Value.currentKey = "tv_back";
			break;
		case R.id.tv_chadd:
		
			Value.currentKey = "tv_chadd";
			break;
		case R.id.tv_chsub:
			
			Value.currentKey = "tv_chsub";
			break;
		case R.id.tv_voladd:
			
			Value.currentKey = "tv_voladd";
			break;
		case R.id.tv_volsub:
			
			Value.currentKey = "tv_volsub";
			break;
		case R.id.tv_ok:
		
			Value.currentKey = "tv_ok";
			break;
		case R.id.tv_menu:
		
			Value.currentKey = "tv_menu";
			break;
		case R.id.tv_up:
			
			Value.currentKey = "tv_up";
			break;
		case R.id.tv_down:
			
			Value.currentKey = "tv_down";
			break;
		case R.id.tv_left:
			
			Value.currentKey = "tv_left";
			break;
		case R.id.tv_right:
			
			Value.currentKey = "tv_right";
			break;
//		case R.id.tv_extend:
//			Value.currentKey = null;
//			Intent i = new Intent(this,
//					TVExtend.class);
//			startActivity(i);
//			break;
		default:
			
			Value.currentKey = null;
			break;
		}
		if (Value.currentKey != null) {
			KeyTreate.getInstance().keyTreate();
		
//			if (kv.getIsLearned()==1){
//			keyValueIndex.setText("Learn key value     " +Value.currentKey);
//			keyValueIndex.setTextColor(Color.RED);
//			}
//			else {
//			keyValueIndex.setText("normal key value     " +Value.currentKey);
//			keyValueIndex.setTextColor(Color.BLUE);	
//			}
			
		}
	}

	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_key0:
			Value.currentKey = "tv_key0";
			break;
		case R.id.tv_key1:
		
			Value.currentKey = "tv_key1";
			break;
		case R.id.tv_key2:
			
			Value.currentKey = "tv_key2";
			break;
		case R.id.tv_key3:
			
			Value.currentKey = "tv_key3";
			break;
		case R.id.tv_key4:
			
			Value.currentKey = "tv_key4";
			break;
		case R.id.tv_key5:
			
			Value.currentKey = "tv_key5";
			break;
		case R.id.tv_key6:
			
			Value.currentKey = "tv_key6";
			break;
		case R.id.tv_key7:
			
			Value.currentKey = "tv_key7";
			break;
		case R.id.tv_key8:
			
			Value.currentKey = "tv_key8";
			break;
		case R.id.tv_key9:
		
			Value.currentKey = "tv_key9";
			break;
		case R.id.tv_key10:
			
			Value.currentKey = "tv_key10";
			break;
		case R.id.tv_power:
		
			Value.currentKey = "tv_power";
			break;
		case R.id.tv_av:
		
			Value.currentKey = "tv_av";
			break;
		case R.id.tv_mute:
		
			Value.currentKey = "tv_mute";
			break;
		case R.id.tv_back:
			
			Value.currentKey = "tv_back";
			break;
		case R.id.tv_chadd:
		
			Value.currentKey = "tv_chadd";
			break;
		case R.id.tv_chsub:
			
			Value.currentKey = "tv_chsub";
			break;
		case R.id.tv_voladd:
			
			Value.currentKey = "tv_voladd";
			break;
		case R.id.tv_volsub:
			
			Value.currentKey = "tv_volsub";
			break;
		case R.id.tv_ok:
		
			Value.currentKey = "tv_ok";
			break;
		case R.id.tv_menu:
		
			Value.currentKey = "tv_menu";
			break;
		default:
			
			Value.currentKey = null;
			break;
		}
		if (Value.currentKey != null) {
		
			Log.v(TAG , "keyvalue long press" + Value.currentKey);
			KeyTreate.getInstance().keyTreate();
			return true;
//			if (kv.getIsLearned()==1){
//			keyValueIndex.setText("Learn key value     " +Value.currentKey);
//			keyValueIndex.setTextColor(Color.RED);
//			}
//			else {
//			keyValueIndex.setText("normal key value     " +Value.currentKey);
//			keyValueIndex.setTextColor(Color.BLUE);	
//			}
			
		}
		return false;
	}
	
	
	
}
