package co.nz.aberg.livetiming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class EventListActivity extends FragmentActivity implements
		EventListFragment.Callbacks {

	private boolean mTwoPane;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		if (findViewById(R.id.event_detail_container) != null) {
			mTwoPane = true;
			((EventListFragment) getSupportFragmentManager().findFragmentById(
					R.id.event_list)).setActivateOnItemClick(true);
		}
	}

//	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(EventDetailFragment.ARG_ITEM_ID, id);
			EventDetailFragment fragment = new EventDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.event_detail_container, fragment).commit();

		} else {
			Intent detailIntent = new Intent(this, EventDetailActivity.class);
			detailIntent.putExtra(EventDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
