package com.uninet.xiaoyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uninet.xiaoyou.net.DataInteractionThread;

//import com.uninet.xiaoyou.net.LoginRegisterThread;

/**
 * 注册界面
 * 
 * @author chenggong
 * 
 */
public class RegisterActivity extends Activity implements OnClickListener {

	EditText input_username, input_password, confirm_password;
	// EditText input_router_id/*, input_identifying_code*/;

	Button submit, back;

	TextView information;

	String username, password;

	// int router_id;/*, identifying_code;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		// 设置界面布局
		input_username = (EditText) findViewById(R.id.username);
		input_password = (EditText) findViewById(R.id.password);
		confirm_password = (EditText) findViewById(R.id.confirm_password);
		// input_router_id = (EditText) findViewById(R.id.router_id);
		// input_identifying_code = (EditText)
		// findViewById(R.id.identifying_code);

		submit = (Button) findViewById(R.id.submit);
		back = (Button) findViewById(R.id.back);

		information = (TextView) findViewById(R.id.information);

		submit.setOnClickListener(this);
		back.setOnClickListener(this);

	}

	/**
	 * 按钮响应事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			// 用户提交数据，先检测输入数据是否合法
			if (CheckIfInputAvailiable()) {
				username = input_username.getText().toString();
				password = input_password.getText().toString();
				// router_id =
				// Integer.valueOf(input_router_id.getText().toString());
				// identifying_code =
				// input_identifying_code.getText().toString();

				// 启动登录/注册线程向服务器提交数据
				// LoginRegisterThread httpsocket = new LoginRegisterThread(
				// this, username, password, Utility.register/*
				// * , router_id,
				// * identifying_code
				// */);
				// httpsocket.start();
				DataInteractionThread dt = new DataInteractionThread(this,
						Utility.register, true);
				dt.start();

				// 显示注册中对话框
				ShowProgressDialog pdialog = new ShowProgressDialog(this);
				pdialog.setDialogInfo(Utility.register);
				pdialog.show();
			}
			break;
		case R.id.back:
			// 返回上级界面
			this.startActivity(new Intent(this, XiaoYouActivity.class));
			break;
		default:
			break;
		}

	}

	/**
	 * 判断用户输入数据是否合法
	 * 
	 * @return boolean
	 */
	private boolean CheckIfInputAvailiable() {
		int usernamelength = input_username.getText().length();
		int passwordlength = input_password.getText().length();

		if (usernamelength == 0) {
			// 是否已输入用户名
			information.setText(getString(R.string.input_username));
			return false;
		} else if (usernamelength < 6 || usernamelength > 32) {
			// 用户名长度至少为6
			information.setText(getString(R.string.username_length_warning));
			return false;
		} else if (passwordlength == 0) {
			// 是否已输入密码
			information.setText(getString(R.string.input_password));
			return false;
		} else if (passwordlength < 6 || passwordlength > 32) {
			// 密码长度必须是6-32位
			information.setText(getString(R.string.password_length_warning));
			return false;
		} else if (!(input_password.getText().toString())
				.equals(confirm_password.getText().toString())) {
			// 第二次输入的密码与第一次输入的不相同
			information
					.setText(getString(R.string.confirm_password_not_match_warning));
			return false;
		}
		// else if (input_router_id.getText().length() == 0) {
		// 是否已输入对应设备ID
		// information.setText(getString(R.string.input_router_id));
		// return false;
		// }
		else {
			return true;
		}

	}
}
