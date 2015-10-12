package com.uninet.xiaoyou.wirelessstore;

import android.app.ProgressDialog;
import android.content.Context;

public class OpProgressDlg extends ProgressDialog{

	public OpProgressDlg(Context context,String title, String message, int style, DialogButton[] dbs) {
		super(context);
		this.setTitle(title);
		this.setMessage(message);
		for(int i= 0; i< dbs.length; i++){
			this.setButton(dbs[i].whichButton,dbs[i].text,dbs[i].listener);
		}
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
		this.setMax(100);
		this.setProgressStyle(style);
		this.show();
	}
}
class DialogButton{
	int whichButton;
	CharSequence text;
	android.content.DialogInterface.OnClickListener listener;
}