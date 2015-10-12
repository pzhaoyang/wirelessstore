package com.uninet.xiaoyou.wirelessstore;

import com.uninet.xiaoyou.*;

import java.io.IOException;

import android.util.Log;
import jcifs.util.MimeMap;

public class MimeUtils{

	static MimeMap mp;
	
	public static String getmimetype(String extension){
		String ext="";
		try {
			mp = new MimeMap();
			ext = mp.getMimeType(extension);
		} catch (IOException e) {
			Log.d(SmbOpApi.TAG,"Extension:  constrator Error!:" + e.getMessage());
		}
		Log.d(SmbOpApi.TAG, "Extension: " + extension + " mimetype  = " + ext);
		return ext;

//        if (extension.length() > 0) {
//            String webkitMimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
//
//            if (webkitMimeType != null) {
//                return webkitMimeType;
//            }
//        }
//        
//        return "application/unknown";
	}
	
	public static int getIconbyType(String type){
		int ico;
		
		if(type.contains("text/")){
			ico = R.drawable.text;
		}else if(type.contains("application/pdf")){
			ico = R.drawable.pdf;
		}else if(type.contains("application/zip")
				||type.contains("application/x-tar")
				||type.contains("application/x-gzip")
				||type.contains("application/x-gtar")){
			ico = R.drawable.compress;
		}else if(type.contains("video/")){
			ico = R.drawable.video;
		}else if(type.contains("image/")){
			ico = R.drawable.image;
		}else if(type.contains("application/msword")){
			ico = R.drawable.word;
		}else if(type.contains("application/mspowerpoint")){
			ico = R.drawable.ppt;
		}else if(type.contains("application/vnd.ms-excel")){
			ico = R.drawable.excel;
		}else if(type.contains("audio/")){
			ico = R.drawable.audio;
		}else{
			ico = R.drawable.icon_common; 
		}
		Log.d(SmbOpApi.TAG, "Extension: " + type + " ico  = " + ico);
		return ico;
	}
	
	public static String getextension(String filename){
		String ex ="";
		ex = filename.substring(filename.lastIndexOf(".")+1,filename.length());
		Log.d(SmbOpApi.TAG, "Extension: " + filename + " Extension  = " + ex);
		return ex;
	}
}