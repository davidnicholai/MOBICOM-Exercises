package com.example.labadapterview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	String[] alphabet;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        alphabet = new String[]{"Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot"};
        
        ListView lv = (ListView) findViewById(R.id.listview_alphabet);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, alphabet);
        lv.setAdapter(adapter); // Populate the list.
        lv.setOnItemClickListener(listListener);
    }
    
    OnItemClickListener listListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String s = alphabet[arg2];
			Toast.makeText(getBaseContext(), "You have chosen " + s, Toast.LENGTH_LONG).show();
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
