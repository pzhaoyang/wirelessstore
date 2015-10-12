package com.uninet.xiaoyou;

import android.util.Log;

/**
 * 全局工具类
 * 
 * @author chenggong
 * 
 */
public final class Utility {

	// 全局log开关
	static public final boolean logable = true;
	static public final String TAG = "cgcgcg";

	// 主服务器地址
	static public final String server_addr = "192.168.0.111";
	// 主服务器端口
	static public final int server_port = 8090;

	static public final int register = 0;
	static public final int login = 1;
	static public final int update_pword = 2;
	static public final int switch_to_pppoe = 3;
	static public final int get_pppoe_info = 4;
	static public final int get_wifista_info = 5;
	static public final int switch_to_wifista = 6;
	static public final int get_wifista_list = 7;
	static public final int get_ssid_info = 8;
	static public final int set_ssid_info = 9;
	static public final int get_usb_mode = 10;
	static public final int set_usb_mode = 11;
	
	static public final int device_pair =12;
	static public final int device_query =13;
	static public final int device_open =14;
	static public final int device_close =15;
	static public final int device_start =16;
	static public final int device_stop =17;
	static public final int device_control =18;
	
	//static public final int soft_version = 6;

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);

			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		b = null;
		return b2;
	}

	public static String bin2hex(String bin) {
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		byte[] bs = bin.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(digital[bit]);
			bit = bs[i] & 0x0f;
			sb.append(digital[bit]);
		}
		return sb.toString();
	}

	public static String hex2bin(String hex) {
		String digital = "0123456789ABCDEF";
		char[] hex2char = hex.toCharArray();
		byte[] bytes = new byte[hex.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) * 16;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xff);
		}
		return new String(bytes);
	}
    
    public static byte[] ASCTOBCD(byte[] ascii, int asc_len) {

        byte bcd[] = new byte[2*asc_len];
        
        int i = 0;
        for(int j = 0; j<asc_len; j++){
        	char asc = (char)ascii[j];
        	Log.d("abc","asc = " + Integer.valueOf(asc) + ", ascii[" + j+"] = " + "0x" + Integer.toHexString(asc) + ", ");
        	bcd[i] = (byte) getBCDChar((byte)( (asc >> 4 )& 0x0F));
        	i++;
        	bcd[i] = (byte) getBCDChar((byte)(asc & 0x0F));
        	i++;
        	 
        }
        return bcd;  
    }
    
    static char getBCDChar(byte asc){
    	char chRes = ' ';
    	switch( Integer.valueOf(asc)){
    	case 0:
    		chRes	= '0';break;
    	case 1:
    		chRes	= '1';break;
    	case 2:
    		chRes	= '2';break;
    	case 3:
    		chRes	= '3';break;
    	case 4:
    		chRes	= '4';break;
    	case 5:
    		chRes	= '5';break;
    	case 6:
    		chRes	= '6';break;
    	case 7:
    		chRes	= '7';break;
    	case 8:
    		chRes	= '8';break;
    	case 9:
    		chRes	= '9';break;
    	case 10:
    		chRes	= 'a';break;
    	case 11:
    		chRes	= 'b';break;
    	case 12:
    		chRes	= 'c';break;
    	case 13:
    		chRes	= 'd';break;
    	case 14:
    		chRes	= 'e';break;
    	case 15:
    		chRes	= 'f';break;
    	}
    	return	chRes;
    }
}
