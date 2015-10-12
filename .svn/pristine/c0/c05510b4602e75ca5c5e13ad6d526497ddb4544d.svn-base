package com.uninet.xiaoyou.remotecontrol.ircomm;

import java.util.Timer;

import android.util.Log;


import com.uninet.xiaoyou.remotecontrol.data.AirData;
import com.uninet.xiaoyou.remotecontrol.data.RemoteData;
import com.uninet.xiaoyou.remotecontrol.utils.Tools;


public class RemoteComm {
	final static String	_ET4003_CONTROL_SEND_CODE_1_=	"53";
	final static int	_ET4003_CONTROL_SEND_CODE   =	0x53;
	
	final static String TAG = "RemoteComm";
	private static final String libSoName = "ETEKIRCore";
	static {  
        System.loadLibrary(libSoName);  
    }  
//	static final String data = "538020000000192B01015200AA001500150015003F00D302AE0152005500D5036B12222222322223332222222333333332245600";

	//static final byte[] demoData = {0x00,0x02,0x20,(byte) 0x80,0x00,0x3d,(byte) 0xbd,0x00,0x00,0x00,0x00,0x20,0x04,0x04,0x02,0x1a,0x00,0x71,0x00,(byte) 0x94,0x01,(byte) 0x93,0x00,0x00,0x00,0x00,0x58,(byte) 0xbf};

	public static void ETEKSendRemote(String remoteData){
		
		if (remoteData== null){
	
			return ;
		}
		
	
		byte[] sendData = Tools.hexStringToBytes(remoteData);
		int length = sendData.length;
		Log.d("cgcgcg", "remoteData = " + remoteData);
		Log.d("cgcgcg", "length = " + length);
		sendIRCode(sendData,length);
	}


public static void sendAirRemote(AirData airdata){
	if (airdata== null){
	//	Log.v(TAG, "send learn data null");
		return ;
	}
	byte[] sendData = new byte[129];
	int[] tempData = Tools.airDataToArray(airdata);

	byte[] toData = getAirData(tempData);


	int length = toData.length+1;
//	Log.v(TAG, "air data  -->"+Tools.bytesToHexString(toData));
	sendData[0] = 	_ET4003_CONTROL_SEND_CODE;
	System.arraycopy(toData, 0, sendData, 1, toData.length);	
	Log.v(TAG,airdata.getInfo());
	sendIRCode(sendData,length);

}

//public static String getLearnData (String data){
//		String keyDataStr;
//		keyDataStr = _ET4003_CONTROL_SEND_CODE_2_ +data;
//		return keyDataStr;		
//}



	public static int initRemote(){
	int ret=IRinit ();
	return ret;
	}
	
	public static void finishRemote(){
		Finish ();
	}
	
	
	
	public static void remoteLearnStop(){
		learnIRCodeStop ();
	}
	public static String readLearnData(){
		
		 byte[] learnData= readLearnIRCode();
		String lrnDtStr = Tools.bytesToHexString(learnData);
	//	Log.v(TAG, "learn data --->" + lrnDtStr);
		return lrnDtStr;
		
		}
	
	public static int learnRemoteLoop(int timeout){
		
		learnIRCodeStart();  
//		String lrnDtStr;
		setLearnTimeout(timeout);
	
	//	while(true){
		int status= learnIRCodeMain();
		if (status==-1){
			Log.v(TAG, "learn remote  device error " );	
//			lrnDtStr="device error";
//			remoteLearnStop();
			return status;
			}
		else if (status==0){
			Log.v(TAG, "learn remote  device timeout " );
//			lrnDtStr="time out";
//			remoteLearnStop();
			return status;
			}
		else {
//			byte[] learnData= readLearnIRCode();
//			 lrnDtStr = Tools.bytesToHexString(learnData);
//			 Log.v(TAG, "learn remote data  --->" + lrnDtStr);
			 return status;
			}
		
		
	}
	
	public static String encodeRemoteData(RemoteData rmtDt){
		if (rmtDt.getData()==""){
			return null;
		}
		String temp = rmtDt.getCustom()+rmtDt.getData();
		byte[] data1=Tools.hexStringToBytes(temp);
		String data2=rmtDt.getCodetype();
		byte[] encodeData=Encode ( data1,data2);
		temp = Tools.bytesToHexString(encodeData);
		temp = _ET4003_CONTROL_SEND_CODE_1_ + temp;
	//	Log.v(TAG, "encode data ->" + temp);
		return temp;
	}
	 
	 public native static void  sendIRCode (byte[] data, int length);  
	 public native static void  learnIRCodeStart();  
	 public native static void  learnIRCodeStop();  
	 public native static byte[]  readLearnIRCode();
	 public native static void  sendLearnCode (byte[] data);
	 public native static int  IRinit ();
	 public native static void  Finish ();
	 public native static void  setLearnTimeout (int time);
	 public native static int  learnIRCodeMain ();
	 public native static byte[]  Encode (byte[] data1,String data2);
	 public native static byte[]  getAirData (int[] data1);
	 public native static int sendIRRepeat();
	 public native static int[]  EncodeSamsung (byte[] data1,String data2);
	 public native static int[]  SendSamsungCode(int freq, int[] data,int dataLen); 
	 public native static int[]  getSamsungAirData(int[] data); 
	 
	 
	
}