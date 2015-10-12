package com.uninet.xiaoyou.routemanager;

import android.graphics.drawable.Drawable; 

public class RouteItem{
	private int mId;
	private Drawable mIcon;
	private String mText = "";
	private String mInfo = "";
	
	public RouteItem(int id, Drawable bullet, String text, String info) {
		this.mId = id;
		this.mIcon = bullet;
		this.mText = text;
		this.mInfo = info;
	}
	public int getId(){
		return mId;
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
}