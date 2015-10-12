/**
 * remotecontrol
 */
package com.uninet.xiaoyou;

import com.uninet.xiaoyou.net.DataInteractionThread;
import com.uninet.xiaoyou.routemanager.MyAsyncTask;
import com.uninet.xiaoyou.routemanager.RouteManagerActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 主界面 登录界面
 * 
 * @author chenggong
 * 
 */
public class XiaoYouActivity extends Activity implements
		OnCheckedChangeListener, OnClickListener {

	boolean isAutoLogin = false;
	boolean isRemberPassword = false;

	EditText /* input_username, */input_password;
	CheckBox rember_password, auto_login;//, showpassword;
	Button /* register, */login;

	String soft_version;

	SharedPreferences sp;
	Editor editor;
	public static ProgressDialog pdialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiaoyou);
		
		// 后台自动检测是否有更新
		// TODO
		// UpdateManager update = new UpdateManager(this);
		// update.checkUpdate();

		// 设置界面布局
		// input_username = (EditText) findViewById(R.id.input_username);
		input_password = (EditText) findViewById(R.id.input_password);
		rember_password = (CheckBox) findViewById(R.id.rember_password);
		auto_login = (CheckBox) findViewById(R.id.auto_login);
		//showpassword = (CheckBox) findViewById(R.id.showpassword);
		// register = (Button) findViewById(R.id.register);
		login = (Button) findViewById(R.id.login);

		rember_password.setOnCheckedChangeListener(this);
		auto_login.setOnCheckedChangeListener(this);
		//showpassword.setOnCheckedChangeListener(this);
		// register.setOnClickListener(this);
		login.setOnClickListener(this);
		
		// 获取设置
		getSettings();
	}
	
	@Override
	protected void onResume() {
		pdialog = new ProgressDialog(this);
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		if(pdialog!= null){
			pdialog.dismiss();
			pdialog = null;
		}
		super.onDestroy();
	}

	/**
	 * 获取软件版本号，用以自动更新
	 * 
	 * @return int
	 */
	private int getVersionCode() {
		int versionCode = 0;
		try {
			versionCode = this.getPackageManager().getPackageInfo(
					this.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 获取配置参数，是否自动登录，是否记住密码等
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldReadableFiles")
	private void getSettings() {
		sp = getSharedPreferences("saved_login_settings", MODE_WORLD_READABLE);
		editor = sp.edit();
		// 是否记住密码
		isRemberPassword = sp.getBoolean("isRemberPassword", false);

		if (isRemberPassword) {
			// 如果已记住密码，读取之前记住的密码并自动填充
			String username = sp.getString("username", "username");
			String password = sp.getString("password", "password");
			// input_username.setText(username);
			input_password.setText(password);
			// 如果已记住密码，是否自动登录
			isAutoLogin = sp.getBoolean("isAutoLogin", false);
			if (isAutoLogin) {
				// 如果自动登录，执行登录操作
				login();
			}
		}
		// 未记住密码时，自动登录单选框不可点选
		rember_password.setChecked(isRemberPassword);
		auto_login.setChecked(sp.getBoolean("isAutoLogin", false));
		auto_login.setEnabled(isRemberPassword);
	}

	/**
	 * 执行登录操作
	 */
	private void login() {
		// String username = input_username.getText().toString();
		String password = input_password.getText().toString();

		// 显示登录中对话框
		if(pdialog != null){
			pdialog.setTitle(R.string.login);
			pdialog.setMessage(getString(R.string.loging));
			pdialog.setCanceledOnTouchOutside(false);
			pdialog.setCancelable(false);
			pdialog.setButton( DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					pdialog.dismiss();
					pdialog = null;
				}
			});
			pdialog.show();
		}
		android.util.Log.d("abc","Start login....");

		// 启动登录/注册线程，向服务器提交数据
		new DataInteractionThread(this, Utility.login, false).start();


	}

	/**
	 * 按钮响应事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.register:
		// // 跳转到注册界面
		// this.startActivity(new Intent(this, RegisterActivity.class));
		// break;
		case R.id.login:
			if (CheckIfInputAvailiable()) {
					// 保存密码
					// editor.putString("username", input_username.getText()
					// .toString());
					editor.putString("password", input_password.getText()
							.toString());
					editor.commit();
				// 执行登录操作
				login();
			}
			break;
		}
	}

	/**
	 * 判断输入是否合法
	 * 
	 * @return boolean
	 */
	private boolean CheckIfInputAvailiable() {
		// int usernamelength = input_username.getText().length();
		int passwordlength = input_password.getText().length();

		/*
		 * if (usernamelength == 0) { // 是否已输入用户名
		 * warning.setText(getString(R.string.input_username)); return false; }
		 * else if (usernamelength < 6 || usernamelength > 32) { // 用户名长度至少为3
		 * warning.setText(getString(R.string.username_length_warning)); return
		 * false; } else
		 */if (passwordlength == 0) {
			// 是否已输入密码
			 Toast.makeText(this, R.string.input_password, 0);
			//warning.setText(getString(R.string.input_password));
			return false;
		} else if (passwordlength < 5 || passwordlength > 32) {
			Toast.makeText(this, R.string.password_length_warning, 0);
			// 密码长度必须是6-18位
			//warning.setText(getString(R.string.password_length_warning));
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 单选框点击事件
	 */
	@Override
	public void onCheckedChanged(CompoundButton b, boolean ischecked) {
		switch (b.getId()) {
		case R.id.rember_password:
			// 记住密码单选框状态改变时，保存设置
			editor.putBoolean("isRemberPassword", ischecked);
			editor.commit();
			auto_login.setEnabled(ischecked);
			isRemberPassword = ischecked;
			break;
		case R.id.auto_login:
			// 自动登录单选框状态改变时，保存设置
			editor.putBoolean("isAutoLogin", ischecked);
			editor.commit();
			isAutoLogin = ischecked;
			break;
		case R.id.showpassword:
			if (ischecked) {
				input_password
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
			} else {
				input_password
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
			}
		default:
			break;
		}

	}

}
