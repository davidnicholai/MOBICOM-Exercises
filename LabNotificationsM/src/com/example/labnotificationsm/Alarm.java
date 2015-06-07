package com.example.labnotificationsm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Alarm extends BroadcastReceiver {
	public Alarm() {
	}

	int number = 0;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		
		// create notification
		Notification.Builder nBuilder = new Notification.Builder(context);
		nBuilder.setSmallIcon(R.drawable.ic_action_event);
		nBuilder.setTicker("CTRL ALT DEL: Event in 10 minutes").setContentTitle("CTRL ALT DEL").setContentText("New Event").setNumber(number++);
		
		// make notification go to an activity
		Intent i = new Intent();
		i.setClass(context, SecondActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
		nBuilder.setContentIntent(pi);
		
		
		//LIKE
		// create notification
		Notification.Builder nBuilder1 = new Notification.Builder(context);
		nBuilder1.setSmallIcon(R.drawable.ic_action_event);
		nBuilder1.setTicker("Share Post").setContentTitle("CTRL ALT DEL").setContentText("New Notification").setNumber(number++);
				
		// make notification go to an activity
		Intent i1 = new Intent();
		i1.setClass(context, SecondActivity.class);
		PendingIntent pi1 = PendingIntent.getActivity(context, 0, i1, Intent.FLAG_ACTIVITY_NEW_TASK);
		nBuilder1.setContentIntent(pi1);		
		
		NotificationManager nm = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
		nm.notify(0, nBuilder.build());
		NotificationManager nm1 = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
		nm1.notify(1, nBuilder.build());
	}
}
