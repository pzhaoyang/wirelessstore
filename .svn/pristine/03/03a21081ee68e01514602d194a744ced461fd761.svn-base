package com.uninet.xiaoyou.remotecontrol.remote;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.database.IRDataBase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

public class StartActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 1000;// Integer.valueOf(this.getString(R.string.splash_delay_value));

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent mainIntent = new Intent(StartActivity.this,
						MainActivity.class);
				startActivity(mainIntent);
				finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}
