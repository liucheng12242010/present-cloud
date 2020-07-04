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
		setTitle("��ѯ--�б�");
		Bundle extras = this.getIntent().getExtras();
		classname= extras.getString("classname");
		teacher= extras.getString("teacher");
		setViews();
/*		mLocationClient = new LocationClient(getApplicationContext());     
	    //����LocationClient��
	    mLocationClient.registerLocationListener(new MyLocationListener());    
	    //ע���������
	    LocationClientOption option = new LocationClientOption();

		option.setLocationMode(LocationMode.Hight_Accuracy);
		//��ѡ�����ö�λģʽ��Ĭ�ϸ߾���
		//LocationMode.Hight_Accuracy���߾��ȣ�
		//LocationMode. Battery_Saving���͹��ģ�
		//LocationMode. Device_Sensors����ʹ���豸��
			
		option.setCoorType("bd09ll");
		//��ѡ�����÷��ؾ�γ���������ͣ�Ĭ��GCJ02
		//GCJ02����������ꣻ
		//BD09ll���ٶȾ�γ�����ꣻ
		//BD09���ٶ�ī�������ꣻ
		//���������λ�����������������ͣ�ͳһ����WGS84��������
		 	
		option.setScanSpan(1000);
		//��ѡ�����÷���λ����ļ����int���ͣ���λms
		//�������Ϊ0��������ζ�λ��������λһ�Σ�Ĭ��Ϊ0
		//������÷�0��������1000ms���ϲ���Ч
			
		option.setOpenGps(true);
		//��ѡ�������Ƿ�ʹ��gps��Ĭ��false
		//ʹ�ø߾��Ⱥͽ����豸���ֶ�λģʽ�ģ�������������Ϊtrue
			
		option.setLocationNotify(true);
		//��ѡ�������Ƿ�GPS��Чʱ����1S/1��Ƶ�����GPS�����Ĭ��false
			
		option.setIgnoreKillProcess(false);
		//��ѡ����λSDK�ڲ���һ��service�����ŵ��˶������̡�
		//�����Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϣ����飩��ɱ������setIgnoreKillProcess(true)
			
		option.SetIgnoreCacheException(false);
		//��ѡ�������Ƿ��ռ�Crash��Ϣ��Ĭ���ռ���������Ϊfalse

		option.setWifiCacheTimeOut(5*60*1000);
		//��ѡ��V7.2�汾��������
		//��������˸ýӿڣ��״�������λʱ�������жϵ�ǰWi-Fi�Ƿ񳬳���Ч�ڣ���������Ч�ڣ���������ɨ��Wi-Fi��Ȼ��λ
			
		option.setEnableSimulateGps(false);
		//��ѡ�������Ƿ���Ҫ����GPS��������Ĭ����Ҫ��������Ϊfalse

		option.setNeedNewVersionRgc(true);
		//��ѡ�������Ƿ���Ҫ���°汾�ĵ�ַ��Ϣ��Ĭ����Ҫ��������Ϊtrue
			
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
		// ��ѯ���ؽ��
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
						
						//mLocationClientΪ�ڶ�����ʼ������LocationClient����
						//�轫���úõ�LocationClientOption����ͨ��setLocOption�������ݸ�LocationClient����ʹ��
						//����LocationClientOption�����ã��������ο���LocationClientOption�����ϸ˵��
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
					// ��ѯ���ؽ��
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
					 "�򵥵���ʾ��Ϣ", 1 );
			toast.show(); 
		}

		/**
		 * ����������������Bitmap���Ͳ���
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
//	        //�˴���BDLocationΪ��λ�����Ϣ�࣬ͨ�����ĸ���get�����ɻ�ȡ��λ��ص�ȫ�����
//	        //����ֻ�оٲ��ֻ�ȡ��γ����أ����ã��Ľ����Ϣ
//	        //��������Ϣ��ȡ˵�����������ο���BDLocation���е�˵��
//				
//	        latitude = location.getLatitude();    //��ȡγ����Ϣ
//	        longitude = location.getLongitude();    //��ȡ������Ϣ
//	        float radius = location.getRadius();    //��ȡ��λ���ȣ�Ĭ��ֵΪ0.0f
//				
//	        String coorType = location.getCoorType();
//	        //��ȡ��γ���������ͣ���LocationClientOption�����ù�����������Ϊ׼
//				
//	        int errorCode = location.getLocType();
//	        //��ȡ��λ���͡���λ���󷵻��룬������Ϣ�ɲ�����ο���BDLocation���е�˵��
//	    }
//	}

}