package com.example.labexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNote extends Activity {
	DatabaseHelper dbHelper;
	Button btnCreateNote;
	EditText etTitle, etContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		
		dbHelper = new DatabaseHelper(getBaseContext(), Note.TABLE_NAME, null, 1);
		initializeElements();
	}
	
	public void initializeElements() {
    	btnCreateNote = (Button) findViewById(R.id.button_create_note);
    	btnCreateNote.setOnClickListener(listenerCreateNote);
    	
    	etTitle = (EditText) findViewById(R.id.edittext_addnote_title);
    	etContent = (EditText) findViewById(R.id.edittext_addnote_content);
    }
	
	OnClickListener listenerCreateNote = new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		dbHelper.addNote(etTitle.getText().toString(), etContent.getText().toString());
    		finish();
    	}
    };
}
