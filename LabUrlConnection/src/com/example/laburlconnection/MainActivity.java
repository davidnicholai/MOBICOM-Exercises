package com.example.laburlconnection;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.text_view);
		new UrlHelper().execute();
	}
	
	private class UrlHelper extends AsyncTask<Void, Void, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
			Document d = null;
			Elements e = null;
			
			try {
				d = Jsoup.connect("http://en.wikipedia.org/wiki/Abbey_Road").get();
				e = d.select("a");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			return e.get(1).text();
			
			//return d.html();
		}
		
		@Override
		protected void onPostExecute(String result) {
			//super.onPostExecute(result);
			textView.setText(result);
		}
	
	}
}
