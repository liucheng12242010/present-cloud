package com.dy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.util.Comment;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class JoinActivity extends Activity {

	private Button buttonJoin;
	private EditText text1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join);
		setTitle("加入班课");
		
		text1 = (EditText) findViewById(R.id.ClassNumber1);
		buttonJoin = (Button) this.findViewById(R.id.ButtonJoin);
		buttonJoin.setOnClickListener(myListener);
	}
	
	private OnClickListener myListener = new Button.OnClickListener() {
		public void onClick(View v) {
			String classNumber;
			if(text1.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "请输入班课号", 1).show();
			}else{
				classNumber = text1.getText().toString();
				String url="";
				try {
					url = HttpUtil.BASE_URL + "JoinServlet?classid="+URLEncoder.encode(
							URLEncoder.encode(classNumber, "UTF-8"),
							"UTF-8")+"&id="+URLEncoder.encode(
									URLEncoder.encode(Comment.PARAMETER.get("userId").toString(), "UTF-8"),
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
						if(photos[0].equals("0")){
							Toast.makeText(getApplicationContext(), "找不到课程号对应的课程", 1).show();
						}else if(photos[0].equals("1")){
							Toast.makeText(getApplicationContext(), "加入班课成功", 1).show();
						}else{
							Toast.makeText(getApplicationContext(), "加入班课异常请联系管理员", 1).show();
						}
					}
				} catch (Exception e) {

					Toast.makeText(getApplicationContext(), "", 1).show();
				}
			}
		}

	};
	
	public void queryBack(View v) {
		finish();
	}
}