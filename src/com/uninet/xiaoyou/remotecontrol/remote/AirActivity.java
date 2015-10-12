package com.uninet.xiaoyou.remotecontrol.remote;

import java.util.ArrayList;

import com.general.ircore.RemoteCore;
import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.Utility;
import com.uninet.xiaoyou.net.DataInteractionThread;
import com.uninet.xiaoyou.remotecontrol.data.AirData;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.utils.MyRemoteDatabase;
import com.uninet.xiaoyou.remotecontrol.utils.Tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AirActivity extends Activity implements OnClickListener  ,OnItemSelectedListener{
	TextView airShow;
	TextView airMode;
	TextView airWindDir;	
	TextView airWind;
	TextView airWindAuto;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_air);
		
		Typeface type= Typeface.createFromAsset(getAssets(),"fonts/lcd.TTF");
		DisplayMetrics dm = new DisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;

		Button air_power = (Button) findViewById(R.id.air_power);
		air_power.setOnClickListener(this);
		air_power.setWidth((screenWidth) / 4);
		air_power.setHeight((screenHeight) / 10);
		air_power.setBackgroundResource(R.drawable.btn_normal);

		Button air_mode = (Button) findViewById(R.id.air_mode);
		air_mode.setOnClickListener(this);
		air_mode.setWidth((screenWidth) / 4);
		air_mode.setHeight((screenHeight) / 10);
		air_mode.setBackgroundResource(R.drawable.btn_normal);

		Button air_tempadd = (Button) findViewById(R.id.air_tempadd);
		air_tempadd.setOnClickListener(this);
		air_tempadd.setWidth((screenWidth) / 4);
		air_tempadd.setHeight((screenHeight) / 10);
		air_tempadd.setBackgroundResource(R.drawable.btn_normal);
		
		Button air_tempsub = (Button) findViewById(R.id.air_tempsub);
		air_tempsub.setOnClickListener(this);
		air_tempsub.setWidth((screenWidth) / 4);
		air_tempsub.setHeight((screenHeight) / 10);
		air_tempsub.setBackgroundResource(R.drawable.btn_normal);

		Button air_wind_auto_dir = (Button) findViewById(R.id.air_wind_auto_dir);
		air_wind_auto_dir.setOnClickListener(this);
		air_wind_auto_dir.setWidth((screenWidth) / 4);
		air_wind_auto_dir.setHeight((screenHeight) / 10);
		air_wind_auto_dir.setBackgroundResource(R.drawable.btn_normal);

		Button air_wind_count = (Button) findViewById(R.id.air_wind_count);
		air_wind_count.setOnClickListener(this);
		air_wind_count.setWidth((screenWidth) / 4);
		air_wind_count.setHeight((screenHeight) / 10);
		air_wind_count.setBackgroundResource(R.drawable.btn_normal);

		Button air_wind_dir = (Button) findViewById(R.id.air_wind_dir);
		air_wind_dir.setOnClickListener(this);
		air_wind_dir.setWidth((screenWidth) / 4);
		air_wind_dir.setHeight((screenHeight) / 10);
		air_wind_dir.setBackgroundResource(R.drawable.btn_normal);
		
		 airShow=((TextView) findViewById(R.id.text_show));
		 airShow.setTypeface(type);
		 airMode=((TextView) findViewById(R.id.text_mode));
		 airMode.setTypeface(type);
		 airWindDir=((TextView) findViewById(R.id.text_wind_dir));
		 airWindDir.setTypeface(type);
		 airWind=((TextView) findViewById(R.id.text_wind_count));
		 airWind.setTypeface(type);
		 
		 airWindAuto=((TextView) findViewById(R.id.text_wind_auto));
		 airWindAuto.setTypeface(type);
		
	}
	@Override
	public void onStart(){
		super.onStart();
		airShow.setText(getTempStr(Value.airData));
		airMode.setText(getModeStr(Value.airData));
		airWindDir.setText(getWindDirStr(Value.airData));
		airWind.setText(getWindStr(Value.airData));
		airWindAuto.setText(getWindAuto(Value.airData));
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
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
		if(Value.airData.getmPower() == 0x00 && v.getId() != R.id.air_power) {
			return;
		}
		
		int data;
		byte airKey = 0xffffffff;
		
		switch (v.getId()) {
		case R.id.air_mode:
			Value.currentKey = "air_mode";
			data= Value.airData.getmMode();
			data++;
			if(data>4){
				data = 0;
			}
			Value.airData.setmMode(data);
			airKey = 1;
			break;
		case R.id.air_power:
			Value.currentKey ="air_power";
			if(Value.airData.getmPower()==0){
				Value.airData.setmPower(1);
			}else{
				Value.airData.setmPower(0);
			}
			airKey = 0;
			break;
		case R.id.air_tempadd:
			Value.currentKey = "air_tempadd";
			data= Value.airData.getmTmp();
			data++;
			
			if(data>30){
				data = 30;
			}
			Value.airData.setmTmp(data);
			airKey = 3;
			break;
		case R.id.air_tempsub:
			Value.currentKey = "air_tempsub";
			data= Value.airData.getmTmp();
			data--;

			if(data<16){
				data = 16;
			}
			Value.airData.setmTmp(data);
			airKey =4 ;
			break;
		case R.id.air_wind_auto_dir:
			Value.currentKey = "air_wind_auto_dir";
			if(Value.airData.getmWindAuto()==0){
				Value.airData.setmWindAuto(1);
			}else{
				Value.airData.setmWindAuto(0);
			}
			airKey = 6;
			break;
		case R.id.air_wind_count:
			Value.currentKey ="air_wind_count";
			data= Value.airData.getmWindCount();
			data++;
			
			if(data>3){
				data = 0;
			}
			Value.airData.setmWindCount(data);
			airKey = 2;
			break;
		case R.id.air_wind_dir:
			Value.currentKey = "air_wind_dir";
			data=Value.airData.getmWindDir();
			data++;
			
			if(data>3){
				data = 0;
			}
			
			Value.airData.setmWindDir(data);
			Value.airData.setmWindAuto(0);
			airKey = 5;
			break;
		default:
			break;
		}
		if (Value.currentKey != null) {
			air_control(airKey);
		}
		MyRemoteDatabase.saveAirData(getApplicationContext(), Value.airData);
		airShow.setText(getTempStr(Value.airData));
		airMode.setText(getModeStr(Value.airData));
		airWindDir.setText(getWindDirStr(Value.airData));
		airWind.setText(getWindStr(Value.airData));
		airWindAuto.setText(getWindAuto(Value.airData));
	}
	
	
	
	
	public  String getModeStr(AirData airdata) {
		String mModeStr = null;
		if (airdata.getmPower() == 0x01) {
			 mModeStr = getString(R.string.air_mode_val);
			if (airdata.getmMode() == 0x00) {
				mModeStr += getString(R.string.air_mode_value_1);
			}
			if (airdata.getmMode()  == 0x01) {
				mModeStr += getString(R.string.air_mode_value_2);
			}
			if (airdata.getmMode()  == 0x02) {
				mModeStr += getString(R.string.air_mode_value_3);
			}
			if (airdata.getmMode()  == 0x03) {
				mModeStr += getString(R.string.air_mode_value_4);
			}
			if (airdata.getmMode()  == 0x04) {
				mModeStr += getString(R.string.air_mode_value_5);
			}
		}else{
			mModeStr = getString(R.string.air_power_off);	
		}

		return mModeStr;
	}
	public  String getWindStr(AirData airdata) {
		String mWindStr = "";
		mWindStr = getString(R.string.air_wind_val);
		if (airdata.getmPower() == 0x01) {
			
				if (airdata.getmWindCount() == 0x00) {
					mWindStr += getString(R.string.air_wind_count_value_1);
				}
				if (airdata.getmWindCount() == 0x01) {
					mWindStr += getString(R.string.air_wind_count_value_2);
				}
				if (airdata.getmWindCount() == 0x02) {
					mWindStr += getString(R.string.air_wind_count_value_3);
				}
				if (airdata.getmWindCount() == 0x03) {
					mWindStr += getString(R.string.air_wind_count_value_4);
				}
		}

		return mWindStr;
	}
	
	public  String getWindAuto(AirData airdata) {
		String mWindStr = null;
		mWindStr = getString(R.string.air_wind_auto_dir);
		if (airdata.getmPower() == 0x01) {
			if (airdata.getmWindAuto() == 0x00) {
				mWindStr += getString(R.string.air_wind_auto_dir_value_0);
			}else if (airdata.getmWindAuto()  == 0x01) {
				mWindStr += getString(R.string.air_wind_auto_dir_value_1);
			}
		}
	
		return mWindStr;
	}
	public  String getWindDirStr(AirData airdata) {
		String mWindStr = null;
		mWindStr = getString(R.string.air_wind_dir_val);
		if (airdata.getmPower() == 0x01) {
			if (airdata.getmWindDir() == 0x00) {
				mWindStr += getString(R.string.air_wind_dir_value_4);
			}else if (airdata.getmWindDir()  == 0x01) {
				mWindStr += getString(R.string.air_wind_dir_value_1);
			}else if (airdata.getmWindDir()  == 0x02) {
				mWindStr += getString(R.string.air_wind_dir_value_2);
			}else if (airdata.getmWindDir()  == 0x03) {
				mWindStr += getString(R.string.air_wind_dir_value_3);
			}
		}

		return mWindStr;
	}
	
	
	public  String getTempStr(AirData airdata) {
		String mTempStr = null;
		if (airdata.getmPower() == 0x01) {
			mTempStr=String.valueOf(Value.airData.getmTmp())+getResources().getString(R.string.degree);
		}else{
			mTempStr = "";	
		}
		
		return mTempStr;
	}
	

	private void air_control(final int key){
		synchronized(getApplicationContext()){
//			new Thread() {
//				DataInteractionThread dt2 = new DataInteractionThread(AirActivity.this,Utility.device_control, false);
//				public void run() {
//					MainActivity.getRmtDB().open();
//					ArrayList<String> brand_list = MainActivity.getRmtDB().getBrand("AIR");
	
//					for (int i = 0; i < brand_list.size(); i++) {
//						Log.d("abc","brand = " + brand_list.get(i));
//						if(brand_list.get(i).equals("samsung")){
//							ArrayList<String> proudcts_list = MainActivity.getRmtDB().getProductsIndex("AIR", brand_list.get(i));
//							
//							Value.airData.setCodeType(Integer.parseInt(proudcts_list.get(2)));
//							Value.airData.setMkey(key);
//							Log.d("abc","Integer.parseInt(proudcts_list.get(2) = " + Integer.parseInt(proudcts_list.get(2)) + ", key = " + Value.airData.getMkey());
//							break;
//						}
//						if(i >= brand_list.size()-1){
//							Log.d("abc","air_control error!");
//							return;
//						}
//					}
					Value.SendCodeAirData(AirActivity.this, Value.airData);
//					byte[] sendData = new byte[129];	
//					byte[] toData = RemoteCore.getAirData(Tools.airDataToArray(Value.airData));
//	
//					sendData[0] = 	0x53;
//					System.arraycopy(toData, 0, sendData, 1, toData.length);
//					
//					byte[] bcdstr = Utility.ASCTOBCD(sendData, sendData.length);
//					
//					dt2.set_remote_value("55034950524f", new String(bcdstr));
//					dt2.run();
//				}
//			}.start();
		}
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {		
	}
}