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

public class MbListActivity extends Activity {

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
	private Button buttonsign;
//	public LocationClient mLocationClient;
//	
//	public double latitude;
//    public double longitude;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mblist);
		setTitle("查询--列表");
		Bundle extras = this.getIntent().getExtras();
		classname= extras.getString("classname");
		teacher= extras.getString("teacher");
		setViews();
/*		mLocationClient = new LocationClient(getApplicationContext());     
	    //声明LocationClient类
	    mLocationClient.registerLocationListener(new MyLocationListener());    
	    //注册监听函数
	    LocationClientOption option = new LocationClientOption();

		option.setLocationMode(LocationMode.Hight_Accuracy);
		//可选，设置定位模式，默认高精度
		//LocationMode.Hight_Accuracy：高精度；
		//LocationMode. Battery_Saving：低功耗；
		//LocationMode. Device_Sensors：仅使用设备；
			
		option.setCoorType("bd09ll");
		//可选，设置返回经纬度坐标类型，默认GCJ02
		//GCJ02：国测局坐标；
		//BD09ll：百度经纬度坐标；
		//BD09：百度墨卡托坐标；
		//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
		 	
		option.setScanSpan(1000);
		//可选，设置发起定位请求的间隔，int类型，单位ms
		//如果设置为0，则代表单次定位，即仅定位一次，默认为0
		//如果设置非0，需设置1000ms以上才有效
			
		option.setOpenGps(true);
		//可选，设置是否使用gps，默认false
		//使用高精度和仅用设备两种定位模式的，参数必须设置为true
			
		option.setLocationNotify(true);
		//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
			
		option.setIgnoreKillProcess(false);
		//可选，定位SDK内部是一个service，并放到了独立进程。
		//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
			
		option.SetIgnoreCacheException(false);
		//可选，设置是否收集Crash信息，默认收集，即参数为false

		option.setWifiCacheTimeOut(5*60*1000);
		//可选，V7.2版本新增能力
		//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
			
		option.setEnableSimulateGps(false);
		//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

		option.setNeedNewVersionRgc(true);
		//可选，设置是否需要最新版本的地址信息。默认需要，即参数为true
			
		mLocationClient.setLocOption(option);*/
	}

	private void setViews() {
		listView = (ListView) findViewById(R.id.mb_list_view);
		list = getDatas();
		adapter = new MbSimpleAdapter(this, list,  R.layout.mbmessage_list,
				new String[] { "classname","teacher" }, new int[] { R.id.class_name, R.id.teacher_text });
		listView.setAdapter(adapter);


	}
	
	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String url="";
		try {
			url = HttpUtil.BASE_URL + "ListServlet?classname="+URLEncoder.encode(
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
			for (int i = 0; i < results.length; i++) {
				String[] photos = results[i].split(",");

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("classname", photos[0]);
					map.put("teacher", photos[1]);
					map.put("classId", photos[2]);
					list.add(map);
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
				view = mInflater.inflate(R.layout.mbmessage_list, null);
				holder.classname = (TextView) view.findViewById(R.id.class_name);
				holder.teacher = (TextView) view.findViewById(R.id.teacher_text);
				holder.signbutton = (Button) view.findViewById(R.id.ButtonSign);
				System.out.println("pppppppppppppppppp   " + p);
				map.put(position, view);
				view.setTag(holder);
			} else {
				view = map.get(position);
				//holder = (GwcViewHolder) view.getTag();
			}
			

			// holder.button.
			holder.classname.setText(mData.get(position).get("classname").toString());
			holder.teacher.setText(mData.get(position).get("teacher").toString());
			holder.signbutton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					String url="";
					try {
						
						//mLocationClient为第二步初始化过的LocationClient对象
						//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
						//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
						//mLocationClient.start();
						HashMap<String, String> map = (HashMap<String, String>) listView
								.getItemAtPosition(position);
						url = HttpUtil.BASE_URL + "SignServlet?classId="+URLEncoder.encode(
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
							Toast.makeText(getApplicationContext(), photos[0], 1).show();
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
			TextView teacher;
			Button signbutton;
		}
		public void showInfo(int position){ 
			Toast toast = Toast.makeText(MbListActivity.this, 
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