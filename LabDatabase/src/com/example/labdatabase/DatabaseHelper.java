package com.example.labdatabase;

import java.util.ArrayList;

import com.example.labdatabase.MainActivity;
import com.example.labdatabase.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// This is where you should create tables.
		// This is where you place your initial data.
		// FORMAT: CREATE TABLE STUDENT (idNumber, integer, fName, lName)
		db.execSQL("CREATE TABLE " + Student.TABLE_NAME +" ("
				+ Student.COLUMN_IDNUMBER + " integer PRIMARY KEY,"
				+ Student.COLUMN_FNAME + " text,"
				+ Student.COLUMN_LNAME + " text)");
		// We don't have to do db.close() here. Automatic na sa onCreate.
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}
	
	// Add a student to the database.
	public void addStudent(Student s) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		/*	We don't need to put the ID number
			anymore because the SQL autoincrements it starting from 0. */
		// values.put(Student.COLUMN_IDNUMBER, s.getIdNumber());
		values.put(Student.COLUMN_FNAME, s.getfName());
		values.put(Student.COLUMN_LNAME, s.getlName());
		
		db.insert(Student.TABLE_NAME, null, values);
		db.close(); // We need to close this.
	}
	
	// Get the total number of students registered in the database.
	public int getStudentCount() {
		// FORMAT: SELECT * FROM STUDENT
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + Student.TABLE_NAME, null);
		
		int x = c.getCount();
		
		c.close();
		db.close();
		return x;
	}
	
	// Get the names of the students and place them in an ArrayList<String>
	public ArrayList<String> getStudentNames() {
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.query(Student.TABLE_NAME,
				new String[] {Student.COLUMN_FNAME, Student.COLUMN_LNAME},
				null,
				null,
				null,
				null,
				null);
		
		ArrayList<String> names = new ArrayList<String>();
		
		if(c.moveToFirst()); {
			do {
				String fName = c.getString(c.getColumnIndex(Student.COLUMN_FNAME));
				String lName = c.getString(c.getColumnIndex(Student.COLUMN_LNAME));
				names.add(fName + " " + lName); // Add fName and lName to the ArrayList as one String.
			} while(c.moveToNext());
		}
		
		c.close();
		db.close();
		
		return names;
	}
	
	// Returns all ID numbers registered in the database through an ArrayList<String>
	public ArrayList<String> getStudentID() {
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.query(Student.TABLE_NAME,
				new String[] {Student.COLUMN_IDNUMBER},
				null,
				null,
				null,
				null,
				null);
		
		ArrayList<String> idNumbers = new ArrayList<String>();
		
		if(c.moveToFirst()); {
			do {
				String idNum = c.getString(c.getColumnIndex(Student.COLUMN_IDNUMBER));
				idNumbers.add(idNum);
			} while(c.moveToNext());
		}
		
		c.close();
		db.close();
		
		return idNumbers;
	}
	
	public void deleteStudent(int index) {
		SQLiteDatabase db = getWritableDatabase();
		
		/*	The line of code:
				new String[]{ String.valueOf(MainActivity.idNumbers.get(index)) }
		 	tells the database to delete a person based on the INDEX of the ARRAYLIST pressed.
		 	Notice that inside MainActivity.java, I made the ArrayList a public static array so
		 	that DatabaseHelper.java can access it with ease. */
		db.delete(Student.TABLE_NAME,
				Student.COLUMN_IDNUMBER + " =? ",
				new String[]{ String.valueOf(MainActivity.idNumbers.get(index)) }
				);
		db.close();
	}

}
