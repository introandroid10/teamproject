package com.yahoo.rssreader.fragments;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.yahoo.rssreader.ItemsActivity;
import com.yahoo.rssreader.R;
import com.yahoo.rssreader.models.Feed;
import com.yahoo.rssreader.models.SubscriptionAdapter;


public class SubscriptionsFragment extends Fragment {

	private SubscriptionAdapter adapter;
	private ListView lvSubscriptions ;
	private List<Feed> subscriptions;
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadAdapter();
	}
	
	private void loadAdapter() {

		lvSubscriptions = (ListView) getActivity().findViewById(R.id.lvSubscriptions);
		subscriptions = Feed.getAll();
		adapter = new SubscriptionAdapter(getActivity(), subscriptions);
		lvSubscriptions.setAdapter(adapter);
		
		lvSubscriptions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Feed feed = adapter.getItem(position);
				//Toast.makeText(SubscriptionsFragment.this.getActivity(),"CLICKING ON ITEM " + feed, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(SubscriptionsFragment.this.getActivity(), ItemsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("feed", feed);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		registerForContextMenu(lvSubscriptions);
		
	
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		 MenuInflater inflater = getActivity().getMenuInflater();
		 inflater.inflate(R.menu.context_menu, menu);
		    
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
	        case R.id.delete:
	        	
	        	Feed feed = (Feed) lvSubscriptions.getItemAtPosition(info.position);
	        	Toast.makeText(getActivity(), "Delete feed " + feed.getName(), Toast.LENGTH_SHORT).show();
	        	feed.delete();
	        	adapter.remove(feed);
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_subscriptions, container, false);
	}

	public void addFeed(Feed feed) {
		adapter.add(feed);
	}
	
	
	
}
