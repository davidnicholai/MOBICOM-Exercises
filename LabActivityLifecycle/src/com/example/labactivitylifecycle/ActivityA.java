package com.example.labactivitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ActivityA extends Activity {
	
	private final String TAG = "ActivityA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Log.i(TAG, "onCreate()");
        
        Button button1 = (Button) findViewById(R.id.button1);
        // button1.setOnClickListener(goToActB);
    }
    
    // OnClickListener goToActB = new OnClickListener();

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
        Log.i(TAG, "onDestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
        Log.i(TAG, "onPause()");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
        Log.i(TAG, "onRestart()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        Log.i(TAG, "onResume()");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
        Log.i(TAG, "onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
        Log.i(TAG, "onStop()");
	}
    
    
    
}
