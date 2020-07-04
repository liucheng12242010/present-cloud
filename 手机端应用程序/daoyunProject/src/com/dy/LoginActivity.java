package com.dy;

import java.net.URLEncoder;

import com.dy.util.Comment;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	// 声明登录、取消按钮
	private Button cancelBtn,loginBtn,exitBtn;
	// 声明用户名、密码输入框
	private EditText userEditText,pwdEditText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("签到系统-登入");
		// 设置当前Activity界面布局
		setContentView(R.layout.login_system);
		Exception();
		// 通过findViewById方法实例化组件
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		// 通过findViewById方法实例化组件
		loginBtn = (Button)findViewById(R.id.loginButton);
		exitBtn = (Button)findViewById(R.id.exitButton);
		// 通过findViewById方法实例化组件
		userEditText = (EditText)findViewById(R.id.userEditText);
		// 通过findViewById方法实例化组件
		pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				userEditText.setText("");
				pwdEditText.setText("");
			}
		});
		
		exitBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.exit(0);   
			}
		});
		
		
		loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					String url = HttpUtil.BASE_URL
							+ "LoginServlet?userName="
							+ URLEncoder.encode(
									URLEncoder.encode(userEditText.getText().toString(), "UTF-8"), "UTF-8")+"&password="
									+ URLEncoder.encode(
									URLEncoder.encode(pwdEditText.getText().toString(), "UTF-8"), "UTF-8");
					// 查询返回结果
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  "+result);
					if(!result.equals("0")){
						Comment.PARAMETER.put("userId", result);
						System.out.println("账号密码登录验证成功");
						Toast.makeText(getApplicationContext(), "登入成功", 1).show();
						Intent intent = new Intent();
						intent.setClass(LoginActivity.this,
								OneActivity.class);
						startActivity(intent);
						
					}else{
						Toast.makeText(getApplicationContext(), "登入失败", 1).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
	}
	public void Exception(){
		   //避免出现android.os.NetworkOnMainThreadException异常
		   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		         .detectDiskReads().detectDiskWrites().detectNetwork()
		         .penaltyLog().build());

		   StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		         .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		         .penaltyLog().penaltyDeath().build());
		}
}