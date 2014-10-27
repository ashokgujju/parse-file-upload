package com.parse.starter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
/*
		byte[] i = null;
		try {
			i = IOUtils.toByteArray("/sdcard/record101.amr"); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		 File file2 = new File("/sdcard/record101.amr");

		    byte[] i = new byte[(int) file2.length()];
		    
		    InputStream inputStream = null;
		    try {
		    
		        inputStream = new FileInputStream(file2);
		        
		        inputStream.read(i);
		        
		    } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
		        try {
					inputStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		
		
		    ParseObject a = null;	
		   
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Recordings");
		try {
			a = query.get("i0Ko1ufOXQ");
			ParseFile b = (ParseFile) a.get("data");
			byte [] file = b.getData();
			File f = new File("sdcard/as.amr");
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(file);
			fos.close();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		query.findInBackground();
		
		a.deleteInBackground();
		
		final ParseFile pf = new ParseFile("kumar.amr", i);
		pf.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				if (e != null) {
					Log.d("ASHOK", e.getMessage());
				}
				ParseObject ob = new ParseObject("Recordings");
				ob.put("name", "Recording002");
				ob.put("data",pf);
				ob.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException e) {
						if (e != null) {
							Log.d("ASHOK", e.getMessage());
						}
					}
				});
			}
		});	
	}
}