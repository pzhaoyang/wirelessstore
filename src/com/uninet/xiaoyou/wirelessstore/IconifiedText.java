package com.uninet.xiaoyou.wirelessstore;

import android.graphics.drawable.Drawable; 

public class IconifiedText{
	private String mText = "";
	private String mInfo = "";
	private String mPath = "";
	private boolean misFile = false;
	private Drawable mIcon;
	
	public IconifiedText(String text, String path, boolean isfile, Drawable bullet) {
		this.mText = text;
		this.mPath = path;
		this.misFile = isfile;
		this.mIcon = bullet;
		
	}
	
	public IconifiedText(String text, String path, boolean isfile, String info,Drawable bullet) {
		this.mText = text;
		this.mPath = path;
		this.misFile = isfile;
		this.mInfo = info;
		this.mIcon = bullet;
	}
	
	public boolean isFile() {
		return misFile; 
	}
	
	public String getText() { 
		return mText; 
	}
	
	public String getInfo() { 
		return mInfo; 
	}
	
	public Drawable getIcon() { 
		return mIcon; 
	}
	
	public String getPath() {
		return mPath;
	}
} 

