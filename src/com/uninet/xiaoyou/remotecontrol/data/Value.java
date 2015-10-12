package com.uninet.xiaoyou.remotecontrol.data;

import java.util.HashMap;
import java.util.Locale;

import com.general.ircore.RemoteCore;
import com.uninet.xiaoyou.Utility;
import com.uninet.xiaoyou.net.DataInteractionThread;
import com.uninet.xiaoyou.remotecontrol.utils.Tools;

import android.content.Context;

public final class Value {
	public static int HEADER = 0x99;
	public final static String[] RemoteType ={ 
		"AIR","TV","DVD","FAN","PJT","STB","CAM"
	};
	
	public static Context mAppContext;

	public static final String USERTAB = "user_tab";
	public static final String USER_ID = "_id";
	public static final String USER_NAME = "name";
	public static final String USER_DATA = "data";

	
	
	
	public static String tv_index;
	public static String dvd_index ;
	public static String stb_index;
	public static String fan_index ;
	public static String pjt_index ;
	public static String air_index ;
	public static String light_index ;
	public static String cam_index ;
	public static Boolean initial;
	public static Boolean isStudying = false;
	public static String currentKey = null;
	public static int currentDev = 0;
	public static int currentType = 0;
	public static String localLanguage = Locale.getDefault().getLanguage();
	public static AirData airData= new AirData(5001, 1, 24, 1, 0, 0, 0);
	public static int terminal;
	

	public static HashMap<String, String> keyRemoteTab ;
	
	public static class DeviceType {
		public final static int TYPE_AIR		= 0x00;
		public final static int TYPE_TV   	= 0x01;
		public final static int TYPE_DVD 	= 0x02;
		public final static int TYPE_FAN 	= 0x03;
		public final static int TYPE_PJT 	= 0x04;
		public final static int TYPE_STB 	= 0x05;
		public final static int TYPE_CAM 	= 0x06;
	}
	
	public static void SendCodeAirData(Context context, AirData airdata){
		DataInteractionThread dt2 = new DataInteractionThread(context, Utility.device_control, false);
		byte[] sendData = new byte[129];	
		byte[] toData = RemoteCore.getAirData(Tools.airDataToArray(airdata));

		sendData[0] = 	0x53;
		System.arraycopy(toData, 0, sendData, 1, toData.length);
		
		byte[] bcdstr = Utility.ASCTOBCD(sendData, sendData.length);
		
		dt2.set_remote_value("55034950524f", new String(bcdstr));
		dt2.run();
	}
	
}
