package com.yahoo.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RSSReaderDetails extends Activity {
	TextView tvTitle, tvDesc, tvURL;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssreader_details);
		
		tvTitle = (TextView)this.findViewById(R.id.DataTitle);
		tvDesc = (TextView)this.findViewById(R.id.DataDesc);
		tvURL = (TextView)this.findViewById(R.id.DataURL);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rssreader_details, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    
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


}
