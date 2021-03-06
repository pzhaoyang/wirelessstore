package com.uninet.xiaoyou;

import java.io.IOException;
import java.util.ArrayList;

import com.general.ircore.RemoteCore;
import com.uninet.xiaoyou.net.DataInteractionThread;
import com.uninet.xiaoyou.remotecontrol.data.AirData;
import com.uninet.xiaoyou.remotecontrol.utils.RemoteDB;
import com.uninet.xiaoyou.remotecontrol.utils.Tools;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class remote extends Activity implements OnClickListener {

	Button pair, query, open, close, air_open,air_tem_up, air_tem_down,air_mode,air_wind_count,air_wind_auto_dir,air_wind_dir;
	static TextView pair_result;
	TextView query_result;

	static SharedPreferences sp;
	static Editor editor;

	static String pair_id;

	private RemoteDB mRmtDB;
	
	AirData air = new AirData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote);

		pair = (Button) findViewById(R.id.pair);
		query = (Button) findViewById(R.id.query);
		open = (Button) findViewById(R.id.open);
		close = (Button) findViewById(R.id.close);
		air_open = (Button) findViewById(R.id.air_open);
		air_tem_up = (Button) findViewById(R.id.air_tem_up);
		air_tem_down = (Button) findViewById(R.id.air_tem_down);
		air_mode = (Button) findViewById(R.id.air_mode);
		air_wind_count = (Button) findViewById(R.id.air_wind_count);
		air_wind_auto_dir = (Button) findViewById(R.id.air_wind_auto_dir);
		air_wind_dir = (Button) findViewById(R.id.air_wind_dir);
		

		pair_result = (TextView) findViewById(R.id.pair_result);
		query_result = (TextView) findViewById(R.id.query_result);

		pair.setOnClickListener(this);
		query.setOnClickListener(this);
		open.setOnClickListener(this);
		close.setOnClickListener(this);
		air_open.setOnClickListener(this);
		air_tem_up.setOnClickListener(this);
		air_tem_down.setOnClickListener(this);
		air_mode.setOnClickListener(this);
		air_wind_count.setOnClickListener(this);
		air_wind_auto_dir.setOnClickListener(this);
		air_wind_dir.setOnClickListener(this);
		
		
		mRmtDB = new RemoteDB(getApplicationContext());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pair:
			DataInteractionThread dt = new DataInteractionThread(remote.this,
					Utility.device_pair, false);
			dt.run();
			break;
		case R.id.air_tem_up:
			int tmp = air.getmTmp() +1;
			air.setmTmp(tmp);
			air_control(3);
			break;
		case R.id.air_tem_down:
			int tmp2 = air.getmTmp() - 1;
			air_control(4);
			break;
		case R.id.air_open:
			int pwr = air.getmPower() == 1 ? 0 : 1; 
			air.setmPower(pwr);
			air_control(0);
			break;
		case R.id.air_mode:
			int mode = air.getmMode();
			if(mode >=4){
				air.setmMode(0);
			}else{
				mode++;
				air.setmMode(mode);
			}
			
			air_control(1);
			break;
		case R.id.air_wind_count:
			int count  = air.getmWindCount();
			if(count >=3){
				air.setmWindCount(0);
			}else{
				count++;
				air.setmWindCount(count);
			}
			air_control(2);
			break;
		case R.id.air_wind_auto_dir:
			int wind_auto_dir = air.getmWindAuto() == 0 ? 1 :0;
			air.setmWindAuto(wind_auto_dir);
			air_control(6);
			break;
		case R.id.air_wind_dir:
			int wind_dir  = air.getmWindDir();
			if(wind_dir >=3){
				air.setmWindDir(0);
			}else{
				wind_dir++;
				air.setmWindDir(wind_dir);
			}
			air_control(5);
			break;
		}

	}

	public static void setPairResult(String id) {
		pair_result.setText(id);
		pair_id = id;
	}

//	public native static byte[]  getAirData (int[] data1);
	
	private void air_control(final int key){
		new Thread() {
			DataInteractionThread dt2 = new DataInteractionThread(remote.this,Utility.device_control, false);
			public void run() {				
				try {
					mRmtDB.createDataBase();
				} catch (IOException e) {
					e.printStackTrace();
				}
				mRmtDB.open();
				ArrayList<String> brand_list = mRmtDB.getBrand("AIR");

				for (int i = 0; i < brand_list.size(); i++) {
					Log.d("abc","brand = " + brand_list.get(i));
					if(brand_list.get(i).equals("samsung")){
						ArrayList<String> proudcts_list = mRmtDB
								.getProductsIndex("AIR", brand_list.get(i));
						
						air.setCodeType(Integer.parseInt(proudcts_list.get(2)));
						air.setMkey(key);
						Log.d("abc","Integer.parseInt(proudcts_list.get(2) = " + Integer.parseInt(proudcts_list.get(2)) + ", key = " + air.getMkey());
						break;
					}
					if(i >= brand_list.size()-1){
						Log.d("abc","air_control error!");
						return;
					}
				}
				

				byte[] sendData = new byte[129];
				int[] tempData = Tools.airDataToArray(air);

				byte[] toData = RemoteCore.getAirData(tempData);

				sendData[0] = 	0x53;
				System.arraycopy(toData, 0, sendData, 1, toData.length);
				
				byte[] bcdstr = Utility.ASCTOBCD(sendData, sendData.length);
				
//				dt2.set_remote_value(/*pair_id*/"55034950524f", new String(bcdstr));
				dt2.set_remote_value(/*pair_id*/"550300000001", new String(bcdstr));
				dt2.run();
			}
		}.start();
	}
}
