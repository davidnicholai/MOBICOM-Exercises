package com.example.labcustomadapter;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomItemAdapter extends ArrayAdapter<City> {
	City[] cities;
	
	public CustomItemAdapter(Context context, int resource, City[] objects) {
		super(context, resource, objects);
		cities = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater infl = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			convertView = infl.inflate(R.layout.list_item, parent, false);
		}
		
		ImageView img = (ImageView) convertView.findViewById(R.id.img);
		TextView tv = (TextView) convertView.findViewById(R.id.tv);
		TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
		
		City temp = cities[position];
		
		img.setImageResource(temp.getCityID());
		tv.setText(temp.getCityName());
		tv2.setText(temp.getCityCountry());

		return convertView;
	}
	
	
	
}
