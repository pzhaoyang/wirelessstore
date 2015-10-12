package com.uninet.xiaoyou.wirelessstore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;

import com.uninet.xiaoyou.GeneralHandler;
import com.uninet.xiaoyou.R;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;


public class SmbOpApi{
	public static String TAG = "smbapp";
	
	public static String prefix = "smb://";
	public static String ip = "";
	public static final int TOAST_MSG_SHOW = 1000;
	public static final int PROGRESS_MSG_SHOW = 1001;

	public static String tmpdir = "";
	public static SmbFile CopyorCut_buffer = null;
	public static int CopyorCut_flag = -1;
	public static boolean io_failed_flag = false;
	public static long io_trans_bytes = 0;
	private static boolean append_flag = false;
	public static boolean stop_thread = false; 
	public static boolean cancel_fail_dlg = false;
	
	static Thread thread = null;
	
	private static ExchangeHandler eh = ExchangeHandler.GetExchangeHandlerInstance();
	
	public static String getSmbRoot(Context context,String ip){
		return prefix + SmbOpApi.getAuthbyUrl(context, ip) + "@" + ip + "/" + "Public/";
	}
	
	//保存登录信息和ip到文件
	private static void saveAuth(Context context,String ip,String user_auth){
		SharedPreferences sp = context.getSharedPreferences("smburlinfo",0);
		Editor ed = sp.edit();
		ed.putString(ip, user_auth);
		ed.commit();
		tmpdir = Environment.getExternalStorageDirectory()
				+"/Android/"
				+ context.getPackageName()
				+ "/.cache/";
		Log.d(TAG,"tmpdir = " + tmpdir);
		Log.d(TAG,"Save Auth Success !");
	}
	
	public static String getAuthbyUrl(Context context, String ip){
		SharedPreferences sp = context.getSharedPreferences("smburlinfo", 0);
		return sp.getString(ip, "");
	}
	
	public static SmbFile getSmbFileByUrl(String smburl){
		try {
			return new SmbFile(smburl);
		} catch(MalformedURLException e){
			Log.d(SmbOpApi.TAG,"MalformedURLException Error! :" + e.getMessage());
		} catch(Exception e){
			Log.d(SmbOpApi.TAG,"normal Exception Error! :" + e.getMessage());
		}
		return null;
	}
	
	//登录
    public static void login(Context context, String ip,String user_auth) throws Exception {
        UniAddress dc = UniAddress.getByName(ip);
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user_auth);
        SmbSession.logon(dc,auth);
        Log.d(TAG,"Login " + ip+ " Success!");
        saveAuth(context, ip, user_auth);
    }
    
	public static String mkdir(Context context, SmbFile sbf){
		String result_msg;
		
		try {
			if(!sbf.exists()){
				sbf.mkdir();
				result_msg = context.getString(R.string.mkdir_success, sbf.getName());
			}else{
				result_msg =  context.getString(R.string.dir_exist, sbf.getName());
			}
			
		} catch (SmbException e) {
			result_msg = context.getString(R.string.mkdir_failed, sbf.getName());
			Log.d(TAG,"mkdir Error!:" + e.getMessage());
		}
		
		return result_msg;
	}
	
	public static boolean exists(SmbFile sbf){		
		try {
			return sbf.exists();
		} catch (SmbException e) {
			Log.d(TAG,"exists Error!:" + e.getMessage());
		}
		return false;
	}
	
	public static String delete(Context context, SmbFile sbf){
		String result_msg = "";
		if(sbf ==null){
			Log.d(TAG,"delete param error!");
			return "";
		}

		try {
			sbf.delete();
			result_msg = context.getString(R.string.del_success,sbf.getName());
		} catch (SmbException e) {
			result_msg = context.getString(R.string.del_failed,sbf.getName());
			Log.d(TAG,"delete Error!: " + e.getMessage());
		}
		
		return result_msg;
	}
	
	public static String rename(Context context, SmbFile from, SmbFile to){
		String result_msg;
		
		try {
			Log.d(TAG,"to == " + to.toString());
			from.renameTo(to);
			result_msg = context.getString(R.string.rename_success, from.getName(), to.getName());
		} catch (SmbException e) {
			result_msg =  context.getString(R.string.rename_failed, from.getName(), to.getName());
			Log.d(TAG,"rename Error!:" + e.getMessage());
		}
		return result_msg;
	}
	
	public static String pst(Context context, SmbFile src, final SmbFile des){
		String toast = "";
		long total = io_trans_bytes;
		 
		if(src == null){
			return "";
		}
		
		try {
			SmbFileInputStream in = new SmbFileInputStream(src);
			SmbFileOutputStream out = new SmbFileOutputStream(des, append_flag);

			int n;
			byte[] b = new byte[8192];
			
	        //跳过已经下载的字节
	        in.skip(io_trans_bytes);
	        
	        while((n = in.read( b )) > 0 ) {
	            out.write( b, 0, n );
	            total += n;
	            setProgressValue(total, getsize(src));
	            Log.d(TAG,"download total = " + total +", length = " + src.length());
		        if(stop_thread){
		        	break;
		        }
	        }
			append_flag = false;
			io_trans_bytes = 0;
			
			if(stop_thread){
				stop_thread = false;
				toast = context.getString((CopyorCut_flag == 1 ? R.string.move_failed : R.string.paste_failed), CopyorCut_buffer.getName());
			}else{
				if(CopyorCut_flag == 1){
					src.delete();
				}
				toast = context.getString((CopyorCut_flag == 1 ? R.string.move_success: R.string.paste_success), CopyorCut_buffer.getName());
			}
		} catch (Exception e) {
			append_flag = true;
			io_trans_bytes = total;
			Log.d(TAG,"pst Error : " + e.getMessage());
		}
		return toast;
	}
	
	public static void copy(Context context, SmbFile slectedfile){
		if(slectedfile == null){
			Log.d(TAG,"copy param error!");
			return;
		}
		CopyorCut_flag = 0;
		CopyorCut_buffer = slectedfile;
		eh.sendMessage(GeneralHandler.MsgElement(ExchangeHandler.MSG_SHOW_TOAST, context.getString(R.string.copied_file_success, CopyorCut_buffer.getName())));
	}

	public static void cut(Context context, SmbFile slectedfile){
		if(slectedfile == null){
			Log.d(TAG,"copy param error!");
			return;
		}
		
		CopyorCut_flag = 1;
		CopyorCut_buffer = slectedfile;
		eh.sendMessage(GeneralHandler.MsgElement(ExchangeHandler.MSG_SHOW_TOAST,context.getString(R.string.cuted_file_success, CopyorCut_buffer.getName())));
	}

	public static String dl(Context context, SmbFile seletcedfile, String target_folder){
		 long total = io_trans_bytes;
		 String toast  = "";

		   try {
			SmbFileInputStream in = new SmbFileInputStream(seletcedfile);
			FileOutputStream out = new FileOutputStream(target_folder + "/" + seletcedfile.getName(),append_flag);
			
			int n;
			byte[] b = new byte[8192];
			
	        //跳过已经下载的字节
	        in.skip(io_trans_bytes);
	        
	        while((n = in.read( b )) > 0 ) {
	            out.write( b, 0, n );
	            total += n;
	            setProgressValue(total,getsize(seletcedfile));
		        if(stop_thread){
		        	break;
		        }
	        }
	        in.close();
	        out.close();
	        append_flag = false;
			io_trans_bytes = 0;
			if(stop_thread){
				stop_thread = false;
				toast = context.getString(R.string.download_file_cancel, seletcedfile.getName());
				
			}else{
				toast = context.getString(R.string.download_file_success, seletcedfile.getName());
			}
		} catch (Exception e){
			append_flag = true;
			io_trans_bytes = total;
			Log.d(TAG,"dl Error: " + e.getMessage());
		}
	    
		return toast;
	}
	
	public static String up(Context context, SmbFile savedfile, String pickedfie){
		String toast = "";
		long tot = io_trans_bytes;
		try {
	        FileInputStream in = new FileInputStream(pickedfie);
	        SmbFileOutputStream out = new SmbFileOutputStream(savedfile,append_flag);

			in.skip(io_trans_bytes);
			
	        byte[] b = new byte[8192];
	        int n;
	        long filelen = (new File(pickedfie)).length();
	        while(( n = in.read( b )) > 0 ) {
	            out.write( b, 0, n );
	            tot += n;
		        setProgressValue(tot,filelen);
		        if(stop_thread){
		        	break;
		        }
	        }
		    in.close();
		    out.close();
		    append_flag = false;
			io_trans_bytes = 0;
			if(stop_thread){
				stop_thread = false;
				toast = context.getString(R.string.upload_file_cancel, savedfile.getName());
			}else{
				toast = context.getString(R.string.upload_file_success, savedfile.getName());
			}
		} catch (Exception e) {
			append_flag = true;
			io_trans_bytes = tot;
			Log.d(TAG,"up error: " + e);
		}
		return toast;
	}
	
	public static boolean isExists(String smbfile){
		boolean result = false;
		try {
			SmbFile f = new SmbFile(smbfile);
			result = f.exists();
		} catch (Exception e) {
			Log.d(TAG, smbfile + " isn't exist !");
		}
		
		return result;
	}
	
	public static boolean isDir(SmbFile sbf){
		boolean isdir = false;
		
		if(sbf == null) return false;
		
		try{
			isdir = sbf.isDirectory();
		}catch(SmbException e){
			Log.d(SmbOpApi.TAG,"e = " + e.getMessage());
		}
		Log.d(SmbOpApi.TAG,"curnode " + sbf.getName() + " is dir = " + isdir);
		return isdir;
	}
	
	public static boolean isFile(SmbFile sbf){
		boolean result = false;
		if(sbf == null) return false;
		
		try{
			result = sbf.isFile();
		}catch(SmbException e){
			Log.d(SmbOpApi.TAG,"e = " + e.getMessage());
		}
		return result;
	}
	
		
	private static long getsize(SmbFile sbf){
		long size = 0L;
		try {
			size = sbf.length();
		} catch (SmbException e) {
			Log.d(SmbOpApi.TAG,"getsize Error:" + e.getMessage());
		}
		return size;
	}
	
	public static String getFileSize(Context context, SmbFile sbf){
		long size = 0L;
		try {
			size = sbf.length();
		} catch (SmbException e) {
			Log.d(SmbOpApi.TAG,"getFileSize Error:" + e.getMessage());
		}
		return Formatter.formatFileSize(context,size);
	}
	
	private static void setProgressValue(long total,long filelength){
		int progress = (int)(total*100/filelength);
		eh.sendMessage(GeneralHandler.MsgElement(ExchangeHandler.MSG_SET_PROGRESS,progress));
	}
	
	public static String getDiskFreeSpace(Context context, SmbFile dir){
		long size = 0L;
		long free = 0L;
		try {
			free = dir.getDiskFreeSpace();
			size = dir.length();
		} catch (SmbException e) {
			Log.d(TAG,"getDiskFreeSpace Error!: " + e.getMessage());
		}
	
		return context.getString(R.string.remainder) +" "+Formatter.formatFileSize(context,free);
	}

	public static void ResetFlgs(){
		//up & down & cache
		io_trans_bytes = 0;
		append_flag = false;
		
		//paste
		CopyorCut_buffer = null;
		CopyorCut_flag = -1;
		
		cancel_fail_dlg = true;
		
	}
	
	public static boolean getAppendFlg(){
		return append_flag;
	}
	
	public static void setAppendFlg(boolean af){
		append_flag = af;
	}
}