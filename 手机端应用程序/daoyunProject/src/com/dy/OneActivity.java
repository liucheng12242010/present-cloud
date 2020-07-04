package com.dy;

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

public class OneActivity extends Activity {
	/** Called when the activity is first created. */
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private ArrayAdapter<String> adapter1;
	private EditText text1;
	private EditText text2;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		setTitle("查询");
		button1 = (Button) this.findViewById(R.id.Button1);
		button1.setOnClickListener(myListener1);

		button2 = (Button) this.findViewById(R.id.Button2);
		button2.setOnClickListener(myListener2);
		
		button3 = (Button) this.findViewById(R.id.Button3);
		button3.setOnClickListener(myListener3);
		
		button4 = (Button) this.findViewById(R.id.Button4);
		button4.setOnClickListener(myListener4);
		
		text1 = (EditText) findViewById(R.id.Text1);
		text2 = (EditText) findViewById(R.id.Text2);

	}

	// 计算
	private OnClickListener myListener1 = new Button.OnClickListener() {

		public void onClick(View v) {
			String classname = text1.getText().toString();
			String teacher = text2.getText().toString();
			Intent intent = new Intent();

			intent.setClass(OneActivity.this, MbListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("classname", classname);
			bundle.putString("teacher",teacher );
			intent.putExtras(bundle);
			startActivity(intent);

		}

	};

	

	// 清空
	private OnClickListener myListener2 = new Button.OnClickListener() {
		public void onClick(View v) {
			text1.setText("");
			text2.setText("");
		}

	};
	
	// 加入班课
	private OnClickListener myListener3 = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(OneActivity.this, JoinActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);
		}

	};
	
	// 查询所有班课
		private OnClickListener myListener4 = new Button.OnClickListener() {
			public void onClick(View v) {
				String classname = text1.getText().toString();
				String teacher = text2.getText().toString();
				Intent intent = new Intent();
				intent.setClass(OneActivity.this, MbList2Activity.class);
				Bundle bundle = new Bundle();
				bundle.putString("classname", classname);
				bundle.putString("teacher",teacher );
				intent.putExtras(bundle);
				startActivity(intent);
			}

		};
	public void queryBack(View v) {
		finish();
	}

}