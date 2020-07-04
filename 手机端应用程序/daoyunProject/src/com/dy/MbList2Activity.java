package com.dy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.dy.util.Comment;
import com.dy.util.HttpUtil;

public class MbList2Activity extends Activity {

	MbSimpleAdapter adapter;
	ListView listView;
	EditText et;
	String id;
	String threadId;
	int index;
	List<Map<String, Object>> list;
	List<Integer> listItemID = new ArrayList<Integer>();
	String classname;
	String teacher;
	private Button buttonjoin;
//	public LocationClient mLocationClient;
//	
//	public double latitude;
//    public double longitude;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mblist2);
		setTitle("查询可加入班课--列表");
		Bundle extras = this.getIntent().getExtras();
		classname= extras.getString("classname");
		teacher= extras.getString("teacher");
		setViews();
	}

	private void setViews() {
		listView = (ListView) findViewById(R.id.mb_list_view2);
		list = getDatas();
		adapter = new MbSimpleAdapter(this, list,  R.layout.mbmessage_list2,
				new String[] { "classname","classId" }, new int[] { R.id.class_name2, R.id.classid2 });
		listView.setAdapter(adapter);


	}
	
	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String url="";
		try {
			url = HttpUtil.BASE_URL + "List2Servlet?classname="+URLEncoder.encode(
					URLEncoder.encode(classname, "UTF-8"),
					"UTF-8")+"&teacher="+URLEncoder.encode(
							URLEncoder.encode(teacher, "UTF-8"),
							"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 查询返回结果
		String result = HttpUtil.queryStringForPost(url);
		System.out.println("=========================  " + result);
		String[] results = result.split("@");

		try {
			if(results!=null){
				for (int i = 0; i < results.length; i++) {
					String[] photos = results[i].split(",");

						Map<String, Object> map = new HashMap<String, Object>();
						map.put("classname", photos[0]);
						map.put("teacher", photos[1]);
						map.put("classId", photos[2]);
						list.add(map);
				}
			}
		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}
	
	public void queryBack(View v) {
		finish();
	}
	public class MbSimpleAdapter extends SimpleAdapter {
		public List<String> mChecked;
		private int[] mTo;
		private String[] mFrom;
		private ViewBinder mViewBinder;
		private List<? extends Map<String, ?>> mData;
		private int mResource;
		private int mDropDownResource;
		private LayoutInflater mInflater;
		public String[] aa = new String[100];
		int a = 0;
		HashMap<Integer, View> map = new HashMap<Integer, View>();
		Context context = null;
		int index = 0;

		public MbSimpleAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource, String[] from,
				int[] to) {
			super(context, data, resource, from, to);
			mChecked = new ArrayList<String>();

			mTo = to;
			mFrom = from;
			mData = data;
			mResource = mDropDownResource = resource;
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.context = context;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			final int p = position;

			View view;
			final GwcViewHolder holder =new GwcViewHolder();

			if (map.get(position) == null) {
				System.out.println("contextcontext  " + context);
				LayoutInflater mInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = mInflater.inflate(R.layout.mbmessage_list2, null);
				holder.classname = (TextView) view.findViewById(R.id.class_name2);
				holder.classid = (TextView) view.findViewById(R.id.classid2);
				holder.joinbutton= (Button) view.findViewById(R.id.Buttonjoin);
				System.out.println("pppppppppppppppppp   " + p);
				map.put(position, view);
				view.setTag(holder);
			} else {
				view = map.get(position);
				//holder = (GwcViewHolder) view.getTag();
			}
			

			// holder.button.
			holder.classname.setText(mData.get(position).get("classname").toString());
			holder.classid.setText(mData.get(position).get("classId").toString());
			holder.joinbutton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					String url="";
					try {
						
						//mLocationClient为第二步初始化过的LocationClient对象
						//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
						//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
						//mLocationClient.start();
						HashMap<String, String> map = (HashMap<String, String>) listView
								.getItemAtPosition(position);
						url = HttpUtil.BASE_URL + "JoinServlet?classid="+URLEncoder.encode(
								URLEncoder.encode(map.get("classId").toString(), "UTF-8"),
								"UTF-8")+"&teacher="+URLEncoder.encode(
										URLEncoder.encode(map.get("teacher").toString(), "UTF-8"),
										"UTF-8")+"&id="+URLEncoder.encode(
												URLEncoder.encode(Comment.PARAMETER.get("userId").toString(), "UTF-8"),
												"UTF-8");/*+"&lat="+URLEncoder.encode(
														URLEncoder.encode(String.valueOf(latitude), "UTF-8"),
														"UTF-8")+"&lon="+URLEncoder.encode(
																URLEncoder.encode(String.valueOf(longitude), "UTF-8"),
																"UTF-8");*/
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 查询返回结果
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  " + result);
					String[] results = result.split("@");

					try {
						for (int i = 0; i < results.length; i++) {
							String[] photos = results[i].split(",");
							if(photos[0].equals("0")){
								Toast.makeText(getApplicationContext(), "找不到课程号对应的课程", 1).show();
							}else if(photos[0].equals("1")){
								Toast.makeText(getApplicationContext(), "加入班课成功", 1).show();
							}else if(photos[0].equals("3")){
								Toast.makeText(getApplicationContext(), "您已经加入过该班课", 1).show();
							}else{	
								Toast.makeText(getApplicationContext(), "加入班课异常请联系管理员", 1).show();
							}
						}
					} catch (Exception e) {

						
					}
				}

			});
			index++;
			System.out.println("indexindexindexindexindexindexindex   " + index);
			return view;
		}

		public class GwcViewHolder {
			TextView classname;
			TextView classid;
			Button joinbutton;
		}
		public void showInfo(int position){ 
			Toast toast = Toast.makeText(MbList2Activity.this, 
					 "简单的提示信息", 1 );
			toast.show(); 
		}

		/**
		 * 添加这个方法来处理Bitmap类型参数
		 * 
		 * @param v
		 * @param bitmap
		 */
		public void setViewImage(ImageView v, Bitmap bitmap) {
			v.setImageBitmap(bitmap);
		}
	}
//	public class MyLocationListener extends BDAbstractLocationListener{
//	    @Override
//	    public void onReceiveLocation(BDLocation location){
//	        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
//	        //以下只列举部分获取经纬度相关（常用）的结果信息
//	        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
//				
//	        latitude = location.getLatitude();    //获取纬度信息
//	        longitude = location.getLongitude();    //获取经度信息
//	        float radius = location.getRadius();    //获取定位精度，默认值为0.0f
//				
//	        String coorType = location.getCoorType();
//	        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
//				
//	        int errorCode = location.getLocType();
//	        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
//	    }
//	}

}