package com.uninet.xiaoyou.routemanager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.Utility;
import com.uninet.xiaoyou.net.DataInteractionThread;
import com.uninet.xiaoyou.net.ParseJSONMessage;
import com.uninet.xiaoyou.wirelessstore.GeneralUtil;

public class WirelessSettingsActivity extends Activity{

	DataInteractionThread dt;

	int security_mode = 0;
	Spinner mSpinner;
	ParseJSONMessage tmpjson  = null;
	Button bback = null;
	Button save;
	EditText input_password, show_ssid;

	private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent i) {
			tmpjson = i.getParcelableExtra("JSON");
			if(tmpjson == null){
				return;
			}
			android.util.Log.d("abc","receive = " + tmpjson.toString());
			try {
				if(("0".equals(tmpjson.getJSONValue("result")) && tmpjson.getJSONObject().has("mssid_0") && tmpjson.getJSONObject().has("security_mode"))){
					show_ssid.setText(tmpjson.getJSONValue("mssid_0"));
					security_mode = tmpjson.getJSONValue("security_mode").equals("Disable") ? 0 : 1;
					mSpinner.setSelection(security_mode);
					if(security_mode != 0){
						input_password.setText(tmpjson.getJSONValue("passphrase"));
					}
				}else if("0".equals(tmpjson.getJSONValue("result"))){
					mSpinner.setSelection(security_mode);
					if(security_mode != 0){
						input_password.setText(tmpjson.getJSONValue("passphrase"));
					}
//					WirelessSettingsActivity.this.finish();
				}else{
					show_ssid.setText("");
					mSpinner.setSelection(0);
				}
			} catch (Exception e) {
				show_ssid.setText("");
				mSpinner.setSelection(0);
				android.util.Log.d("abc","onReceive Error: " + e);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wirelesssettings);
		TextView tv = (TextView)findViewById(R.id.main_title);
		tv.setText(R.string.wireless_settings);

		show_ssid = (EditText) findViewById(R.id.show_ssid);
		mSpinner = (Spinner) findViewById(R.id.spinner_encrypt);
		input_password = (EditText) findViewById(R.id.ssid_passwd);
		save = (Button) findViewById(R.id.save);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if( true == GeneralUtil.isInvalidName(show_ssid.getText().toString().trim())
						||  show_ssid.getText().toString().trim().length() == 0 || show_ssid.getText().toString().trim().length() >32 ){
					Toast.makeText(WirelessSettingsActivity.this, R.string.ssid_error, Toast.LENGTH_SHORT).show();
					return;
				}
				if(security_mode == 0 || input_password.getText().toString().trim().length() >= 8 ){
				dt = new DataInteractionThread(WirelessSettingsActivity.this, Utility.set_ssid_info, false);
				dt.set_ssid(show_ssid.getText().toString(), security_mode==0? "Disable" : "WPAPSKWPA2PSK",
						input_password.getText().toString().trim());
				new MyAsyncTask(WirelessSettingsActivity.this, dt).execute("");
				}else{
					Toast.makeText(WirelessSettingsActivity.this, R.string.passwd_len_error, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		bback = (Button)findViewById(R.id.exit_bt);
		bback.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				WirelessSettingsActivity.this.finish();
			}
		});
		
		String[] mItems = getResources().getStringArray(R.array.encrypt);
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mItems);
		mSpinner.setAdapter(_Adapter);

		mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(arg2 == 0){
					input_password.setVisibility(View.GONE);
					input_password.setText("");
					security_mode = 0;
				}else if(arg2 == 1){
					input_password.setVisibility(View.VISIBLE);
					security_mode  = 1;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
			
		});
	}

	@Override
	protected void onResume() {
		dt = new DataInteractionThread(this, Utility.get_ssid_info, false);
		new MyAsyncTask(this, dt).execute();

		IntentFilter intentFilter = new IntentFilter("DT_REAULT");
		registerReceiver(myBroadcastReceiver, intentFilter);

		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(myBroadcastReceiver);
		super.onPause();
	}
}
