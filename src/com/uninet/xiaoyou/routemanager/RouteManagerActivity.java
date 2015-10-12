package com.uninet.xiaoyou.routemanager;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.Utility;
import com.uninet.xiaoyou.net.DataInteractionThread;
import com.uninet.xiaoyou.net.ParseJSONMessage;

/**
 * 查看设备信息
 * 
 * @author chenggong
 * 
 */
public class RouteManagerActivity extends ListActivity {
	final private int WIRESSLESS_SETTING_ID = 100;
	final private int USBMODE_SETTING_ID = 101;
	
	private RouterSettingListAdapter rla = null;
	private ArrayList<RouteItem> alist = new ArrayList<RouteItem>();
	private DataInteractionThread dt = null;
	private int usbmode = -1;
	private ParseJSONMessage tmpjson  = null;
	private Button bback = null;

	
	private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent i) {
			tmpjson = i.getParcelableExtra("JSON");
			if(tmpjson == null){
				return;
			}
			android.util.Log.d("abc","receive = " + tmpjson.toString());
			try {
				if(("0".equals(tmpjson.getJSONValue("result")) && tmpjson.getJSONObject().has("usb_mode"))){
					InitListItems(CurrUsbMode().equals("1") ? getString(R.string.udisk) : getString(R.string.charging));
					usbmode = CurrUsbMode().equals("1") ? 1 : 0;
				}else if("0".equals(tmpjson.getJSONValue("result"))){
					InitListItems(usbmode == -1 ?  "" :  usbmode == 1 ? getString(R.string.udisk) : getString(R.string.charging));
				}else{
					usbmode = -1;
					InitListItems("");
				}
			} catch (Exception e) {
				usbmode = -1;
				InitListItems("");
				android.util.Log.d("abc","onReceive Error: " + e);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routesetting_list);
		TextView tv = (TextView)findViewById(R.id.main_title);
		tv.setText(R.string.route_manager);
		bback = (Button)findViewById(R.id.exit_bt);
		bback.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				RouteManagerActivity.this.finish();
			}
			
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter("DT_REAULT");
		registerReceiver(myBroadcastReceiver, intentFilter);
		
		setAdapter();
		
		InitListItems("");
		
		dt = new DataInteractionThread(this, Utility.get_usb_mode, false);
		new MyAsyncTask(this, dt).execute();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(myBroadcastReceiver);
		super.onPause();
	}

	void setAdapter(){
		rla  = new RouterSettingListAdapter(RouteManagerActivity.this);
		rla.setListItems(alist);
		setListAdapter(rla);
	}
	void addItemtoList(int id, int icon, String title, String info){
		alist.add(new RouteItem(
				id,
				getResources().getDrawable(icon),
				title,
				info));
	}
	
	void InitListItems(String summary){
		alist.clear();
		addItemtoList(WIRESSLESS_SETTING_ID, R.drawable.wireless_setting, getString(R.string.wireless_settings), "");
		addItemtoList(USBMODE_SETTING_ID,R.drawable.usb_mode, getString(R.string.usb_mode), summary);
		rla.notifyDataSetChanged();
		}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		RouteItem it = alist.get(position);
		switch(it.getId()){
		case WIRESSLESS_SETTING_ID:
			this.startActivity(new Intent(this, WirelessSettingsActivity.class));
			break;
		case USBMODE_SETTING_ID:
			usbmode_setting_dlg();
			break;
		}
	}
	
	void usbmode_setting_dlg(){
		AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setTitle(R.string.usb_mode);
        builder.setSingleChoiceItems(R.array.usbmode_list,  usbmode, new OnClickListener(){
            public void onClick(android.content.DialogInterface dialog, int which) {
                usbmode = which;
                android.util.Log.d("abc","current check = " +usbmode);
            }
        });
        builder.setPositiveButton(android.R.string.ok, new android.content.DialogInterface.OnClickListener(){
            public void onClick(android.content.DialogInterface dialog, int which) {
            	dt = new DataInteractionThread(RouteManagerActivity.this, Utility.set_usb_mode, false);
        		dt.set_usb_mode(usbmode == 1 ? "1" : "0");
        		new MyAsyncTask(RouteManagerActivity.this, dt).execute();
        		android.util.Log.d("abc","click...");
        	}
        });
        builder.show();
	}
	
	String CurrUsbMode() throws Exception{
		return tmpjson.getJSONValue("usb_mode");
	}
}
