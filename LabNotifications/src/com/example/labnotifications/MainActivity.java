package com.example.labnotifications;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnNotify = (Button) findViewById(R.id.btnNotify);
        btnNotify.setOnClickListener(notify);
        
        Button btnNotify2 = (Button) findViewById(R.id.btnNotify2);
        btnNotify2.setOnClickListener(notify2);
    }
    
    OnClickListener notify = new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		/* create notif on click
    		Notification.Builder nBuilder = new Notification.Builder(getBaseContext());
    		nBuilder.setSmallIcon(R.drawable.ic_action_event);
    		//nBuilder.setLargeIcon(R.drawable.ic_action_event);
    		nBuilder.setTicker("We spent $899.00 under your Google account!");
    		nBuilder.setContentTitle("We used your money");
    		nBuilder.setContentText("We spent $899.00 under your Google account!");
    		nBuilder.setNumber(number++);
    		
    		Intent i = new Intent();
    		i.setClass(getBaseContext(), SecondActivity.class);
    		PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
    		nBuilder.setContentIntent(pi);
    		//startActivity(i);
    		
    		NotificationManager nm = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
    		nm.notify(0, nBuilder.build());*/
    		
    		Calendar currentTime = Calendar.getInstance();
    		//currentTime.getTimeInMillis();
    		Intent i = new Intent();
    		i.setClass(getBaseContext(), AlarmReceiver.class);
    		PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    		
    		
    		long time = currentTime.getTimeInMillis() + (5 * 1000);
    		AlarmManager am = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
    		am.set(AlarmManager.RTC_WAKEUP, time, pi);
    	}
    };
    
    OnClickListener notify2 = new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		Calendar currentTime = Calendar.getInstance();
    		//currentTime.getTimeInMillis();
    		Intent i = new Intent();
    		i.setClass(getBaseContext(), AlarmReceiver.class);
    		PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    		
    		
    		long time = currentTime.getTimeInMillis() + (10 * 1000);
    		AlarmManager am = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
    		am.set(AlarmManager.RTC_WAKEUP, time, pi);
    	}
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
