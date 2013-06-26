package com.yahoo.rssreader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yahoo.rssreader.fragments.SubscriptionsFragment;
import com.yahoo.rssreader.models.Feed;

public class YahooRSSActivity extends FragmentActivity {

	private SubscriptionsFragment subscriptions;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yahoo_rss);
				
		FragmentManager manager = getSupportFragmentManager();
		subscriptions = (SubscriptionsFragment) manager.findFragmentById(R.id.fragmentSubscriptions);
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
		//TEMP
		Toast.makeText(this, "Clicked on Add Subscription", Toast.LENGTH_SHORT).show();
		
		//PROGRAMATICALLY ADDING A FEED FOR TESTING
//		Feed feed = new Feed();
//		feed.setImageUrl("http://upload.wikimedia.org/wikipedia/en/thumb/4/43/Feed-icon.svg/128px-Feed-icon.svg.png");
//		feed.setName("Engadget");
//		feed.setUrl("http://www.engadget.com/rss.xml");
//		feed.setUnreadCount(new Random().nextInt(10));
//		feed.save();
		Feed.deleteAll();
		Feed feed = Feed.fromRssUrl("http://www.engadget.com/rss.xml");
		//feed.save();
		subscriptions.addFeed(feed);
		
	}

}
