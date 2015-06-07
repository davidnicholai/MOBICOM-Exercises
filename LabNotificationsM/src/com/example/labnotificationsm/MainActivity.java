package com.example.labnotificationsm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonNotify = (Button) findViewById(R.id.button_notify);
		buttonNotify.setOnClickListener(notify);
		Button buttonLike = (Button) findViewById(R.id.button_like);
		buttonLike.setOnClickListener(like);
	}
	
	int number = 0;
	
	OnClickListener notify = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			// create notification
//			Notification.Builder nBuilder = new Notification.Builder(getBaseContext());
//			nBuilder.setSmallIcon(R.drawable.ic_action_event);
//			nBuilder.setTicker("CTRL ALT DEL: Event in 10 minutes").setContentTitle("CTRL ALT DEL").setContentText("New Event").setNumber(number++);
//			
//			// make notification go to an activity
//			Intent i = new Intent();
//			i.setClass(getBaseContext(), SecondActivity.class);
//			PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
//			nBuilder.setContentIntent(pi);
//			
//			NotificationManager nm = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
//			nm.notify(0, nBuilder.build());
			
			//set an alarm
			//set the time
			Calendar currentTime = Calendar.getInstance();
			Intent i = new Intent();
			i.setClass(MainActivity.this, Alarm.class);
			PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
			
			long time = currentTime.getTimeInMillis() + (5 * 1000);
			AlarmManager am = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, time, pi);
		}
	};
	
OnClickListener like = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//set an alarm
			//set the time
			Calendar currentTime = Calendar.getInstance();
			Intent i = new Intent();
			i.setClass(MainActivity.this, Alarm.class);
			PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
			
			long time = currentTime.getTimeInMillis() + (10 * 1000);
			AlarmManager am = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, time, pi);
		}
	};

	protected PendingIntent PendingIntent(Context baseContext, int i,
			Intent i2, int flagActivityNewTask) {
		// TODO Auto-generated method stub
		return null;
	}
}
