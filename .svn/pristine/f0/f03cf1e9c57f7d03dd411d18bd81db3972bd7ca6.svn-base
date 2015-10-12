package com.uninet.xiaoyou.remotecontrol.ircomm;

import android.content.Context;
import android.os.Handler;
import android.util.Log;




import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.AirData;
import com.uninet.xiaoyou.remotecontrol.data.RemoteData;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.database.IRDataBase;

public class KeyTreate {
	private final static String TAG ="KeyTreate";
	private Handler mHandler;
	private Context mContext;
	private static int index=0;
	private static KeyTreate mKeyTreate = null;
	final static String	_ET4003_CONTROL_SEND_CODE_2_=		"55";
		
	public void setHandler(Handler _handler) {
		mHandler = _handler;
	}
	
	
	
	public static KeyTreate getInstance() {
		if (mKeyTreate == null) {
			mKeyTreate = new KeyTreate();
		}
		return mKeyTreate;
	}
	public void setContext(Context _context) {
		mContext = _context;
	}
	public  void keyTreate(){
		
		try {
		
			if (Value.isStudying){
				String learnOrigin = RemoteComm.readLearnData();
				if ("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".equals(learnOrigin)||
					"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff".equals(learnOrigin)	){
					mHandler.obtainMessage(R.id.MSG_LEARN_ERR, 1, -1)
					.sendToTarget();	
				}else {
			String learnData =  _ET4003_CONTROL_SEND_CODE_2_ +learnOrigin;
			Value.keyRemoteTab.put(Value.currentKey, learnData);
		
			mHandler.obtainMessage(R.id.MSG_LEARN_END, 1, -1)
			.sendToTarget();
				}
		//	storeStudyValue();
			}
			else{
				
			String sendData =Value.keyRemoteTab.get(Value.currentKey);
			//Log.v(TAG, sendData);
			Log.d("cgcgcg","sendData = " + sendData);
	//		RemoteData rmtData = IRDataBase.getSampleKeyData(0, "200");
	//		String sendData = RemoteComm.encodeRemoteData(rmtData);
			RemoteComm.ETEKSendRemote(sendData);	
			
		//	remoteSendUI();
			}
			//		RemoteComm.sendRemote(rmtData,codeType);
		//	RemoteComm.sendRemote();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void airKeyTreate(AirData airData){
		try {
			
			RemoteComm.sendAirRemote(airData);	
			remoteSendUI();
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	private synchronized void remoteSendUI() {
		
		if (mHandler == null)
			return;
		mHandler.obtainMessage(R.id.MESSAGE_SEND, 1, -1)
				.sendToTarget();
	}
	


}
