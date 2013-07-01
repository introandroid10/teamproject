package com.yahoo.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class RSSReaderDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssreader_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rssreader_details, menu);
		return true;
	}

}
