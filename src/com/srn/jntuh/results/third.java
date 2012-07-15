package com.srn.jntuh.results;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		sname = (TextView) findViewById(R.id.sname);
		sfname = (TextView) findViewById(R.id.sfname);
		wv = (WebView) findViewById(R.id.webView1);
		
		
	}
	
	
	public void getResult(View v){
		String htnu = htn.getText().toString();
		try {
			getresults(htnu, ecode1);
			sname.setText(infolist.get(1));
			sfname.setText(infolist.get(2));
			wv.loadData(mt, "text/html", null);
		} catch (Exception e) {
			e.printStackTrace();
			sname.setText("invalid hallticket");
		}
		
	}
	
	//methods
	public void getresults(String htno, String ecode){
		
		Document resdoc;
		try {
			resdoc = Jsoup.connect("http://jntuh.ac.in/results/htno/"+htno+"/"+ecode).get();
			Elements tables = resdoc.select("table");
			Elements infotable = tables.get(0).select("td");
			Elements markstable = tables.get(1).select("tr");
			for(int ii=0; ii<markstable.size();ii++){
				tables.get(1).select("tr").get(ii).select("td").get(0).remove();
			}
			mt = "<html><head><style> td{font-size:16px; padding:3px; background:#ccc;}</style></head><body> <table>" + tables.get(1).html() + "</table></body></html>";		
			for(Element elem : infotable){			
				infolist.add(elem.text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		
		
	}

}
