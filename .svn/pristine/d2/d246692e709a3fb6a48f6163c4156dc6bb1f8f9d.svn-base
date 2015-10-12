package com.uninet.xiaoyou.remotecontrol.data;


import android.content.Context;
import android.util.Log;


import com.uninet.xiaoyou.remotecontrol.database.IRDataBase;
import com.uninet.xiaoyou.remotecontrol.utils.RemoteDB;


public class KeyToRemote {
	private static RemoteDB mRmtDB = null;
	 final static String  TAG = "KeyToRemote";	

	
	public static void keyTabSetValue(int dType,Context mContext){
	
	  
		String[] codeDatas = new String[100];
		int keyColumn;
		if (mRmtDB==null){
			mRmtDB = new RemoteDB(mContext);	
			}
		mRmtDB.open();
		switch (dType){
		case Value.DeviceType.TYPE_TV:
			codeDatas=IRDataBase.getRemoteData(dType, Value.tv_index);

			String[] tv_keys = mRmtDB.getKeyValueTAB(dType);
			for (String key:tv_keys){
				keyColumn= mRmtDB.getKeyColumn(key);

				Value.keyRemoteTab.put(key, codeDatas[keyColumn]);
			}
			break;
		case Value.DeviceType.TYPE_STB:
			codeDatas=IRDataBase.getRemoteData(dType, Value.stb_index);
			String[] stb_key = mRmtDB.getKeyValueTAB(dType);
			for (String key:stb_key){
				keyColumn= mRmtDB.getKeyColumn(key);
				Value.keyRemoteTab.put(key, codeDatas[keyColumn]);
			}
			break;	
		case Value.DeviceType.TYPE_DVD:
			codeDatas=IRDataBase.getRemoteData(dType, Value.dvd_index);
			String[] dvd_key = mRmtDB.getKeyValueTAB(dType);
			for (String key:dvd_key){
				keyColumn= mRmtDB.getKeyColumn(key);
				Value.keyRemoteTab.put(key, codeDatas[keyColumn]);
			}
			break;	
		case Value.DeviceType.TYPE_FAN:
			codeDatas=IRDataBase.getRemoteData(dType, Value.fan_index);
			String[] fan_key = mRmtDB.getKeyValueTAB(dType);
			for (String key:fan_key){
				keyColumn= mRmtDB.getKeyColumn(key);
				
				Value.keyRemoteTab.put(key, codeDatas[keyColumn]);
			//	Log.v(TAG, "column data  ------>"+codeDatas[keyColumn]+ "column   ------>"+ +keyColumn);
			}
			break;	
		case Value.DeviceType.TYPE_PJT:
			codeDatas=IRDataBase.getRemoteData(dType, Value.pjt_index);
			String[] pjt_key = mRmtDB.getKeyValueTAB(dType);
			for (String key:pjt_key){
				keyColumn= mRmtDB.getKeyColumn(key);
				Value.keyRemoteTab.put(key, codeDatas[keyColumn]);
			}
			break;	
		case Value.DeviceType.TYPE_CAM:
			codeDatas=IRDataBase.getRemoteData(dType, Value.cam_index);
			String[] cam_key = mRmtDB.getKeyValueTAB(dType);
			for (String key:cam_key){
				keyColumn= mRmtDB.getKeyColumn(key);
				Value.keyRemoteTab.put(key, codeDatas[keyColumn]);
			}
			break;	
		default:
			break;
		}
		
		mRmtDB.close();

	}
	
	
	
	

}
