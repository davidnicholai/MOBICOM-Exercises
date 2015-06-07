package com.example.labexamrssfeed;

import java.io.IOException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FetcherException;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;

public class MainActivity extends Activity {
	private ListView lvNews;
	private News[] news;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lvNews = (ListView) findViewById(R.id.lvNews);
		
		new UrlHelper().execute();
	}
	
	private class UrlHelper extends AsyncTask<Void, Void, String> {
		
		@Override
		protected String doInBackground(Void... params) {
			String link = "http://www.nasa.gov/rss/dyn/breaking_news.rss";
			FeedFetcher feedFetcher = new HttpURLFeedFetcher();
			SyndFeed syndFeed = null;
			SyndEntry syndEntry = null;
			
			try {
				syndFeed = feedFetcher.retrieveFeed(new URL(link));
				
				// We initialize News[] with the size of getEntries().
				news = new News[syndFeed.getEntries().size()];
				
				for (int i = 0; i < syndFeed.getEntries().size(); i++) {
					syndEntry = (SyndEntry) syndFeed.getEntries().get(i);
					
					String title = syndEntry.getTitle();
					String section = syndEntry.getDescription().getValue();
					
					news[i] = new News(title, section);
				}
				
				return "true"; // Means that populating news[] with RSS feeds is successful.
			} catch (IllegalArgumentException | IOException | FeedException | FetcherException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			/*	If the background task is successful,
			 *	set the array contents to the ListView.
			 */
			if (result == "true") { 
				CustomItemAdapter adapter = new CustomItemAdapter(getBaseContext(), R.layout.list_item, news);
				lvNews.setAdapter(adapter);
			}
		}
	}
}
