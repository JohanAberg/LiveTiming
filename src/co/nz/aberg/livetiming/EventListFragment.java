package co.nz.aberg.livetiming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import co.nz.aberg.livetiming.dummy.DummyContent;
import co.nz.aberg.livetiming.model.DownloadData;
import co.nz.aberg.livetiming.model.Scraper;

import android.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class EventListFragment extends ListFragment {

	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private Callbacks mCallbacks = sDummyCallbacks;
	private int mActivatedPosition = ListView.INVALID_POSITION;

	public interface Callbacks {

		public void onItemSelected(String id);
	}

	private static Callbacks sDummyCallbacks = new Callbacks() {
//		@Override
		public void onItemSelected(String id) {
		}
	};

	public EventListFragment() {
	}

	
	private class DownloadData extends AsyncTask<String, Void, ArrayList<String>> {
		   protected ArrayList<String> doInBackground(String... urls) {
			   try {
				   	ArrayList<String> data = Scraper.getData();
				   	return data;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      return null;
		   }

		   protected void onPostExecute(ArrayList<String> result) {
//			   System.out.println("DONE:" + result);
//			   Toast.makeText(getActivity(), "DIDIT"+result, Toast.LENGTH_LONG).show();

			// Create the array list of to do items 
			   ArrayList<String> todoItems = new ArrayList < String >(); 
			   
			   // Create the array adapter to bind the array to the listview 
			   ArrayAdapter<String> aa = new ArrayAdapter <String>( getActivity(), android.R.layout.simple_list_item_activated_1, result); 
			   
			   // Bind the array adapter to the listview.
			   setListAdapter(aa);
			   
			   
//			   ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
//			   
//			   HashMap<String, String> map = new HashMap<String, String>();
//			   map = new HashMap<String, String>();          
//			   map.put("id", "2");
//			   map.put("name", "sally");
//			   mylist.add(map);
//			   			   
//			   
//			   SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.listrow,
//			            new String[] {"id", "name"}, new int[] {0, 1});
//			   
//			   setListAdapter(mSchedule);
			   
			   
		   }
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
				R.layout.simple_list_item_activated_1, R.id.text1,
				DummyContent.ITEMS));
		new DownloadData().execute("");
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
		
//		new DownloadData().execute("");
		
	}


	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	public void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
}
