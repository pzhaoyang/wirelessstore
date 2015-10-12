package com.uninet.xiaoyou.remotecontrol.remote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.remotecontrol.data.Value;
import com.uninet.xiaoyou.remotecontrol.ui.CharacterParser;
import com.uninet.xiaoyou.remotecontrol.ui.ClearEditText;
import com.uninet.xiaoyou.remotecontrol.ui.PinyinComparator;
import com.uninet.xiaoyou.remotecontrol.ui.SideBar;
import com.uninet.xiaoyou.remotecontrol.ui.SortAdapter;
import com.uninet.xiaoyou.remotecontrol.ui.SortModel;
import com.uninet.xiaoyou.remotecontrol.ui.SideBar.OnTouchingLetterChangedListener;
import com.uninet.xiaoyou.remotecontrol.utils.RemoteDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BrandListActivity extends Activity {
	private ListView brandListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> nameList = new ArrayList<String>();
	private int mType;
	private String mTypeName = null;
	private RemoteDB mRmtDB;
	private TextView deviceType;

	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brand_list);
		initData();
		initViews();
	}

	private void initData() {
		mRmtDB = new RemoteDB(getApplicationContext());

		android.util.Log.d("abc","type = " +  Value.currentType);
		mType = Value.currentType;
		mTypeName = Value.RemoteType[mType];
	}

	private void initViews() {
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		deviceType = (TextView) findViewById(R.id.device_type);
		String[] types = getResources().getStringArray(R.array.type_array);
		deviceType.setText(types[mType]);
	
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					brandListView.setSelection(position);
				}

			}
		});

		brandListView = (ListView) findViewById(R.id.brand_list);
		brandListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(BrandListActivity.this, RemoteListActivity.class);
				i.putExtra("brand", ((SortModel) adapter.getItem(position)).getName());
				startActivity(i);
				finish();
				Toast.makeText(getApplication(), ((SortModel) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});

		getBrand(mTypeName);
		String[] nameListSTr = new String[nameList.size()];
		nameList.toArray(nameListSTr);

		SourceDateList = filledData(nameListSTr);

		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		brandListView.setAdapter(adapter);

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private ArrayList<String> getBrand(String _type) {

		mRmtDB.open();
		list = mRmtDB.getBrand(_type);
		nameList = mRmtDB.translateBrands(list);
		mRmtDB.close();
		return nameList;
	}

	/**
	 * ΪListView������
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String[] date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.length; i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * ���������е�ֵ��������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// ���a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
