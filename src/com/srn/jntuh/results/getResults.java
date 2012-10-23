package com.srn.jntuh.results;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getResults extends Thread {
	public String mt;
	ArrayList<String> infolist = new ArrayList<String>();
	public String htno;
	public String ecode;
	
	
	public getResults(String htn, String ecde){
		htno = htn;
		ecode = ecde;
	}
	public void run(){
		getresults(htno, ecode);
	}
	
	
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
			// to do stuff catach error invalid hallticket
			// network error etc

		}
	}
	public String returnmt(){
		return mt;
	}
	public ArrayList<String> returninfolist(){
		return infolist;
	}

}
