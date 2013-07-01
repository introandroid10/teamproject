package com.yahoo.rssreader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yahoo.rssreader.fragments.NewSubscriptionFeedFragment;
import com.yahoo.rssreader.fragments.SubscriptionsFragment;
import com.yahoo.rssreader.models.Feed;

public class YahooRSSActivity extends FragmentActivity {

	private SubscriptionsFragment subscriptions;
	private NewSubscriptionFeedFragment newSubscriptions;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yahoo_rss);

		subscriptions = new SubscriptionsFragment();
		newSubscriptions = new NewSubscriptionFeedFragment();
		loadSubscriptionFeedFragment();
		
		progressBar = (ProgressBar) findViewById(R.id.pbMain);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.yahoo_rs, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_add_subscription:
	        addSubscriptionActivity();
	    	return true;
	    case R.id.action_refresh:
	    	refreshFragment();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void refreshFragment() {
		//TEMP
		Toast.makeText(this, "Clicked on Refresh", Toast.LENGTH_SHORT).show();
		
	}

	private void addSubscriptionActivity() {
		
		loadAddSubscriptionFeedFragment();		
		
	}

	private void loadSubscriptionFeedFragment() {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		fts.replace(R.id.frameContainer, subscriptions);
		fts.commit();
	}
	
	private void loadAddSubscriptionFeedFragment() {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		fts.replace(R.id.frameContainer, newSubscriptions);
		fts.commit();
	}
	
	public void loadFeed(String url){
		Toast.makeText(this, "Loading RSS Feed", Toast.LENGTH_SHORT).show();
		loadSubscriptionFeedFragment();
		//Feed.deleteAll();
		//There needs to be a better way to do this than sending these objects
		//"http://www.engadget.com/rss.xml"
		Feed.fromRssUrl(url, progressBar, subscriptions); //Async task
	}

}
