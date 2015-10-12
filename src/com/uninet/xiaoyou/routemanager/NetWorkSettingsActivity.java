package com.uninet.xiaoyou.routemanager;

import java.util.ArrayList;
import java.util.List;

import com.uninet.xiaoyou.R;
import com.uninet.xiaoyou.Utility;
import com.uninet.xiaoyou.net.DataInteractionThread;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

public class NetWorkSettingsActivity extends ListActivity implements
		OnCheckedChangeListener, OnClickListener, OnItemClickListener {

	private static ArrayList<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	private int which;
	private final static int PPPOE_DIALOG = 0;
	private final static int AP_CLIENT_DIALOG = 1;
	private final static int DHCP_DILAOG = 2;

	EditText pword, username;
	CheckBox showpwd;
	CheckedTextView scan;
	String[] wifista;
	ListView wifista_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle(R.string.network_info);

		list.add(getString(R.string.pppoe));
		list.add(getString(R.string.ap_client));
		list.add(getString(R.string.dhcp));

		adapter = new ArrayAdapter<String>(this,
				R.layout.devicemanager_showlist, list);
		setListAdapter(adapter);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d(Utility.TAG, "position : " + position);
		showDialog(position);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		LayoutInflater layoutInflater = LayoutInflater
				.from(NetWorkSettingsActivity.this);
		View dialog_view;

		showpwd.setOnCheckedChangeListener(NetWorkSettingsActivity.this);

		DataInteractionThread dt;

		switch (id) {
		case PPPOE_DIALOG:

			dialog_view = layoutInflater.inflate(R.layout.dialog_pppoe, null);
			pword = (EditText) dialog_view.findViewById(R.id.password);
			username = (EditText) dialog_view.findViewById(R.id.account);
			showpwd = (CheckBox) dialog_view.findViewById(R.id.showpassword);

			// 获取已保存的宽带连接信息
			dt = new DataInteractionThread(this, Utility.get_pppoe_info, false);
			dt.start();
			// 如果已有保存的信息，则自动填充用户名
				try {
					username.setText(dt.get_pppoe_uname());
				} catch (Exception e) {
					username.setText("");
					Log.d("abc","PPOE RESET: " + e);
				}

			return new AlertDialog.Builder(this)
					.setTitle(R.string.pppoe)
					.setView(dialog_view)
					.setNegativeButton(R.string.ok,
							NetWorkSettingsActivity.this)
					.setPositiveButton(R.string.cancel,
							NetWorkSettingsActivity.this).create();

		case AP_CLIENT_DIALOG:
			List<String> list = new ArrayList<String>();
			dialog_view = layoutInflater.inflate(R.layout.dialog_ap_client,
					null);
			pword = (EditText) dialog_view.findViewById(R.id.password);
			username = (EditText) dialog_view.findViewById(R.id.account);
			showpwd = (CheckBox) dialog_view.findViewById(R.id.showpassword);
			wifista_list = (ListView) dialog_view
					.findViewById(R.id.wifista_list);
			wifista_list.setAdapter(new ArrayAdapter<String>(this,
					R.layout.devicemanager_showlist, list));

			dt = new DataInteractionThread(NetWorkSettingsActivity.this,
					Utility.get_wifista_list, false);
			dt.start();

			do {
				wifista = dt.get_wifista_list();
			} while (wifista.length < 2);
			for (int i = 0; i < wifista.length; i++) {
				list.add(wifista[i]);
			}
			wifista_list.setOnItemClickListener(NetWorkSettingsActivity.this);

			return new AlertDialog.Builder(this)
					.setTitle(R.string.ap_client)
					.setView(dialog_view)
					.setNegativeButton(R.string.ok,
							NetWorkSettingsActivity.this)
					.setPositiveButton(R.string.cancel,
							NetWorkSettingsActivity.this).create();

		}
		return super.onCreateDialog(id);
	}

	@Override
	@Deprecated
	protected void onPrepareDialog(int id, Dialog dialog) {
		DataInteractionThread dt;

		switch (id) {
		case PPPOE_DIALOG:
			// 获取已保存的宽带连接信息
			dt = new DataInteractionThread(this, Utility.get_pppoe_info, false);
			dt.start();
			// 如果已有保存的信息，则自动填充用户名
			try {
				username.setText(dt.get_pppoe_uname());
			} catch (Exception e) {
				username.setText("");
				Log.d("abc","PPOE RESET: " + e);
			}
			break;

		case AP_CLIENT_DIALOG:
			dt = new DataInteractionThread(NetWorkSettingsActivity.this,
					Utility.get_wifista_list, false);
			dt.start();

			do {
				wifista = dt.get_wifista_list();
			} while (wifista.length < 2);
			for (int i = 0; i < wifista.length; i++) {
				list.add(wifista[i]);
			}
			break;
		}
		super.onPrepareDialog(id, dialog);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
		if (ischecked) {
			pword.setTransformationMethod(HideReturnsTransformationMethod
					.getInstance());
		} else {
			pword.setTransformationMethod(PasswordTransformationMethod
					.getInstance());
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void onClick(DialogInterface dialog, int button) {
		switch (which) {
		case PPPOE_DIALOG:
			if (button == dialog.BUTTON_NEGATIVE) {
				DataInteractionThread _dt = new DataInteractionThread(
						NetWorkSettingsActivity.this, Utility.switch_to_pppoe,
						false);
				_dt.set_pppoe_info(username.getText().toString(), pword
						.getText().toString());
				_dt.start();
			}
			return;

		case AP_CLIENT_DIALOG:
			if (button == dialog.BUTTON_NEGATIVE) {
				DataInteractionThread _dt = new DataInteractionThread(
						NetWorkSettingsActivity.this,
						Utility.switch_to_wifista, false);
				_dt.set_wifista_info(username.getText().toString(), pword
						.getText().toString());
				_dt.start();
			}
			return;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		username.setText(wifista[arg2]);
	}

}
