package com.example.labsplitactionbar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


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
        switch(id) {
        case R.id.actions_email:
        	Intent i = new Intent();
        	i.setClass(getBaseContext(), MessagesActivity.class);
        	i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        	startActivity(i);
        	break;
        case R.id.actions_upload:
        	Intent j = new Intent();
        	j.setClass(getBaseContext(), UploadActivity.class);
        	j.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        	j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        	startActivity(j);
        	break;
        case R.id.actions_search:
        	Fragment blankfrag = new BlankFragment();
        	FragmentTransaction ft = getFragmentManager().beginTransaction();
        	ft.replace(R.id.container, blankfrag);
        	ft.commit();
        	break;
        case R.id.actions_settings:
        	Fragment blankfrag2 = new SettingsFragment();
        	FragmentTransaction ft2 = getFragmentManager().beginTransaction();
        	ft2.replace(R.id.container, blankfrag2);
        	ft2.commit();
        	break;
        }
        return super.onOptionsItemSelected(item);
    }
}
