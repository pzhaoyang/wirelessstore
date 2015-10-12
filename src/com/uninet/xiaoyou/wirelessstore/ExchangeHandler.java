package com.uninet.xiaoyou.wirelessstore;

import com.uninet.xiaoyou.GeneralHandler;
import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.SyncLock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


public class ExchangeHandler extends Handler{
	
	final static int MSG_SHOW_PROGRESS_DLG = 1000;
	final static int MSG_SET_PROGRESS = 1001;
	final static int MSG_DISMISS_PROGRESS_DLG = 1002;
	final static int MSG_SHOW_RETRY_DLG = 1003;
	final static int MSG_SHOW_TOAST = 1004;

	private static ExchangeHandler eh;
	private static SyncLock sl = new SyncLock(1);
	
	volatile OpProgressDlg pdialog = null;
	
	public static ExchangeHandler GetExchangeHandlerInstance(){
		if(eh == null){
			eh = new ExchangeHandler();
		}
		return eh;
	}
	
	
	@Override
	public void handleMessage(Message msg) {
		switch(msg.what){
		case MSG_SHOW_PROGRESS_DLG:
			pdialog = new OpProgressDlg(SmbListActivity.getContext(), SmbListActivity.getContext().getString(R.string.task_status), (String)msg.obj, msg.arg1, SetProgressButton());
			break;
		case MSG_SET_PROGRESS:
			if(pdialog != null){
				pdialog.setProgress((Integer)msg.obj);
			}
			break;
		case MSG_DISMISS_PROGRESS_DLG:
			if(pdialog != null){
				SmbOpApi.cancel_fail_dlg = false;
				pdialog.dismiss();
				pdialog = null;
			}
			break;
		case MSG_SHOW_RETRY_DLG:
        	new AlertDialog.Builder(SmbListActivity.getContext())
        	.setTitle(SmbListActivity.getContext().getString(R.string.taskfailed))
            .setPositiveButton(SmbListActivity.getContext().getString(R.string.retry),
                        new OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                    int which) {
                            	sl.goon();
                            }
                        })
            .setNegativeButton(SmbListActivity.getContext().getString(android.R.string.cancel),
                    new OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	SmbOpApi.ResetFlgs();
                        	sl.goon();
                        }
                    }).create().show();
			 break;
		case MSG_SHOW_TOAST:
			Toast.makeText(SmbListActivity.getContext(), (String)msg.obj, Toast.LENGTH_SHORT).show();
			break;
		default:
			Log.d(SmbOpApi.TAG,"InVaild msg...");
			break;
		}
		super.handleMessage(msg);
	}
	
	private DialogButton[] SetProgressButton(){//final Runnable r){

		DialogButton[] db = new DialogButton[1];
		for(int i = 0; i<db.length; i++){
			db[i] = new DialogButton();
		}
		
		db[0].whichButton = DialogInterface.BUTTON_NEGATIVE;
		db[0].text = SmbListActivity.getContext().getString(android.R.string.cancel);
		db[0].listener = new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(pdialog != null){
					pdialog.dismiss();
				}
		        SmbOpApi.stop_thread = true;
		        SmbOpApi.cancel_fail_dlg = true;
			}
		};
		return db;
	}
	void showProgressDialog(Object message,int style){
		sendMessage(GeneralHandler.MsgElement(ExchangeHandler.MSG_SHOW_PROGRESS_DLG,message,style));
	}
	
	SyncLock getSynclock(){
		return sl;
	} 
}