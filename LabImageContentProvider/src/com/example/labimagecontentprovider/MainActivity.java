package com.example.labimagecontentprovider;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity {

	GridView gridView;
	SimpleCursorAdapter gridAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gridView = (GridView) findViewById(R.id.grid_view);
        gridAdapter = new SimpleCursorAdapter(
        		getBaseContext(), R.layout.image_cell, null,
        		new String[] { MediaStore.Images.Thumbnails._ID,
        			MediaStore.Images.Thumbnails.DATA },
        		new int[] { R.id.image_text, R.id.image },
        		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        		);
        gridView.setAdapter(gridAdapter);
        getLoaderManager().initLoader(0, null, loaderCallback);
    }
    
    LoaderCallbacks<Cursor> loaderCallback = new LoaderCallbacks<Cursor>() {
    	
    	@Override
    	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    		Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
    		return new CursorLoader(getBaseContext(), uri, null, null, null, null);
    	}
    	
    	@Override
    	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    		gridAdapter.swapCursor(data);    		
    	}
    	
    	@Override
    	public void onLoaderReset(Loader<Cursor> loader) {
    		
    	}
    	
    };

}
