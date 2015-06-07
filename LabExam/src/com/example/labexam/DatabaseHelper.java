package com.example.labexam;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
	Context ctx;
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// This is where you should create tables.
		// This is where you place your initial data.
		// FORMAT: CREATE TABLE Note (idNumber, integer, fName, lName)
		db.execSQL("CREATE TABLE " + Note.TABLE_NAME +" ("
				+ Note.COLUMN_ID + " integer PRIMARY KEY,"
				+ Note.COLUMN_TITLE + " text,"
				+ Note.COLUMN_CONTENT + " text)");
		// We don't have to do db.close() here. Automatic na sa onCreate.
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}
	
	// Add a Note to the database.
	public void addNote(String title, String content) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		/*	We don't need to put the ID number
			anymore because the SQL autoincrements it starting from 0. */
		// values.put(Note.COLUMN_ID, s.getIdNumber());
		values.put(Note.COLUMN_TITLE, title);
		values.put(Note.COLUMN_CONTENT, content);
		
		db.insert(Note.TABLE_NAME, null, values);
		db.close(); // We need to close this.
	}
	
	// Get the total number of Notes registered in the database.
	public int getNoteCount() {
		// FORMAT: SELECT * FROM Note
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + Note.TABLE_NAME, null);
		
		int x = c.getCount();
		
		c.close();
		db.close();
		return x;
	}
	
	// Get the names of the Notes and place them in an ArrayList<String>
	public ArrayList<String> getNoteTitles() {
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.query(Note.TABLE_NAME,
				new String[] { Note.COLUMN_TITLE },
				null,
				null,
				null,
				null,
				null);
		
		ArrayList<String> titles = new ArrayList<String>();
		
		if(c.moveToFirst()) {
			do {
				String title = c.getString(c.getColumnIndex(Note.COLUMN_TITLE));
				titles.add(title);
			} while(c.moveToNext());
		}
		
		c.close();
		db.close();
		
		return titles;
	}
	
	public String[] getEntireNote(int index) {
		String[] entireNote = {"", ""};
		
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.query(Note.TABLE_NAME,
				new String[] { Note.COLUMN_ID, Note.COLUMN_TITLE, Note.COLUMN_CONTENT },
				null,
				null,
				null,
				null,
				null);
		
		if(c.moveToFirst()) {
			do {
				int cIndex = c.getInt(c.getColumnIndex(Note.COLUMN_ID));
				if(index == cIndex) {
					String title = c.getString(c.getColumnIndex(Note.COLUMN_TITLE));
					String content = c.getString(c.getColumnIndex(Note.COLUMN_CONTENT));
					entireNote[0] = title;
					entireNote[1] = content;
				}
			} while(c.moveToNext());
		}
		
		c.close();
		db.close();
		
		return entireNote;
	}
	
	public void deleteNote(int index) {
		SQLiteDatabase db = getWritableDatabase();
		
		db.delete(Note.TABLE_NAME,
				Note.COLUMN_ID + " =? ",
				new String[]{ String.valueOf(index) }
				);
		db.close();
		
		Toast.makeText(ctx, "DBHelper: Deleting index " + index, Toast.LENGTH_SHORT).show();
	}
	
	public void updateNote(int index, String newTitle, String newContent) {
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(Note.COLUMN_TITLE, newTitle);
		values.put(Note.COLUMN_CONTENT, newContent);
		db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + "=" + index, null);
	}

}
