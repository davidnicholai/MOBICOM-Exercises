package com.example.labexamrssfeed;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomItemAdapter extends ArrayAdapter<News> {
	private News[] news;
	
	public CustomItemAdapter(Context context, int resource, News[] objects) {
		super(context, resource, objects);
		news = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infl = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			convertView = infl.inflate(R.layout.list_item, parent, false);
		}
		
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		TextView tvSubtext = (TextView) convertView.findViewById(R.id.tvSubtext);
		
		News temp = news[position];
		
		tvTitle.setText(temp.getTitle());
		tvSubtext.setText(temp.getSubtext());
		
		return convertView;
	}
}
