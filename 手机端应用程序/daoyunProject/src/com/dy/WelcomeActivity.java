package com.dy;

import java.net.URLEncoder;

import com.dy.util.Comment;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	
	 int count = 6;
	    private Handler handler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);
	            count--;

	            if (count == 0) {
	                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
	                startActivity(intent);
	            } else {
	                handler.sendEmptyMessageDelayed(1, 1000);
	            }

	        }
	    };


	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
			// 设置标题
			setTitle("签到系统-登入");
			// 设置当前Activity界面布局
			setContentView(R.layout.welcome);
			Exception();
	        handler.sendEmptyMessage(1);

	    }


	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        handler.removeMessages(1);

	        if (handler != null) {
	            handler = null;
	        }
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