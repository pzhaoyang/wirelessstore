package com.uninet.xiaoyou.remotecontrol.remote;




import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.ircomm.KeyTreate;

import android.app.Activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.TextView;


public class CameraActivity extends Activity implements OnClickListener {

	//TextView keyValueIndex;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		
		Button cam_shutter = (Button) findViewById(R.id.camera_pow);
		cam_shutter.setOnClickListener(this);
		cam_shutter.setWidth((screenWidth) / 4);
		cam_shutter.setHeight((screenHeight) / 11);
		cam_shutter.setBackgroundResource(R.drawable.btn_normal);
		
		
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
		
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.camera_pow:
			Value.currentKey = "camera_pow";
			break;
	
		default:
			Value.currentKey = null;
		}
		if (Value.currentKey != null) {
			KeyTreate.getInstance().keyTreate();
//			KeyValue kv = Value.keyValueTab.get(Value.currentKey);
//			if (kv.getIsLearned()==1){
//			keyValueIndex.setText("Learn key value     " +Value.currentKey);
//			keyValueIndex.setTextColor(Color.RED);
//			}
//			else {
//				keyValueIndex.setText("normal key value     " +Value.currentKey);
//				keyValueIndex.setTextColor(Color.BLUE);	
//			}
			
		}
	}
}
