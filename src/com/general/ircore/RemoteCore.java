package com.general.ircore;



import com.uninet.xiaoyou.remotecontrol.data.AirData;
import com.uninet.xiaoyou.remotecontrol.data.RemoteData;
import com.uninet.xiaoyou.remotecontrol.utils.Tools;

import android.util.Log;



public class RemoteCore {
	final static String	_CONTROL_SEND_CODE_1_=	"53";
	final static int	_CONTROL_SEND_CODE   =	0x53;
	
	final static String TAG = "RemoteComm";
	private static final String libSoName = "IRCore";
	static {  
        System.loadLibrary(libSoName);  
    }  

	public static void SendRemote(String remoteData){
		
		if (remoteData== null){
	
			return ;
		}
		
	
		byte[] sendData = Tools.hexStringToBytes(remoteData);
		int length = sendData.length;
		Log.v(TAG, remoteData);
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
	sendData[0] = 	_CONTROL_SEND_CODE;
	System.arraycopy(toData, 0, sendData, 1, toData.length);	
//	Log.v(TAG,airdata.getInfo());
	sendIRCode(sendData,length);

}


public static String getAirData(AirData airdata){
	if (airdata== null){
	//	Log.v(TAG, "send learn data null");
		return "";
	}
	byte[] sendData = new byte[129];
	int[] tempData = Tools.airDataToArray(airdata);

	byte[] toData = getAirData(tempData);


	int length = toData.length+1;
//	Log.v(TAG, "air data  -->"+Tools.bytesToHexString(toData));
	sendData[0] = 	_CONTROL_SEND_CODE;
	System.arraycopy(toData, 0, sendData, 1, toData.length);	
	//Log.v(TAG,airdata.getInfo());
	//sendIRCode(sendData,length);
	return Tools.bytesToHexString(sendData);

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

			return status;
			}
		else if (status==0){
			Log.v(TAG, "learn remote  device timeout " );

			return status;
			}
		else {

			 return status;
			}
		
		
	}
	
	public static String encodeRemoteData(RemoteData rmtDt){
		if ("".equals(rmtDt.getData())){
			return null;
		}
		String temp = rmtDt.getCustom()+rmtDt.getData();
		byte[] data1=Tools.hexStringToBytes(temp);
		String data2=rmtDt.getCodetype();
		byte[] encodeData=Encode ( data1,data2);
		temp = Tools.bytesToHexString(encodeData);
		temp = _CONTROL_SEND_CODE_1_ + temp;
	//	Log.v(TAG, "encode data ->" + temp);
		return temp;
	}
	
	public static String encodeRemoteData(String data,String type){
		if ("".equals(data)){
			return null;
		}
		String temp;
		byte[] data1=Tools.hexStringToBytes(data);
		
		byte[] encodeData=Encode ( data1,type);
		temp = Tools.bytesToHexString(encodeData);
		temp = _CONTROL_SEND_CODE_1_ + temp;
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
	 public native static int  CheckIR(); 
//	 public native static IRCode learnpronto();
	 public native static byte[] prontoToEtcode(int freq,char[] data);
	 
}