package com.srn.jntuh.results;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class third extends Activity {
	
	ArrayList<String> infolist = new ArrayList<String>();
	String mt;
	
	
	String ecode1;
	EditText htn;
	
	
	TextView sname; 
	TextView sfname;
	WebView wv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		ecode1 = getIntent().getExtras().getString("ecode");
		htn = (EditText) findViewById(R.id.htno);
		//htn.setText(ecode1);
		sname = (TextView) findViewById(R.id.sname);
		sfname = (TextView) findViewById(R.id.sfname);
		wv = (WebView) findViewById(R.id.webView1);
		
		
	}
	
	
	public void getResult(View v){
		String htnu = htn.getText().toString();
		getResults gr = new getResults(htnu, ecode1);
		gr.start();
		try {
			gr.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mt = gr.returnmt();
		infolist = gr.returninfolist();
		sname.setText(infolist.get(1));
		sfname.setText(infolist.get(2));
		wv.loadData(mt, "text/html", null);
	}
   
	

}
