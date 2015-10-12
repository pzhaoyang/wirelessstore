package com.uninet.xiaoyou;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * 进度对话框工具类
 * 
 * @author chenggong
 * 
 */
public class ShowProgressDialog extends ProgressDialog implements DialogInterface.OnClickListener {

	private static ShowProgressDialog spd;
	private static Context mContext;

	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	public ShowProgressDialog(Context context) {
		super(context);
		mContext = context;
	}
	
	public static ShowProgressDialog GetProgressDialogInstance(Context contex){
		if(null ==spd){
			mContext = contex;
			spd = new ShowProgressDialog(contex);
		}

		return spd;
	}

	/**
	 * 设置对话框布局
	 * 
	 * @param which
	 *            Utility工具类中定义
	 */
	@SuppressWarnings("deprecation")
	public void setDialogInfo(int which) {
		// 该对话框仅能通过取消按钮取消
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		setButton(mContext.getString(R.string.cancel), this);

		switch (which) {
		case Utility.register:
			// 注册中
			setTitle(R.string.register);
			setMessage(mContext.getString(R.string.completing_register));
			break;
		case Utility.login:
			// 登录中
			setTitle(R.string.login);
			setMessage(mContext.getString(R.string.completing_login));
			break;
		default:
			break;
		}

	}

//	/**
//	 * 显示对话框接口方法
//	 */
//	public void show() {
//		show();
//		// 系统自带布局字体太小，进行调整，字体设为20sp
//		setDialogFontSize(this, 20);
//	}


	/**
	 * 获取指定对话框中的布局，并修改字体大小
	 * 
	 * @param dialog
	 *            指定对话框对象
	 * @param size
	 *            指定字体大小
	 */
	private void setDialogFontSize(Dialog dialog, int size) {
		// 获取目标对话框布局
		Window window = dialog.getWindow();
		View view = window.getDecorView();
		// 设置字体大小
		setViewFontSize(view, size);
	}

	/**
	 * 遍历目标布局，获取所有TextView并修改字体大小
	 * 
	 * @param view
	 *            指定布局对象
	 * @param size
	 *            指定字体大小
	 */
	private void setViewFontSize(View view, int size) {
		if (view instanceof ViewGroup) {
			// 如果对象是ViewGroup，遍历该对象
			ViewGroup parent = (ViewGroup) view;
			int count = parent.getChildCount();
			for (int i = 0; i < count; i++) {
				// 递归，判断子布局是否符合条件
				setViewFontSize(parent.getChildAt(i), size);
			}
		} else if (view instanceof TextView) {
			// 如果对象是TextView，设置字体大小
			TextView textview = (TextView) view;
			textview.setTextSize(size);
		}
	}

	public void Dismiss(){
		if(spd != null){
			dismiss();
			spd = null;
		}
	}
	/**
	 * 按钮响应事件
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		Dismiss();
	}

}
