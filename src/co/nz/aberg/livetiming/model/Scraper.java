package co.nz.aberg.livetiming.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class Scraper {
	

	public static ArrayList<String> getData() throws IOException {
		// TODO Auto-generated method stub
		
		Document doc = null;
		
		ArrayList<String> mylistLinks = new ArrayList<String>();
		ArrayList<String> mylist = new ArrayList<String>();
		
//		String url2 = "http://livetiming.co.nz/wsTriSeries.aspx";
//		String url = "http://livetiming.co.nz/data/TriSeries-125237154222.htm?v=1355624095668";
		String baseUrl = "http://livetiming.co.nz";
		String url = baseUrl + "/TimeDatadat.aspx?td=TriSeries";
		
		
		try {
			System.out.println("get");
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return mylist;
//			e.printStackTrace();
		}
		
		
		Elements frames = doc.select("iframe");
			
		String[] iframesrc;
		int iframeCount  = frames.size();
		iframesrc = new String [iframeCount];
		int i=0;
		for(Element e : frames)
		{
		    iframesrc[i] = e.getElementsByTag("iframe").attr("src");
		    i++;
		}
		
		//get iFrame content
		Document [] iframeDoc;
		iframeDoc = new Document[iframeCount];
		int j = 0;
		for (String s : iframesrc){
			s = s.replaceFirst("^.", "");
		    iframeDoc[j] = Jsoup.connect(baseUrl+s).get(); 
		    j++;
		}
	
		
		Elements tableBody = iframeDoc[0].select("table");
		
		Element table = tableBody.get(1).select("table").first();
		
		Elements trs = table.select("tr");
		Iterator trIter = trs.iterator();
		
		while (trIter.hasNext()) {
			
			Element tr = (Element)trIter.next();
			
			Elements tds = tr.select("td");
			Iterator tdIter = tds.iterator();
	
			Boolean odd = false;
			while (tdIter.hasNext()) {
				Element td = (Element)tdIter.next();
				String country = td.text();
				mylist.add(country);
				System.out.println("[" + country + "]");
				String link = td.select("a[href]").attr("abs:href");
				mylistLinks.add(link);
			}
		}
		return mylist;
	
//		System.out.println(doc.title());
//		System.out.println(mylist);
	}

}
