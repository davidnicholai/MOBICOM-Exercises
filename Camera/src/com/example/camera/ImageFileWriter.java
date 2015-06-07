package com.example.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

public class ImageFileWriter {

	public static String writeFile(ContentResolver cr, Bitmap bitmap) {
		OutputStream fOut = null;
		
		String path = Environment.getExternalStorageDirectory().toString() + "/Pictures/GroceryManager/";
		createFolder(path);
		
		String generatedName = nameGenerator() + ".jpg";
		File file = new File(path, generatedName);
		
		try {
			fOut = new FileOutputStream(file);
			
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			
			fOut.flush();
			fOut.close();

			MediaStore.Images.Media.insertImage(cr, file.getAbsolutePath(), file.getName(), file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return generatedName;
	}
	
	public static void createFolder(String path) {
		File file = new File(path);
		
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	public static String nameGenerator() {
		String alphabet = "0123456789ABCDE";
	    int N = alphabet.length();
	    String newName = "";
	    
	    Random r = new Random();

	    for (int i = 0; i < 8; i++) {
	        newName += alphabet.charAt(r.nextInt(N));
	    }
	    
	    return newName;
	}
	
}
