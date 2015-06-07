package com.example.labnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
	public AlarmReceiver() {
	}
	
	int number = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		Notification.Builder nBuilder = new Notification.Builder(context);
		nBuilder.setSmallIcon(R.drawable.ic_action_event);
		//nBuilder.setLargeIcon(R.drawable.ic_action_event);
		nBuilder.setTicker("We spent $899.00 under your Google account!");
		nBuilder.setContentTitle("We used your money");
		nBuilder.setContentText("We spent $899.00 under your Google account!");
		nBuilder.setNumber(number++);
		
		Intent i = new Intent();
		i.setClass(context, SecondActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
		nBuilder.setContentIntent(pi);
		//startActivity(i);
		
		NotificationManager nm = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
		nm.notify(0, nBuilder.build());
		
		
	}
}
