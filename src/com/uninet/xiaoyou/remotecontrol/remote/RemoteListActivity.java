package com.uninet.xiaoyou.remotecontrol.remote;

import java.util.ArrayList;
import java.util.regex.Pattern;




import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.AirData;
import com.uninet.xiaoyou.remotecontrol.data.KeyToRemote;
import com.uninet.xiaoyou.remotecontrol.data.RemoteData;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.ui.widget.DropDown;
import com.uninet.xiaoyou.remotecontrol.utils.MyRemoteDatabase;
import com.uninet.xiaoyou.remotecontrol.utils.RemoteDB;
import com.uninet.xiaoyou.remotecontrol.utils.UserDB;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;

import android.widget.Button;

import android.widget.TextView;

public class RemoteListActivity extends Activity implements OnClickListener {
	final static String TAG = "RemoteListActivity";
	private static int mCount = 0;
	private static  int mCutCount = 0;
	private AirData ad;

	private TextView mBrandName = null;


	private static  TextView mCurrentCount = null;

	private int mType = 0;
	private String mTypeName = null;
	


	private ArrayList<String> productList = new ArrayList<String>();
	private ArrayList<String> productName = new ArrayList<String>();
	private RemoteDB mRmtDB;
	private UserDB mUsertDB;
	RemoteData rmtData = new RemoteData();
	ImageButton saveButton;
	ImageButton upButton;
	ImageButton downButton;
	
	Button testBT1;
	Button testBT2;
	Button testBT3;
	Button testBT4;
	static DropDown remoteDropDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.remote_list);
		ad = new AirData(5003, 1, 24, 1, 1, 1, 0);
		// database instruction
		mRmtDB = new RemoteDB(getApplicationContext());
		mUsertDB = new UserDB(getApplicationContext());
		mType = Value.currentType;
		mTypeName = Value.RemoteType[mType];
		Intent i = getIntent();
		
		String brand = i.getStringExtra("brand");
		 Log.v(TAG, brand);
		if ("zh".equals( Value.localLanguage)){
		brand = brand.substring(0,brand.length()-1);
		}
		 Log.v(TAG, Value.localLanguage);
	    Log.v(TAG, brand);


		//
	    mBrandName = (TextView) findViewById(R.id.brand_name_index);
	    mBrandName.setText(brand);
		
		mCurrentCount = (TextView) findViewById(R.id.index_count);
		
		saveButton = (ImageButton) findViewById(R.id.str_save);
		saveButton.setOnClickListener(this);
		upButton = (ImageButton) findViewById(R.id.str_up);
		upButton.setOnClickListener(this);
		
		downButton = (ImageButton) findViewById(R.id.str_down);
		downButton.setOnClickListener(this);
		
		getProducts(mTypeName, brand);
		productName = reWriteStr(productName);
		String[] remoteListSTr = new String[productName.size()];
		productName.toArray(remoteListSTr);
		
		initTestButton();

		remoteDropDown = (DropDown) findViewById(R.id.main_cities_dropDown);
	     mCutCount = 0;
		// Set the items of the dropdown
		remoteDropDown.setItems(remoteListSTr);
		remoteDropDown.setSelection(mCutCount);
		updataCountIndex(mCutCount);
		
	}
	void initTestButton(){
		DisplayMetrics dm = new DisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		 testBT1 = (Button) findViewById(R.id.test_bt1);
		 testBT2 = (Button) findViewById(R.id.test_bt2);
		 testBT3 = (Button) findViewById(R.id.test_bt3);
		 testBT4 = (Button) findViewById(R.id.test_bt4);
		 testBT1.setWidth((screenWidth) / 4);
		 testBT1.setHeight((screenHeight) / 10);
		 testBT1.setOnClickListener(this);
		 testBT2.setWidth((screenWidth) / 4);
		 testBT2.setHeight((screenHeight) / 10);
		 testBT2.setOnClickListener(this);
		 testBT3.setWidth((screenWidth) / 4);
		 testBT3.setHeight((screenHeight) / 10);
		 testBT3.setOnClickListener(this);
		 testBT4.setWidth((screenWidth) / 4);
		 testBT4.setHeight((screenHeight) / 10);
		 testBT4.setOnClickListener(this);
		 switch (mType) {
			case Value.DeviceType.TYPE_TV:
				testBT1.setText(R.string.tv_power);
				testBT2.setText(R.string.tv_mute);
				testBT3.setText(R.string.tv_voladd);
				testBT4.setText(R.string.tv_menu);
				
				break;
			case Value.DeviceType.TYPE_DVD:
				testBT1.setText(R.string.dvd_power);
				testBT2.setText(R.string.dvd_play);
				testBT3.setText(R.string.dvd_stop);
				testBT4.setText(R.string.dvd_pause);
			
				break;
			case Value.DeviceType.TYPE_STB:
				testBT1.setText(R.string.stb_wait);
				testBT2.setText(R.string.stb_watch);
				testBT3.setText(R.string.stb_voladd);
				testBT4.setText(R.string.stb_menu);
			
				break;
			case Value.DeviceType.TYPE_PJT:
				testBT1.setText(R.string.pjt_power_on);
				testBT2.setText(R.string.pjt_power_off);
				testBT3.setText(R.string.pjt_voladd);
				testBT4.setText(R.string.pjt_menu);
				
				break;
			case Value.DeviceType.TYPE_FAN:
				testBT1.setText(R.string.fan_power);
				testBT2.setVisibility(Button.INVISIBLE);
				testBT3.setVisibility(Button.INVISIBLE);
				testBT4.setVisibility(Button.INVISIBLE);
				break;
			case Value.DeviceType.TYPE_AIR:
				testBT1.setText(R.string.pjt_power_on);
				testBT2.setText(R.string.pjt_power_off);

				testBT3.setVisibility(Button.INVISIBLE);
				testBT4.setVisibility(Button.INVISIBLE);
				break;
			case Value.DeviceType.TYPE_CAM:
				testBT1.setText(R.string.camera_shutter);
				testBT2.setVisibility(Button.GONE);

				testBT3.setVisibility(Button.GONE);
				testBT4.setVisibility(Button.GONE);
				break;
			default:
				break;
			}
	
	}
	private void getProducts(String _type, String _brand) {
		String brandName = null;
		mRmtDB.open();
		try {
			brandName = mRmtDB.getBrandOriginal(_brand);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			productName = mRmtDB.getProducts(_type, brandName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			productList = mRmtDB.getProductsIndex(_type, brandName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mRmtDB.close();
		mCount = productList.size();
	}

	private final Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case R.id.MESSAGE_WRITE:
				break;
			}
		}
	};

	void sendTestCode(int count,int keyColumn) {
		String index = productList.get(count);
	
		if (mType != Value.DeviceType.TYPE_AIR) {
			Log.d("abc","not Air to add testcode at here.");
		} else {
			int id = Integer.parseInt(index);
			ad.setCodeType(id);
			if (keyColumn==2){
				ad.setmPower(0);	
			} else {
				ad.setmPower(1);		
			}
			Value.SendCodeAirData(RemoteListActivity.this, ad);
			Log.d("abc","add air test code.");
		}
	}

	void save(int count) {
		String index = productList.get(count);
		switch (mType) {
		case Value.DeviceType.TYPE_TV:
			Value.tv_index = index;
			break;
		case Value.DeviceType.TYPE_DVD:
			Value.dvd_index = index;
			break;
		case Value.DeviceType.TYPE_STB:
			Value.stb_index = index;
			break;
		case Value.DeviceType.TYPE_PJT:
			Value.pjt_index = index;
			break;
		case Value.DeviceType.TYPE_FAN:
			Value.fan_index = index;
			break;
		case Value.DeviceType.TYPE_CAM:
			Value.cam_index = index;
			break;
		case Value.DeviceType.TYPE_AIR:
			Value.air_index = index;
			int id = Integer.parseInt(index);
			// Log.v(TAG, "id ---------->"+ id);
			ad.setCodeType(id);
			MyRemoteDatabase.saveAirData(getApplicationContext(), ad);
			Value.airData = ad;
			break;
		default:
			break;
		}
		MyRemoteDatabase.saveRemoteIndex(getApplicationContext());
		KeyToRemote.keyTabSetValue(mType, getApplicationContext());
		mUsertDB.open();
		mUsertDB.saveAllKeyTabValue();
		mUsertDB.close();
	}

	@Override
	public void onStart() {
		super.onStart();
		// mRmtDB.open();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mRmtDB.close();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.str_save:
			save(mCutCount);
			finish();
			break;
		case R.id.str_up:
			mCutCount++;
			if (mCutCount > mCount - 1) {
				mCutCount = 0;
			}
			sendTestCode(mCutCount,pressKey(0));
			updataCountIndex(mCutCount);
			break;
		case R.id.str_down:
			mCutCount--;
			if (mCutCount < 0) {
				mCutCount = mCount - 1;
			}
			sendTestCode(mCutCount,pressKey(0));
			updataCountIndex(mCutCount);
			break;
		case R.id.test_bt1:
			sendTestCode(mCutCount,pressKey(1));
			break;
		case R.id.test_bt2:
			sendTestCode(mCutCount,pressKey(2));
			break;
		case R.id.test_bt3:
			sendTestCode(mCutCount,pressKey(3));
			break;
		case R.id.test_bt4:
			sendTestCode(mCutCount,pressKey(4));
			break;
		}
	}
	
	public  int pressKey(int press){ 
		int keyColumn = 0;
		switch (mType) {
		case Value.DeviceType.TYPE_TV:
			if (press==0){
				keyColumn = 10;
			}else if(press==1){
				keyColumn = 	4;
			}else if(press==2){
				keyColumn = 	12;
			}else if(press==3){
				keyColumn = 	10;
			}else if(press==4){
				keyColumn = 	5;
			}
			break;
			
		case Value.DeviceType.TYPE_DVD:
			if (press==0){
				keyColumn = 7;
				}else if(press==1){
					keyColumn = 	4;
				}else if(press==2){
					keyColumn = 	7;
				}else if(press==3){
					keyColumn = 	10;
				}else if(press==4){
					keyColumn = 	13;
				}
			break;
		case Value.DeviceType.TYPE_STB:
			if (press==0){
				keyColumn = 8;
				}else if(press==1){
					keyColumn = 	4;
				}else if(press==2){
					keyColumn = 	28;
				}else if(press==3){
					keyColumn = 	8;
				}else if(press==4){
					keyColumn = 	5;
				}
			break;
			
		case Value.DeviceType.TYPE_PJT:
			if (press==0){
				keyColumn = 4;
				}else if(press==1){
					keyColumn = 	4;
				}else if(press==2){
					keyColumn = 	5;
				}else if(press==3){
					keyColumn = 	20;
				}else if(press==4){
					keyColumn = 	13;
				}
			break;
		case Value.DeviceType.TYPE_FAN:
			if (press==0){
				keyColumn = 4;
				}else if(press==1){
					keyColumn = 	4;
				}else if(press==2){
					keyColumn = 	4;
				}else if(press==3){
					keyColumn = 	4;
				}else if(press==4){
					keyColumn = 	4;
				}
			break;
		case Value.DeviceType.TYPE_AIR:
			if (press==0){
				keyColumn = 0;
				}else if(press==1){
					keyColumn = 	1;
				}else if(press==2){
					keyColumn = 	2;
				}else if(press==3){
					keyColumn = 	3;
				}else if(press==4){
					keyColumn = 	4;
				}
			break;
		case Value.DeviceType.TYPE_CAM:
			if (press==0){
				keyColumn = 4;
				}else if(press==1){
					keyColumn = 	4;
				}else if(press==2){
					keyColumn = 	4;
				}else if(press==3){
					keyColumn = 	4;
				}else if(press==4){
					keyColumn = 	4;
				}
			break;
		default:
			break;
		}
		Log.v(TAG, "keyColumn -------->" + keyColumn);
	    return keyColumn;
	 } 
	
	public static void updataCountIndex(int mCurCount){ 
		mCurrentCount.setText("    (" + String.valueOf(mCurCount + 1) + "/" + String.valueOf(mCount)
				+ ")     ");
		remoteDropDown.setSelection(mCurCount);
		mCutCount = mCurCount;
	    return ;
	 } 
	public static ArrayList<String> reWriteStr(ArrayList<String> str){ 
		ArrayList<String> newStr = new ArrayList<String>();
		int count = str.size();
		for (int i=0;i<count;i++){
			String tempStr = str.get(i);
			if (isNumeric(tempStr)){
				if (Integer.valueOf(tempStr)<250){
					 tempStr = Value.mAppContext.getResources().getString(R.string.general) + tempStr;
				}
			}
			newStr.add(tempStr);
		}
	    return newStr;
	 } 
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
}
