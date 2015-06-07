package com.example.labexplicitintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	EditText etName;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etName = (EditText) findViewById(R.id.etInput);
        Button btnIntent = (Button) findViewById(R.id.btnIntent);
        
        btnIntent.setOnClickListener(moveActivity);
    }
    
    OnClickListener moveActivity = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent i = new Intent();
			String name = etName.getText().toString();
			i.putExtra("username", name);
			i.setClass(getBaseContext(), SecondActivity.class);
			startActivityForResult(i, 0);
		}
    	
    };
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode == 0) {
    		data.getExtras().getString("msg");
    		TextView tvMsg = (TextView) findViewById(R.id.tvMsg);
    		tvMsg.setText(data.getExtras().getString("msg"));
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
