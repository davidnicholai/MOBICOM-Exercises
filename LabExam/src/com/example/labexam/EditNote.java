package com.example.labexam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends Activity {
	DatabaseHelper dbHelper;
	Button btnDelete;
	EditText etTitle, etContent;
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_note);
		
		dbHelper = new DatabaseHelper(getBaseContext(), Note.TABLE_NAME, null, 1);
		initializeElements();
		
		Bundle extras = getIntent().getExtras();
		position = extras.getInt("index");
		position += 1;
		
		String[] strArray = dbHelper.getEntireNote(position);
		etTitle.setText(strArray[0]);
		etContent.setText(strArray[1]);
	}
	
	protected void onPause() {
		super.onPause();
		
		dbHelper.updateNote(position, etTitle.getText().toString(), etContent.getText().toString());
	}
	
	public void initializeElements() {
		btnDelete = (Button) findViewById(R.id.button_delete_note);
		btnDelete.setOnClickListener(listenerDelete);
		
		etTitle = (EditText) findViewById(R.id.edittext_editnote_title);
		etContent = (EditText) findViewById(R.id.edittext_editnote_content);
	}
	
	OnClickListener listenerDelete = new OnClickListener() {
		@Override
		public void onClick(View v) {
			dbHelper.deleteNote(position);
			//
			finish();
		}
	};
}
