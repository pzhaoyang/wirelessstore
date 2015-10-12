package com.uninet.xiaoyou.routemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.net.DataInteractionThread;

public class MyAsyncTask extends AsyncTask<String, Void, Void> {
	@Override
	protected void onCancelled() {
		dialog.cancel();
		super.onCancelled();
	}

	private static ProgressDialog dialog = null;
	Context mContext;

	DataInteractionThread dt;

	public MyAsyncTask(Context context, DataInteractionThread _dt) {
		mContext = context;
		dt = _dt;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		dialog = new ProgressDialog(mContext);
		dialog.setMessage(mContext.getString(R.string.loading));
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	@Override
	protected Void doInBackground(String... arg0) {
		dt.run();
		android.util.Log.d("abc","run...");
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Intent i = new Intent("DT_REAULT");
		i.putExtra("JSON", dt.getJSON());
		dialogcancel();
		mContext.sendBroadcast(i);
		android.util.Log.d("abc","end...");
	}

	public static void dialogcancel() {
		if (dialog != null) {
			dialog.cancel();
		}
	}
}
