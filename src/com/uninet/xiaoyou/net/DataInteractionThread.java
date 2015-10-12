package com.uninet.xiaoyou.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jcifs.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import com.uninet.xiaoyou.FunctionSelectionActivity;
import com.uninet.xiaoyou.GeneralHandler;
import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.ShowProgressDialog;
import com.uninet.xiaoyou.Utility;
import com.uninet.xiaoyou.XiaoYouActivity;
import com.uninet.xiaoyou.remote;
/**
 * 网络交互操作
 * 
 * @author chenggong
 * 
 */

 public class DataInteractionThread extends Thread {
	private HttpURLConnection conn = null;
	private static ParseJSONMessage getmsg = null;
	static JSONObject postmsg = new JSONObject();
	static String path;
	// 是否需要鉴权
	static boolean isWithAuthorization = false;
	// 是否是post消息
	static boolean ispost = false;

	private Context mContext;
	// 具体操作内容
	private int whitch;
	// 鉴权信息
	private String username = "admin";
	private String password;
	private boolean isServer = false;
	
	private String pppoe_name, pppoe_pwd;

	private String apcli_ssid, apcli_bssid, apcli_mode, apcli_enc,
			apcli_wpapsk, apcli_channel;

	private String mssid_0, security_mode, passphrase;

	private String usb_mode;

	private String remote_id, remote_data;


	private String version_out = null;
	
	private static GeneralHandler gh = null;

	/**
	 * 空构造方法,便于调用查询方法
	 * 
	 * @param _context
	 */
	public DataInteractionThread() {
	}

	/**
	 * 构造方法,用于与远程进行交互
	 * 
	 * @param _context
	 */
	public DataInteractionThread(Context _context, int _whitch, boolean where) {
		mContext = _context;
		whitch = _whitch;
		isServer = where;

		SharedPreferences sp = mContext.getSharedPreferences(
				"saved_login_settings", 0);
		password = sp.getString("password", "password");

		gh = GeneralHandler.GetExchangeHandlerInstance(_context);
	}

	/**
	 * 获取当前连接的网关IP
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String getGateWay() {

		WifiManager wm = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		String getway = Formatter.formatIpAddress(wm.getDhcpInfo().gateway);

		return getway;
	}

	void openURL(URL url) throws Exception{
		conn = (HttpURLConnection) url.openConnection();
	}
	
	void setConnectTimeout(int timeout) throws Exception{
		if(conn == null) throw new Exception("DataInteractionException");

		conn.setConnectTimeout(timeout);
	}
	
	void setDoInput(boolean b) throws Exception{
		if(conn == null) throw new Exception("DataInteractionException");
		
		conn.setDoInput(b);
	}
	
	void setDoOutput(boolean b) throws Exception{
		if(conn == null) throw new Exception("DataInteractionException");
		
		conn.setDoOutput(b);
	}
	
	void setAllowUserInteraction(boolean b) throws Exception{
		if(conn == null) throw new Exception("DataInteractionException");
		
		conn.setAllowUserInteraction(true);
	}
	
	void setRequestProperty(String field, String newValue) throws Exception{
		if(conn == null) throw new Exception("DataInteractionException");
		
		conn.setRequestProperty(
				"Authorization",
				"Basic "
						+ Base64.encode((username + ":" + password)
								.getBytes()));
	}
	
	void setRequestMethod(String method) throws Exception{
		if(conn == null) throw new Exception("DataInteractionException");
		
		conn.setRequestMethod(method);
	}
	
	InputStream getInputStream() throws Exception{
		return conn.getInputStream();
	}
	
	void disconnect(){
		if(conn == null) return;
		
		conn.disconnect();
		conn = null;
	}
	
	void DataSend(DataOutputStream out) throws Exception{
		out.write(postmsg.toString().getBytes());
		out.flush();
		out.close();
	}
	/**
	 * 开启远程连接，连接当前网关，发送消息
	 * 
	 * @param isWithAuthorization
	 *            是否需要鉴权
	 * @param ispost
	 *            是否是post消息
	 */
	private void connect() throws Exception {
		String prefix = isServer ? "http://27.115.113.62:8080/uninet" : ("http://" + getGateWay());
		openURL(new URL(prefix + path));
		setConnectTimeout(5000);
			
		if (isWithAuthorization) {
			setDoInput(true);
			setAllowUserInteraction(true);
			setRequestProperty(
					"Authorization",
					"Basic " + Base64.encode((username + ":" + password).getBytes()));
		}
		
		if (ispost) {
			setConnectTimeout(6000);
			setDoOutput(true);
			setRequestMethod("POST");

			DataSend(GetOutPutStream());
			Log.d("abc", "postmsg =  " + postmsg);
		}
		
		Log.d("abc", "before connect : " + conn.getURL().toString());
		conn.connect();
		Log.d("abc", "getResponseCode : " + conn.getResponseCode());
		if (conn.getResponseCode() != 200) {
			 throw new Exception("DataInteractionException");
		}
		
		getInfo();
	}

	/**
	 * 获取远程返回
	 */
	private void getInfo() throws Exception{
		
		DataInputStream in = GetInputStream();
		getmsg = null;
		getmsg = getJSONfromStream(in);
		
		disconnect();
		closeStream(in);
	}

	ParseJSONMessage getJSONfromStream(DataInputStream in) throws Exception{
		return  new ParseJSONMessage(in);
	}
	
	DataInputStream GetInputStream() throws Exception{
		return new DataInputStream(getInputStream());
	}
	
	DataOutputStream GetOutPutStream() throws Exception{
		return new DataOutputStream(conn.getOutputStream());
	}
	
	void closeStream(DataInputStream ds) throws Exception{
		ds.close();
	}
	
	/**
	 * 获取远程返回的结果
	 * 
	 * @return String 0：success 1：鉴权失败 2：功能不支持 3：未知错误
	 */
	public String get_result() throws Exception{
		if(getmsg == null) throw new Exception("DataInteractionException");
		
		return getmsg.getJSONValue("result");
	}

	/**
	 * 获取百度云开启状态
	 * 
	 * @return String
	 */
	public String get_baiducloud_opened() throws Exception{		
		return getmsg.getJSONValue("baiducloud_opened");
	}

	/**
	 * 获取百度云用户名
	 * 
	 * @return String
	 */
	public String get_baiducloud_username()  throws Exception{		
		return getmsg.getJSONValue("baiducloud_username");
	}

	/**
	 * 获取百度云密码
	 * 
	 * @return String
	 */
	public String get_baiducloud_password()  throws Exception{		
		return getmsg.getJSONValue("baiducloud_password");
	}

	/**
	 * 获取USB模式
	 * 
	 * @return String
	 */
	public String get_usb_mode()  throws Exception{
		return getmsg.getJSONValue("usb_mode");
	}

	/**
	 * 获取wifi ssid
	 * 
	 * @return String
	 */
	public String get_ssid()  throws Exception{		
		return getmsg.getJSONValue("mssid_0");
	}

	/**
	 * 设置wifi ssid
	 * 
	 * @return String
	 */
	public void set_ssid(String _mssid_0, String _security_mode,
			String _passphrase) {
		mssid_0 = _mssid_0;
		security_mode = _security_mode;
		passphrase = _passphrase;
	}

	/**
	 * 获取wifi密码类型
	 * 
	 * @return String
	 */
	public String get_type()  throws Exception{				
		return getmsg.getJSONValue("security_mode");
	}

	/**
	 * 获取wifi密码
	 * 
	 * @return String
	 */
	public String get_password()  throws Exception{		
		return getmsg.getJSONValue("passphrase");
	}

	/**
	 * 获取wifi穿墙模式
	 * 
	 * @return String
	 */
	public String get_wifi_power()  throws Exception{		
		return getmsg.getJSONValue("wifi_power");
	}

	/**
	 * 获取wifi工作模式
	 * 
	 * @return String
	 */
	public String get_wifi_mode()  throws Exception{		
		return getmsg.getJSONValue("wifi_mode");
	}

	/**
	 * 获取外部版本号
	 * 
	 * @return String
	 */
	public String get_version_out() throws Exception{
		if (version_out != null) {
			return version_out;
		}
		
		return getmsg.getJSONValue("version_out");
	}

	/**
	 * 获取内部版本号
	 * 
	 * @return String
	 */
	public String get_version_in()  throws Exception{
		return getmsg.getJSONValue("version_in");
	}

	/**
	 * 获取项目信息
	 * 
	 * @return String
	 */
	public String get_product()  throws Exception{		
		return getmsg.getJSONValue("product");
	}

	/**
	 * 获取name
	 * 
	 * @return String
	 */
	public String get_name()  throws Exception{		
		return getmsg.getJSONValue("name");
	}

	/**
	 * 获取pppoe密码
	 */
	public String get_pppoe_pwd()  throws Exception{
		return getmsg.getJSONValue("pppoePass");
	}

	/**
	 * 获取pppoe用户名
	 */
	public String get_pppoe_uname()  throws Exception{		
		return getmsg.getJSONValue("pppoeUser");
	}

	/**
	 * 设置pppoe用户名密码
	 */
	public void set_pppoe_info(String name, String pwd) {
		pppoe_name = name;
		pppoe_pwd = pwd;
	}

	/**
	 * 获取wifi ssid
	 */
	public String get_wifista_ssid()  throws Exception{		
		return getmsg.getJSONValue("apcli_ssid");
	}

	/**
	 * 设置wifi sta信息
	 */
	public void set_wifista_info(String _apcli_ssid, String _apcli_wpapsk) {
		apcli_ssid = _apcli_ssid;
		apcli_wpapsk = _apcli_wpapsk;
	}

	/**
	 * 获取ap列表
	 */
	public String[] get_wifista_list() {
		String[] error = { "error" };
		try {

			JSONObject object = getmsg.getJSONObject();
			JSONArray arrary = object.names();
			if (arrary.length() < 2) {
				return error;
			}
			String[] list = new String[arrary.length()];
			for (int i = 0; i < arrary.length(); i++) {
				list[i] = arrary.getString(i);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return error;
	}

	/**
	 * 设置USB模式
	 */
	public void set_usb_mode(String mode) {
		usb_mode = mode;
	}

	/**
	 * 获取配对结果
	 */
	public String get_pair_id()  throws Exception{
		return getmsg.getJSONValue("device_result");
	}
	
	public void set_remote_value(String id, String data) {
		remote_id = id;
		remote_data = data;
	}

	@Override
	public void run() {
		Log.d("abc", "whitch : " + whitch);
		if (isServer) {
			switch (whitch) {
			case Utility.login:
				GetFromRemote.server_login();
				break;
			case Utility.register:
				PostToRemote.server_regist(username, password);
				break;
			case Utility.update_pword:
				PostToRemote.server_update_password("");
				break;
			}
		} else {
			switch (whitch) {
			case Utility.login:
				GetFromRemote.login();
				break;
			case Utility.get_pppoe_info:
				GetFromRemote.wanGetPPPOEInfo();
				break;
			case Utility.switch_to_pppoe:
				PostToRemote.wanSwitchToPPPOE(pppoe_name, pppoe_pwd);
				break;
			case Utility.get_wifista_info:
				GetFromRemote.wanGetWifiSta();
				break;
			case Utility.switch_to_wifista:
				PostToRemote.wanSwitchToWifiSta(apcli_ssid, apcli_bssid,
						apcli_mode, apcli_enc, apcli_wpapsk, apcli_channel);
				break;
			case Utility.get_wifista_list:
				GetFromRemote.wanGetWifiStaSearchInfo();
				break;
			case Utility.get_ssid_info:
				GetFromRemote.getSsid();
				break;
			case Utility.set_ssid_info:
				PostToRemote.wifiSetSSIDInfo(mssid_0, security_mode, passphrase);
				break;
			case Utility.set_usb_mode:
				PostToRemote.setUsbmode(usb_mode);
				break;
			case Utility.get_usb_mode:
				GetFromRemote.getUsbmode();
				break;
			case Utility.device_pair:
				GetFromRemote.devicePair();
				break;
			case Utility.device_query:
				PostToRemote.deviceQuery(remote_id);
				break;
			case Utility.device_open:
				PostToRemote.deviceOpen(remote_id);
				break;
			case Utility.device_close:
				PostToRemote.deviceClose(remote_id);
				break;
			case Utility.device_control:
				PostToRemote.deviceControl(remote_id, remote_data);
				break;
			default:
				break;
			}
		}
		dell_connect();
	}

	public ParseJSONMessage getJSON(){		
		return getmsg;	
	}

	void dell_connect(){
		try {
			connect();
			
			if(whitch == Utility.device_pair || whitch == Utility.get_wifista_list){
				if(whitch == Utility.device_pair){
					remote.setPairResult(get_pair_id());
				} else if (whitch == Utility.get_wifista_list) {
					Log.d("abc", "get wifista list");
				}
			}else{
				Log.d("abc","whitch = " + whitch + ", result = " + get_result());
				if ("0".equals(get_result())) {
					
					Log.d("abc","whitch = " + whitch);
					if(whitch == Utility.login){
						gh.sendMessage(GeneralHandler.MsgElement(GeneralHandler.MSG_SHOW_TOAST, mContext.getString(R.string.server_ok)));
						
						if(XiaoYouActivity.pdialog != null){
							XiaoYouActivity.pdialog.dismiss();
						}
						mContext.startActivity(new Intent(mContext, FunctionSelectionActivity.class));
					}else{
						gh.sendMessage(GeneralHandler.MsgElement(GeneralHandler.MSG_SHOW_TOAST, mContext.getString(R.string.op_success)));
					}
				} else {
					
					if(whitch == Utility.login){
						if(XiaoYouActivity.pdialog != null){
							XiaoYouActivity.pdialog.dismiss();
						}
						gh.sendMessage(GeneralHandler.MsgElement(GeneralHandler.MSG_SHOW_TOAST, mContext.getString(R.string.server_failed) + " " + get_result()));
					}else{
						gh.sendMessage(GeneralHandler.MsgElement(GeneralHandler.MSG_SHOW_TOAST, mContext.getString(R.string.op_failed) + " " + get_result()));
					}
				}
			}
		} catch (Exception e) {
			postmsg = new JSONObject();
			disconnect();
			
			if(whitch == Utility.login){
				if(XiaoYouActivity.pdialog != null){
					XiaoYouActivity.pdialog.dismiss();
				}
				gh.sendMessage(GeneralHandler.MsgElement(GeneralHandler.MSG_SHOW_TOAST, mContext.getString(R.string.server_failed)));
			}else{
				gh.sendMessage(GeneralHandler.MsgElement(GeneralHandler.MSG_SHOW_TOAST, mContext.getString(R.string.op_failed)));
			}
			Log.e("abc","Connect Error: " + e);
		}
	}
}