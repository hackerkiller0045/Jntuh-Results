package com.srn.jntuh.results;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class second extends Activity {
	
	String ecode;
	
	ArrayList<String> rtext1 = new ArrayList<String>();
	ArrayList<String> rhref1 = new ArrayList<String>();
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		rtext1 = getIntent().getExtras().getStringArrayList("rtext");
		rhref1 = getIntent().getExtras().getStringArrayList("rhref");
		ListView listview;
		listview = (ListView) findViewById(R.id.mylist);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, rtext1);
		
		
		listview.setAdapter(adapter); 
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	        	//to do stuff
				getEcode getec = new getEcode(rhref1, position);
				
				getec.start();
				try {
					getec.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ecode = getec.returnecode();
				Intent sintent = new Intent(second.this, third.class);
				sintent.putExtra("ecode", ecode);
				startActivity(sintent);
				
			}
		});
		
		 

	}
	
	
	//methods
	

	
	
	
	
	
}
