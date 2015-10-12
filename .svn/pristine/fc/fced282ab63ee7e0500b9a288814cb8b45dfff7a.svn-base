package com.uninet.xiaoyou.wirelessstore;

import com.uninet.xiaoyou.*;

import java.io.File;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
public class BrowsLocalFileTask extends AsyncTask<String, Void, Void>{
		private static Context mcontext;
		private ArrayList<IconifiedText> item = new ArrayList<IconifiedText>();
		private static ArrayList<IconifiedText> nativelist = new ArrayList<IconifiedText>();
		private static IconifiedTextListAdapter dlistadapter = null;
		private static String NativeCurrPath = "";
		
		private ProgressDialog dialog = null;
		
		BrowsLocalFileTask(String path){
			this.execute(path);
		}
		
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			dialog = new ProgressDialog(mcontext);
			dialog.setMessage(mcontext.getString(R.string.loading));
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params){
				
				File lf = new File(params[0]);
				ArrayList<File> dlist = new ArrayList<File>();
				ArrayList<File> flist = new ArrayList<File>();
				setNativeCurrPath(lf);

				File[] fs = lf.listFiles();
				
				for (File f : fs){
					
					if(f.isHidden()){//跳过隐藏文件或目录
						continue;
					}
					
					if (f.isDirectory()){
						if((Init.getStoragePrefix().equals(lf.getPath())
								&& f.canRead() 
								&& f.canWrite()
								&& f.list().length !=0)
								|| !(Init.getStoragePrefix().equals(lf.getPath()))){
							dlist.add(f);
						}
					} else {
						if(GeneralUtil.is_pick_file){
							flist.add(f);
						}
					}
				}
	
				dlist.addAll(flist);
	
				for (File f : dlist){
					String filePath = f.getPath();
					String fileName = f.getName();
					boolean isFile = f.isFile();
					String fileSize = Formatter.formatFileSize(mcontext, f.length());
					Drawable dab = mcontext.getResources().getDrawable(
							getIconbyNativeFile(filePath));
					/*IconifiedText(String filename, String filepath,boolean isfile, Drawable fileicon,);*/
					if(isFile){
						item.add(new IconifiedText(fileName, filePath, isFile, fileSize, dab));
					}else{
						item.add(new IconifiedText(fileName, filePath, isFile, dab));
					}
				}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			nativelist.clear();
			if(!isNativeRoot()){
				String up = new File(NativeCurrPath).getParent();
				nativelist.add(new IconifiedText("..", up, false, mcontext.getResources().getDrawable(R.drawable.folder)));
			}
			
			for (IconifiedText i : item){
				nativelist.add(i);
			}

			dialog.cancel();
			dlistadapter.notifyDataSetChanged();
		}
		
		static IconifiedTextListAdapter getadapter(){
			return dlistadapter;
		}
		
		static void setadapter(Context context){
			mcontext = context;
			dlistadapter = new IconifiedTextListAdapter(context);
		}
		
		static void setContext(Context context){
			mcontext = context;
		}
		
		static void setNativeCurrPath(File f){
			if(!f.toString().equals(Init.getStoragePrefix())){
				NativeCurrPath = f.toString();
			}else{
				NativeCurrPath = Init.getStoragePrefix();
			}
			Log.d("abc","NativeCurrPath = " + NativeCurrPath);
		}
		
		private boolean isNativeRoot(){
			return NativeCurrPath.equals(Init.getStoragePrefix());
		}
		
		static String getNativeCurrPath(){
			return NativeCurrPath;
		}
		
		private int getIconbyNativeFile(String filepath){
			int icon = R.drawable.ic_launcher;
			File f = new File(filepath);
			
			if(f.isDirectory()){
				icon = R.drawable.folder;
			}else{
				icon = MimeUtils.getIconbyType(MimeUtils.getmimetype(MimeUtils.getextension(f.getName())));
			}
			
			return icon;
		}
		
		static ArrayList<IconifiedText> getNativeList(){
			return nativelist;
		}
		
		public static  View SetDialogListAdapter(Context context){
			LayoutInflater inflater = LayoutInflater.from(context);
		    View view = inflater.inflate(R.layout.dialog_list, null);
		    ListView lv  = (ListView)view.findViewById(R.id.dialog_list);

		    lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					IconifiedText it = BrowsLocalFileTask.getNativeList().get(arg2);
					
					if(it.isFile()){
						BrowsLocalFileTask.setNativeCurrPath(new File(it.getPath()));
						BrowsLocalFileTask.getadapter().setSelectItem(arg2);
						BrowsLocalFileTask.getadapter().notifyDataSetInvalidated();
					}else{
						BrowsLocalFileTask.getadapter().setSelectItem(-1);//用于清除选择项，当目录切换时
						new BrowsLocalFileTask(it.getPath());
					}
				}
			});
		    
		    BrowsLocalFileTask.setadapter(context);
		    BrowsLocalFileTask.getadapter().setListItems(BrowsLocalFileTask.getNativeList());
			lv.setAdapter(BrowsLocalFileTask.getadapter());
			return view;
		}		
	}