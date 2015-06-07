package com.example.labdatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {
	DatabaseHelper dbHelper;
	Button btnAdd;
	EditText fname, lname;
	TextView tv;
	ListView lvNames;
	
	// This is public static so DatabaseHelper.java can access it to delete data!
	public static ArrayList<String> idNumbers;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        initializeElements();
    }
    
    public void initializeElements() {
    	dbHelper = new DatabaseHelper(getBaseContext(), "enrolment", null, 1);
    
	    tv = (TextView) findViewById(R.id.tv_count);
	    tv.setText("You have " + String.valueOf(dbHelper.getStudentCount()) + " students.");
	    
	    fname = (EditText) findViewById(R.id.et_fname);
	    lname = (EditText) findViewById(R.id.et_lname);
	    
	    btnAdd = (Button) findViewById(R.id.btnAdd);
	    btnAdd.setOnClickListener(listenerAdd); // So we can add a student!
	
	    lvNames = (ListView) findViewById(R.id.lv_names);
	    lvNames.setOnItemClickListener(clickListener); // So we can delete data!
	    
	    // Update the ListView when the app starts.
		if(dbHelper.getStudentCount() > 0)
			updateListView(); // App will crash if it tries to display 0 students.
    }
    
    OnClickListener listenerAdd = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// Create a new Student based on what was inputed on the EditText fields.
			// And add it to our DatabaseHelper.
			dbHelper.addStudent(new Student(fname.getText().toString(), lname.getText().toString()));
			
			updateTextViewStudentCount();
			updateListView();
		}
	};
	
	OnItemClickListener clickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
			// Tell DatabaseHelper to delete a student depending on the index pressed in the ListView.
			dbHelper.deleteStudent(index);
			
			// Updates the student count TextView on every ListView press.
			updateTextViewStudentCount();
			
			// Update the ListView after doing deleteStudent().
			updateListView();
		}
	};
	
	public void updateTextViewStudentCount() {
		// Updates the student count TextView on every button press.
		tv.setText("You have " + String.valueOf(dbHelper.getStudentCount()) + " students.");
	}
	
	public void updateListView() {
        idNumbers = dbHelper.getStudentID(); // Update the ArrayList on every button press!
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.list_view, idNumbers);
        lvNames.setAdapter(adapter); // Display the updated ArrayList on the ListView.		
	}
	
	// UNNECESSARY CODE BELOW

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
