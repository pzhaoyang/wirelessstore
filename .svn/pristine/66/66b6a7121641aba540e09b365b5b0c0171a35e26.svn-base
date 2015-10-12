package com.uninet.xiaoyou.wirelessstore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GeneralUtil {
	public static String localip = "127.0.0.1";
	public static int port = 0;
	public static String auth = "guest:guest";
	public static boolean is_pick_file = true;
	
	public static String getFileType(String uri){
		String type = MimeUtils.getmimetype(MimeUtils.getextension(uri));
		android.util.Log.d(SmbOpApi.TAG,"File type = " + type );
		return type;
	}
	
	public static String getAuth(){
		return auth;
	}
	
	public static void SetAuth(String userpass){
		auth = userpass;
	}

    public static boolean isInvalidName(String name) {
        final String ILLGEAL_CHARACTER = "[/:\\*\\?\"<>\\|\\\\]|[\\\\]";
        
        if(name.startsWith(".")){
        	return true;
        }
        
        Pattern p = Pattern.compile(ILLGEAL_CHARACTER);
        Matcher m = p.matcher(name);
        return m.find();
    }
}
