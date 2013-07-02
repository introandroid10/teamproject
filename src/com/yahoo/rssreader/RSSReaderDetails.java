package com.yahoo.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.rssreader.models.Item;

public class RSSReaderDetails extends Activity {
	TextView tvTitle;
	WebView tvDesc;
	Item item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssreader_details);
		
		tvTitle = (TextView) this.findViewById(R.id.DataTitle);
		tvDesc = (WebView) this.findViewById(R.id.DataDesc);
		//tvURL = (TextView) this.findViewById(R.id.DataURL);
		
		// Waiting for info from Sameer to get data to put into Details Page
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey("item")){
				item = (Item)bundle.get("item");
				//Toast.makeText(this, "Item{" + item + "}", Toast.LENGTH_SHORT).show();
				// Push results to Text View
				tvTitle.setText(Html.fromHtml("<b>" + item.getTitle() + "</b>"));
				//tvURL.setText(Html.fromHtml(item.getLink()));
				//tvDesc.setText(Html.fromHtml(item.getDescription()));
				tvDesc.loadData(item.getDescription(), "text/html", null);
				
			} else {
				//Toast.makeText(this, "Item not found in bundle of size " + bundle.size(), Toast.LENGTH_SHORT).show();
			}
			
		} else {
			//Toast.makeText(this, "Bundle is null", Toast.LENGTH_SHORT).show();
		}
		
		
		tvTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(item != null && item.getLink() != null){
				//Opening a web browser:
				Intent webPageIntent = new Intent(Intent.ACTION_VIEW);
				webPageIntent.setData(Uri.parse(item.getLink()));
				startActivity(webPageIntent);
				}
			}


		});

		
	}
	


	// Not used: In case there's a need to inflate the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rssreader_details, menu);
		return true;
	}
	
	// Not used: In case we want to add refresh feature on details page
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
	
	// Not used: In case we want to add refresh feature on the details page
	private void refreshFragment() {
		//TEMP
		Toast.makeText(this, "Clicked on Refresh", Toast.LENGTH_SHORT).show();
		
	}


}
