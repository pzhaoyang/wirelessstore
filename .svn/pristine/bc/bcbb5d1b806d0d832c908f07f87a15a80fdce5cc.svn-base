package com.uninet.xiaoyou.net;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.uninet.xiaoyou.Utility;

/**
 * 打包JSON数据工具类
 * @author chenggong
 *
 */
public class PackJSONMessage {

	/**
	 * 构造方法
	 */
	public PackJSONMessage(){
	}
	
	/**
	 * 把数据打包成JSON格式
	 * @param which
	 * @return JSONObject
	 */
	public JSONObject packmessage(Context mContext, String username, String password, int router_id, int which) {
		JSONObject pkg = new JSONObject();
		try {
			switch (which) {
			case Utility.register:
				// 注册
				if (Utility.logable)
					Log.d(Utility.TAG, "username : " + username
							+ ", password : " + password);

				pkg.put("requestcode", Utility.register);
				pkg.put("username", username);
				pkg.put("password", password);
				pkg.put("router_id", router_id);
				//pkg.put("identifying_code", identifying_code);
				break;
			case Utility.login:
				// 登录
				if (Utility.logable)
					Log.d(Utility.TAG, "username : " + username
							+ ", password : " + password);
				
				pkg.put("requestcode", Utility.login);
				pkg.put("username", username);
				pkg.put("password", password);
				break;
			default:
				break;
			}
		} catch (JSONException e) {
			Log.e(mContext.getPackageName(), "JSONException : " + e);
		}
		if (Utility.logable)
			Log.d(Utility.TAG, "pkg : " + pkg);
		return pkg;
	}
}
