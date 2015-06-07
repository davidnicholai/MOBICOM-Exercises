package com.example.labexplicitintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
	EditText etMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		String username = getIntent().getExtras().getString("username");
		
		TextView tvName = (TextView) findViewById(R.id.tvName);
		tvName.setText("Hello, " + username);
		
		etMsg = (EditText) findViewById(R.id.etMsg);
		
		Button btnBack = (Button) findViewById(R.id.btnBack);
		
		btnBack.setOnClickListener(back);
	}
	
	OnClickListener back = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent();
			i.putExtra("msg", etMsg.getText().toString());
			setResult(RESULT_OK, i);
			
			finish();
		}
	};
}
