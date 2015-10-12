package com.uninet.xiaoyou.wirelessstore;

import java.security.MessageDigest;
import java.io.FileInputStream;

import android.util.Log;
import jcifs.smb.SmbFileInputStream;
    

public class FileCheckSum {

	/**
	 * @param datafile need to generated filepath
	 */
	public static String GetFileCheckSum(String datafile) throws Exception{
		long startTime= System.currentTimeMillis(); 
		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(datafile);
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        
        String cm = utils(md);
        Log.d("abc","GetFileCheckSum " + datafile + " time = " + (System.currentTimeMillis() - startTime));
        return cm;
	}
	
	/**
	 * @param datafile need to generated smbfilepath
	 */
	public static String GetSmbFileCheckSum(String datafile) throws Exception{
		long startTime= System.currentTimeMillis(); 
		MessageDigest md = MessageDigest.getInstance("MD5");
		SmbFileInputStream fis = new SmbFileInputStream(datafile);
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        
        String cm = utils(md);
        Log.d("abc","GetSmbFileCheckSum  " + datafile + " time = " + (System.currentTimeMillis() - startTime));
        return cm;
	}
	
	
	private static String utils(MessageDigest md) throws Exception{
        byte[] mdbytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
	}
	
}
