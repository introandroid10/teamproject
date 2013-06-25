package com.yahoo.rssreader.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
