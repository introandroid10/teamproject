package com.yahoo.rssreader.fragments;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
				intent.putExtra("test", "testValue");
				startActivity(intent);
				
			}
		});
		
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
