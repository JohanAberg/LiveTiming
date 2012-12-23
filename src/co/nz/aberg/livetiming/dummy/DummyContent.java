package co.nz.aberg.livetiming.dummy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import co.nz.aberg.livetiming.model.Scraper;


public class DummyContent {


	
	
//	public static ArrayList<String> dataList;
		
//	private static void doScrape() {
//		
//		
//		try {
//			System.out.println("scraper");
//			dataList = Scraper.getData();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
	
	public static class DummyItem {

		public String id;
		public String content;
		
		
		
		public DummyItem(String id, String content) {
			this.id = id;
			this.content = content;
						
		}

		@Override
		public String toString() {
			return content;
		}
	}

	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		addItem(new DummyItem("1", " Tri-Series"));
		addItem(new DummyItem("2", "NZSBK 2012 Championship"));
		addItem(new DummyItem("3", "Vic Club Winter Series"));
		
//		dataList = new ArrayList<String>();
//		doScrape();
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
	
		
}
