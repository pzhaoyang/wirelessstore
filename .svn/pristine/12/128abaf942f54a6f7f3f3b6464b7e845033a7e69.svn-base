package com.uninet.xiaoyou.routemanager;

import java.util.ArrayList;
import java.util.List;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.wirelessstore.IconifiedText;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;

public class ShowListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<String> mItems = new ArrayList<String>();
	private int selectItem = -1;
	
	public ShowListAdapter(Context context){
		mInflater = LayoutInflater.from(context);
	}
	
	public void setSelected(int position){
		selectItem = position;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}
	
	public void addItem(String item) {
		mItems.add(item);
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void setListItems(List<String> list) {
		mItems = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
		}

		return null;
	}

}
