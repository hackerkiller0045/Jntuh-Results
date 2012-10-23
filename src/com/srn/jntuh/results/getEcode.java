package com.srn.jntuh.results;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class getEcode extends Thread{
	public String ecode;
	ArrayList<String> rhref = new ArrayList<String>();
	int position;
	
	public getEcode(ArrayList<String> rh, int pos){
		rhref = rh;
		position = pos;
		
	}
	public void run(){
		try {
			ecode = getecode(rhref.get(position));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String getecode(String s) throws IOException{
		Document edoc = Jsoup.connect(s).get();
		Elements elem = edoc.getElementsByAttributeValue("id", "ecode");
		String eee = elem.attr("value").toString();
		System.out.println(eee);
		return eee;
	}
	public String returnecode(){
		return ecode;
	}
}
