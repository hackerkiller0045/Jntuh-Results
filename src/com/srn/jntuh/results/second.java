package com.srn.jntuh.results;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.*;

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
			
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				try {
					ecode = getecode(rhref1.get(position));
					
					Intent sintent = new Intent(second.this, third.class);
					sintent.putExtra("ecode", ecode);
					
					startActivity(sintent);
					
					
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		 

	}
	
	
	//methods
	public static String getecode(String s) throws IOException{
		Document edoc = Jsoup.connect(s).get();
		Elements elem = edoc.getElementsByAttributeValue("id", "ecode");
		String eee = elem.attr("value").toString();
		System.out.println(eee);
		return eee;
	}

	
	
	
	
	
}
