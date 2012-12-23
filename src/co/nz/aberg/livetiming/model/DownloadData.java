package co.nz.aberg.livetiming.model;

import java.io.IOException;
import java.util.ArrayList;

import android.os.AsyncTask;

public class DownloadData extends AsyncTask<String, Integer, ArrayList<String>>{


	     protected ArrayList<String> doInBackground(String...urls) {
	         try {
				return Scraper.getData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	     }





}
