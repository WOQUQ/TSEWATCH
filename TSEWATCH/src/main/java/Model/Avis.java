package Model;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.HTTPRequest;

public class Avis {
	String date;
	String titre;
	String link;
	
	public Avis(String date,String titre,String link){
		this.date = date;
		this.titre = titre;
		this.link = link;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getStr2File() {
		return this.titre+"|"+this.link+"|"+this.date;
	}
	
	// for the HTML
	/**
	 * Merge from DEV_ZHI branch
	 * @param keyWord
	 * @return true : It has the keyword false: it hasn't it
	 */
	public boolean checkKeywordHTML(ArrayList<String> keyword) {

		boolean result = false;
		// send GET request to the link to get the HTML data
		for (String key : keyword) {
			String result_ = null;
			try {
				result_ = HTTPRequest.sendGET(this.link);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String text = null;

			// parse the HTML data to get the text data
			Document doc = Jsoup.parse(result_);
			Elements eles = doc.getAllElements();
			for (Element ele : eles) {
				text += ele.text();
			}
			
			if(text.contains(key)) {
				result = true;
			}
			
		}
		return result;

	}
	
	public void print() {
		System.out.println(this.date);
		System.out.println("titre: "+ titre);
		System.out.println("link: "+link);
		System.out.println("");
	}
}
