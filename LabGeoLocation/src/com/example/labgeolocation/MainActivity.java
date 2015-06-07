package com.example.labgeolocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.Service;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvLocation;
	LocationManager locManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvLocation = (TextView) findViewById(R.id.tv_location);
		locManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
	}
	
	@Override
	protected void onResume() { 
		super.onResume();
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50, locListener);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locManager.removeUpdates(locListener);
	}
	
	private class LocationHelper extends AsyncTask<Location, Void, String> {
		@Override
		protected String doInBackground(Location... params) {
			Location loc = params[0];
			String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" 
					+loc.getLatitude()+"," 
					+loc.getLongitude()
					+"&sensor=true";
			// http://maps.googleapis.com/maps/api/geocode/json…
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = null;
			String data = "";
			try {
				httpResponse = httpClient.execute(httpGet);
				data = EntityUtils.toString(httpResponse.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return data;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			tvLocation.setText(result);
		}
	}
	
	LocationListener locListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			
		}
		
		@Override
		public void onProviderEnabled(String arg0) {
			
		}
		
		@Override
		public void onProviderDisabled(String arg0) {
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			double x = location.getLatitude();
			double y = location.getLongitude();
			tvLocation.setText("You are currently in " + x + ", " + y);
			
			Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
			List<Address> addrList = new ArrayList<Address>();
			try {
				addrList = geoCoder.getFromLocation(x, y, 1);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			if(!addrList.isEmpty()) {
				Address a = addrList.get(0);
				String s = "Your nearest address is: \n";
				s += "\nCountry: " + a.getCountryName()
						+ "\nLocality: " + a.getLocality()
						+ "\nThoroughfare: " + a.getThoroughfare();
				
				tvLocation.setText(s);
			} else {
				tvLocation.setText("No address found near you.");
			}
			
			new LocationHelper().execute(location);
		}
	};
	
	
	
}
