package com.uninet.xiaoyou.remotecontrol.remote;



import java.util.ArrayList;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.KeyToRemote;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.ircomm.KeyTreate;
import com.uninet.xiaoyou.remotecontrol.utils.MyRemoteDatabase;
import com.uninet.xiaoyou.remotecontrol.utils.RemoteDB;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class FanActivity extends Activity implements OnClickListener {
	private static final String TAG = "fanactivity";
//	private RemoteDB mRmtDB = null;
//	private Spinner CodeSp = null;
//	ArrayAdapter<String> codeAdapter;
//	private ArrayList<String> codes = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fan);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		
		Button fan_key0 = (Button) findViewById(R.id.fan_speed);
		fan_key0.setOnClickListener(this);
		fan_key0.setWidth((screenWidth) / 4);
		fan_key0.setHeight((screenHeight) / 11);
		fan_key0.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key1 = (Button) findViewById(R.id.fan_key1);
		fan_key1.setOnClickListener(this);
		fan_key1.setWidth((screenWidth) / 4);
		fan_key1.setHeight((screenHeight) / 11);
		fan_key1.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key2 = (Button) findViewById(R.id.fan_key2);
		fan_key2.setOnClickListener(this);
		fan_key2.setWidth((screenWidth) / 4);
		fan_key2.setHeight((screenHeight) / 11);
		fan_key2.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key3 = (Button) findViewById(R.id.fan_key3);
		fan_key3.setOnClickListener(this);
		fan_key3.setWidth((screenWidth) / 4);
		fan_key3.setHeight((screenHeight) / 11);
		fan_key3.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key4 = (Button) findViewById(R.id.fan_key4);
		fan_key4.setOnClickListener(this);
		fan_key4.setWidth((screenWidth) / 4);
		fan_key4.setHeight((screenHeight) / 11);
		fan_key4.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key5 = (Button) findViewById(R.id.fan_key5);
		fan_key5.setOnClickListener(this);
		fan_key5.setWidth((screenWidth) / 4);
		fan_key5.setHeight((screenHeight) / 11);
		fan_key5.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key6 = (Button) findViewById(R.id.fan_key6);
		fan_key6.setOnClickListener(this);
		fan_key6.setWidth((screenWidth) / 4);
		fan_key6.setHeight((screenHeight) / 11);
		fan_key6.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key7 = (Button) findViewById(R.id.fan_key7);
		fan_key7.setOnClickListener(this);
		fan_key7.setWidth((screenWidth) / 4);
		fan_key7.setHeight((screenHeight) / 11);
		fan_key7.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key8 = (Button) findViewById(R.id.fan_key8);
		fan_key8.setOnClickListener(this);
		fan_key8.setWidth((screenWidth) / 4);
		fan_key8.setHeight((screenHeight) / 11);
		fan_key8.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_key9 = (Button) findViewById(R.id.fan_key9);
		fan_key9.setOnClickListener(this);
		fan_key9.setWidth((screenWidth) / 4);
		fan_key9.setHeight((screenHeight) / 11);
		fan_key9.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_power = (Button) findViewById(R.id.fan_power);
		fan_power.setOnClickListener(this);
		fan_power.setWidth((screenWidth) / 4);
		fan_power.setHeight((screenHeight) / 11);
		fan_power.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_timer = (Button) findViewById(R.id.fan_timer);
		fan_timer.setOnClickListener(this);
		fan_timer.setWidth((screenWidth) / 4);
		fan_timer.setHeight((screenHeight) / 11);
		fan_timer.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_li = (Button) findViewById(R.id.fan_anion);
		fan_li.setOnClickListener(this);
		fan_li.setWidth((screenWidth) / 4);
		fan_li.setHeight((screenHeight) / 11);
		fan_li.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_cool = (Button) findViewById(R.id.fan_cool);
		fan_cool.setOnClickListener(this);
		fan_cool.setWidth((screenWidth) / 4);
		fan_cool.setHeight((screenHeight) / 11);
		fan_cool.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_mode = (Button) findViewById(R.id.fan_mode);
		fan_mode.setOnClickListener(this);
		fan_mode.setWidth((screenWidth) / 4);
		fan_mode.setHeight((screenHeight) / 11);
		fan_mode.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_sleep = (Button) findViewById(R.id.fan_sleep);
		fan_sleep.setOnClickListener(this);
		fan_sleep.setWidth((screenWidth) / 4);
		fan_sleep.setHeight((screenHeight) / 11);
		fan_sleep.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_light = (Button) findViewById(R.id.fan_light);
		fan_light.setOnClickListener(this);
		fan_light.setWidth((screenWidth) / 4);
		fan_light.setHeight((screenHeight) / 11);
		fan_light.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_speed = (Button) findViewById(R.id.fan_volume);
		fan_speed.setOnClickListener(this);
		fan_speed.setWidth((screenWidth) / 4);
		fan_speed.setHeight((screenHeight) / 11);
		fan_speed.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_speedlow = (Button) findViewById(R.id.fan_speedlow);
		fan_speedlow.setOnClickListener(this);
		fan_speedlow.setWidth((screenWidth) / 4);
		fan_speedlow.setHeight((screenHeight) / 11);
		fan_speedlow.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_speedmiddle = (Button) findViewById(R.id.fan_speedmiddle);
		fan_speedmiddle.setOnClickListener(this);
		fan_speedmiddle.setWidth((screenWidth) / 4);
		fan_speedmiddle.setHeight((screenHeight) / 11);
		fan_speedmiddle.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_speedhight = (Button) findViewById(R.id.fan_speedhigh);
		fan_speedhight.setOnClickListener(this);
		fan_speedhight.setWidth((screenWidth) / 4);
		fan_speedhight.setHeight((screenHeight) / 11);
		fan_speedhight.setBackgroundResource(R.drawable.btn_normal);
		
		Button fan_freq = (Button) findViewById(R.id.fan_shake);
		fan_freq.setOnClickListener(this);
		fan_freq.setWidth((screenWidth) / 4);
		fan_freq.setHeight((screenHeight) / 11);
		fan_freq.setBackgroundResource(R.drawable.btn_normal);
//		mRmtDB = new RemoteDB(this);
//		mRmtDB.open();
//		codes=mRmtDB.getCodeAll("FAN");
//		mRmtDB.close();
//		codeAdapter =new  ArrayAdapter<String>(this, R.layout.option_item, codes);

//		CodeSp = (Spinner) findViewById(R.id.fan_code);
//		CodeSp.setAdapter(codeAdapter);
//		CodeSp.setOnItemSelectedListener(this);
//		CodeSp.setSelection(1);
	}
	@Override
	public void onStart()
	{
		super.onStart();
	
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			if (event.isLongPress()) {
				openOptionsMenu();
				return true;
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.fan_speed:
			Value.currentKey = "fan_speed";
			break;
		case R.id.fan_key1:
			Value.currentKey = "fan_key1";
			break;
		case R.id.fan_key2:
			Value.currentKey = "fan_key2";
			break;
		case R.id.fan_key3:
			Value.currentKey = "fan_key3";
			break;
		case R.id.fan_key4:
			Value.currentKey = "fan_key4";
			break;
		case R.id.fan_key5:
			Value.currentKey = "fan_key5";
			break;
		case R.id.fan_key6:
			Value.currentKey = "fan_key6";
			break;
		case R.id.fan_key7:
			Value.currentKey = "fan_key7";
			break;
		case R.id.fan_key8:
			Value.currentKey = "fan_key8";
			break;
		case R.id.fan_key9:
			Value.currentKey = "fan_key9";
			break;
		case R.id.fan_sleep:
			Value.currentKey = "fan_sleep";
			break;
		case R.id.fan_power:
			Value.currentKey = "fan_power";
			break;
		case R.id.fan_mode:
			Value.currentKey = "fan_mode";
			break;
		case R.id.fan_cool:
			Value.currentKey = "fan_cool";
			break;
		case R.id.fan_anion:
			Value.currentKey = "fan_anion";
			break;
		case R.id.fan_timer:
			Value.currentKey = "fan_timer";
			break;
		case R.id.fan_volume:
			Value.currentKey = "fan_volume";
			break;
		case R.id.fan_light:
			Value.currentKey = "fan_light";
			break;
		case R.id.fan_speedlow:
			Value.currentKey = "fan_speedlow";
			break;
		case R.id.fan_speedmiddle:
			Value.currentKey = "fan_speedmiddle";
			break;
		case R.id.fan_speedhigh:
			Value.currentKey = "fan_speedhigh";
			break;
		case R.id.fan_shake:
			Value.currentKey = "fan_shake";
			break;
		default:
			Value.currentKey = null;
		}
		if (Value.currentKey != null) {
			try {
				KeyTreate.getInstance().keyTreate();
		//		KeyData.Write(Value.currentKey);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
//	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
//			long arg3) {
//		// TODO Auto-generated method stub
//		if (arg0 == CodeSp) {
//			String index = codeAdapter.getItem(arg2).toString();
//	
//			
//			Value.fan_index=index;
//			Log.v(TAG, index);
//			MyRemoteDatabase.saveRemoteIndex(getApplicationContext());
//			KeyToRemote.keyTabSetValue(Value.DeviceType.TYPE_FAN,getApplicationContext());
//				 
//		}
//			
//	}
//	@Override
//	public void onNothingSelected(AdapterView<?> arg0) {
//		// TODO Auto-generated method stub
//		
//	}
}