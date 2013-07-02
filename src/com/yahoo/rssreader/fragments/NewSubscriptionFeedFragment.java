package com.yahoo.rssreader.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yahoo.rssreader.R;
import com.yahoo.rssreader.YahooRSSActivity;

public class NewSubscriptionFeedFragment extends Fragment {
	
	private EditText etRssUrl;
	private Button btnSubmitRssUrl;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_add_new_subscription_feed, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		etRssUrl = (EditText) getActivity().findViewById(R.id.etRssUrl);
		btnSubmitRssUrl = (Button) getActivity().findViewById(R.id.btnSubmitRssUrl);
		
		btnSubmitRssUrl.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				String url = etRssUrl.getText().toString();
				((YahooRSSActivity) getActivity()).loadFeed(url);
			}
			
		});
	}
	
}
