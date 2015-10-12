package com.uninet.xiaoyou.net;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


/**
 * 解析JSON数据包工具类
 * 
 * @author chenggong
 * 
 */
public class ParseJSONMessage implements Parcelable{

	private JSONObject jsonObject;

	public static final Parcelable.Creator<ParseJSONMessage> CREATOR = new Creator<ParseJSONMessage>() { 
        @Override 
        public ParseJSONMessage createFromParcel(Parcel source) {  
            String str = source.readString();
            ParseJSONMessage pjson = new ParseJSONMessage(str);
            return pjson; 
        }
 
        @Override
        public ParseJSONMessage[] newArray(int size) {
            return new ParseJSONMessage[size];
        } 
    };
    
	/**
	 * 构造方法
	 * 
	 * @param _in
	 */
	public ParseJSONMessage(InputStream in) throws Exception{
		parse(in);
	}
	
	ParseJSONMessage(String str){
		try {
			jsonObject = new JSONObject(str);
		} catch (JSONException e) {
			Log.e("abc","ParseJSONMessage error : " + e);
		}
	}
	
	byte[] Stream2Bytes(InputStream in) throws IOException{
		byte[] b = new byte[in.available()];
		
		in.read(b);
		in.close();
		
		return b;
	}
	
	
	private void parse(InputStream in)  throws Exception{
		byte[] b = Stream2Bytes(in);
		jsonObject = new JSONObject(new String(b));
		Log.d("abc", "ParseJSONMessage::parse jsonObject: " + jsonObject);
	}

	public String getJSONValue(String key) throws Exception{
		return jsonObject.getString(key);
	}
	
	public JSONObject getJSONObject(){
		return jsonObject;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		 dest.writeString(jsonObject.toString());
	}
	
    public String toString() {
        return jsonObject.toString();
    }
}
