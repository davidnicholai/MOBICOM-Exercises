package com.example.labexam;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
	DatabaseHelper dbHelper;
	Button btnAddNote;
	TextView tvCount;
	ListView lvNotes;
	ArrayList<String> noteTitles;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DatabaseHelper(getBaseContext(), Note.TABLE_NAME, null, 1);
        noteTitles = new ArrayList<String>();
        initializeElements();
    }
    
    protected void onResume() {
    	super.onResume();
    	
		updateTextViewNoteCount();
		updateListView();
    }
    
    public void initializeElements() {
    	tvCount = (TextView) findViewById(R.id.textview_count);
    	
    	btnAddNote = (Button) findViewById(R.id.button_add_note);
    	btnAddNote.setOnClickListener(listenerAddNote);
    	
    	lvNotes = (ListView) findViewById(R.id.listview_notes);
    	lvNotes.setOnItemClickListener(listenerEditNote);
    }
    
    OnClickListener listenerAddNote = new OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		startActivity(new Intent().setClass(getBaseContext(), AddNote.class));
    	}
    };

	OnItemClickListener listenerEditNote = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			int position = arg2+1;
			
			Intent i = new Intent();
			i.putExtra("index", position);
			i.setClass(getBaseContext(), EditNote.class);
			startActivity(i);
		}
	};
    
    public void updateTextViewNoteCount() {
		tvCount.setText("You have " + String.valueOf(dbHelper.getNoteCount()) + " notes.");
	}
	
	public void updateListView() {
        noteTitles = dbHelper.getNoteTitles();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.string_item, noteTitles);
        lvNotes.setAdapter(adapter);		
	}
    
}
