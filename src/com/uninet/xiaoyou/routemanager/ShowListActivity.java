//package com.uninet.xiaoyou.routemanager;
//
//import com.uninet.xiaoyou.R;
//import com.uninet.xiaoyou.Utility;
//import com.uninet.xiaoyou.net.DataInteractionThread;
//
//import android.annotation.SuppressLint;
//import android.app.ListActivity;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
///**
// * 二级功能界面
// * 
// * @author chenggong
// * 
// */
//@SuppressLint("ResourceAsColor")
//public class ShowListActivity extends ListActivity {
//
//	static ArrayAdapter<String> adapter;
//	private static int which;
//
//	SharedPreferences sp;
//	Editor editor;
//
//	static DataInteractionThread getInfo = new DataInteractionThread();
//	DataInteractionThread setInfo;
//
//	/**
//	 * 构造方法
//	 */
//	@SuppressLint({ "WorldReadableFiles", "ResourceAsColor" })
//	@SuppressWarnings("deprecation")
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		sp = getSharedPreferences("router_info", MODE_WORLD_READABLE);
//		editor = sp.edit();
//		int which = getIntent().getIntExtra("which", 0);
//
//		adapter = new ArrayAdapter<String>(this,
//				R.layout.devicemanager_showlist, RouteManagerActivity.list);
//		setListAdapter(adapter);
//
////		if (which == Utility.through_wall_settings) {
////			int selected = sp.getInt("through_wall_settings", 1);
////		}
//	}
//
//	/**
//	 * 更新list并通知界面刷新
//	 * 
//	 * @param which
//	 */
//	public void refresh(int _which) {
//		which = _which;
//
//		if (!getInfo.get_result().equals("0")) {
//			RouteManagerActivity.list.clear();
//			RouteManagerActivity.list.add(getInfo.get_result());
//		} else {
//			switch (_which) {
//
//			}
//		}
//		adapter.notifyDataSetChanged();
//	}
//
//	/**
//	 * 相应点击事件
//	 */
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		if (!getInfo.get_result().equals("0")) {
//			return;
//		}
//		switch (which) {
//
//		}
//	}
//
//}