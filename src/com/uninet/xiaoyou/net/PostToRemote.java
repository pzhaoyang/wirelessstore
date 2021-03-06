package com.uninet.xiaoyou.net;

import org.json.JSONException;


public class PostToRemote {
	/**
	 * 与路由交互部分
	 */

	/**
	 * 
	 */
	public static void setDeviceName() {
		DataInteractionThread.path = "/app/setDeviceName";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
	}

	/**
	 * 设置百度云
	 * 
	 * @param username
	 * @param password
	 */
	public static void setBaiduCloud(String opened, String username, String password) {
		DataInteractionThread.path = "/app/setBaiduCloud";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("baiducloud_opened", opened);
			DataInteractionThread.postmsg.put("baiducloud_username", username);
			DataInteractionThread.postmsg.put("baiducloud_password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置USB模式
	 * 
	 * @param usb_mode
	 */
	public static void setUsbmode(String usb_mode) {
		DataInteractionThread.path = "/app/setUsbmode";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("usb_mode", usb_mode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置WIFI Ssid
	 * 
	 * @param ssid
	 *            wifi ssid
	 * @param type
	 *            密码类型
	 * @param password
	 *            wifi密码
	 */
	public static void wifiSetSSIDInfo(String ssid, String security_mode, String password) {
		DataInteractionThread.path = "/app/wifiSetSSIDInfo";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("mssid_0", ssid);
			DataInteractionThread.postmsg.put("security_mode", security_mode);
			DataInteractionThread.postmsg.put("passphrase", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置wifi穿墙模式
	 * 
	 * @param wifipower
	 */
	public static void setWifipower(String wifipower) {
		DataInteractionThread.path = "/app/setWifipower";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("wifipower", wifipower);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置wifi工作模式
	 */
	public static void setWifimode(String mode) {
		DataInteractionThread.path = "/app/setWifimode";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("wifi_mode", mode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * OTA升级
	 * 
	 * @param image_data
	 */
	public static void otaUpgrade(Object image_data) {
		DataInteractionThread.path = "/app/otaUpgrade";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
	}

	/**
	 * 
	 */
	public static void setHostName() {
		DataInteractionThread.path = "/app/setHostName";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
	}
	
	/**
	 * 切换到【宽带拨号】方式
	 */
	public static void wanSwitchToPPPOE(String pppoeUser, String pppoePass) {
		DataInteractionThread.path = "/app/wanSwitchToPPPOE";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("pppoeUser", pppoeUser);
			DataInteractionThread.postmsg.put("pppoePass", pppoePass);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 切换到【手动网关】方式
	 */
	public static void wanSwitchToStatic(String staticIp, String staticNetmask, String staticGateway, String staticPriDns, String staticSecDns) {
		DataInteractionThread.path = "/app/wanSwitchToStatic";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("staticIp", staticIp);
			DataInteractionThread.postmsg.put("staticNetmask", staticNetmask);
			DataInteractionThread.postmsg.put("staticGateway", staticGateway);
			DataInteractionThread.postmsg.put("staticPriDns", staticPriDns);
			DataInteractionThread.postmsg.put("staticSecDns", staticSecDns);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 切换到【无线中继】方式
	 */
	public static void wanSwitchToWifiSta(String apcli_ssid, String apcli_bssid, String apcli_mode, String apcli_enc, String apcli_wpapsk, String apcli_channel) {
		DataInteractionThread.path = "/app/wanSwitchToWifiSta";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("apcli_ssid", apcli_ssid);
			DataInteractionThread.postmsg.put("apcli_bssid", apcli_bssid);
			DataInteractionThread.postmsg.put("apcli_mode", apcli_mode);
			DataInteractionThread.postmsg.put("apcli_enc", apcli_enc);
			DataInteractionThread.postmsg.put("apcli_wpapsk", apcli_wpapsk);
			DataInteractionThread.postmsg.put("apcli_channel", apcli_channel);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改登陆密码
	 */
	public static void managementSetPassword(String oldpass, String admpass) {
		DataInteractionThread.path = "/app/managementSetPassword";
		DataInteractionThread.isWithAuthorization = true;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("oldpass", oldpass);
			DataInteractionThread.postmsg.put("admpass", admpass);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 与服务器交互部分
	 */
	
	/**
	 * 服务器注册
	 */
	public static void server_regist(String uname, String pword){
		DataInteractionThread.path = "/server/regist";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("uname", uname);
			DataInteractionThread.postmsg.put("pword", pword);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 服务器修改密码
	 */
	public static void server_update_password(String pword_new){
		DataInteractionThread.path = "/server/update";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("pword_new", pword_new);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询设备状态
	 */
	public static void deviceQuery(String id){
		DataInteractionThread.path = "/app/deviceQuery";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("id", id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设备打开
	 */
	public static void deviceOpen(String id){
		DataInteractionThread.path = "/app/deviceOpen";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("id", id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设备关闭
	 */
	public static void deviceClose(String id){
		DataInteractionThread.path = "/app/deviceClose";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("id", id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开始学习
	 */
	public static void deviceStart(String id){
		DataInteractionThread.path = "/app/deviceStart";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("id", id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 结束学习
	 */
	public static void deviceStop(String id){
		DataInteractionThread.path = "/app/deviceStop";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("id", id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送红外码
	 */
	public static void deviceControl(String id, String data){
		DataInteractionThread.path = "/app/deviceControl";
		DataInteractionThread.isWithAuthorization = false;
		DataInteractionThread.ispost = true;
		try {
			DataInteractionThread.postmsg.put("data", data);
			DataInteractionThread.postmsg.put("id", id);
			android.util.Log.d("abc","postmsg devicecontrol = " + DataInteractionThread.postmsg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
