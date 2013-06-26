package com.yahoo.rssreader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.yahoo.rssreader.models.Feed;

public class ItemsActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey("feed")){
				Feed feed = (Feed)bundle.get("feed");
				Log.d("DEBUG", "Feed{" + feed + "}");
				Toast.makeText(this, "Feed{" + feed + "}", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Feed not found in bundle", Toast.LENGTH_SHORT).show();
			}
		
		} else {
			Toast.makeText(this, "Bundle is null", Toast.LENGTH_SHORT).show();
		}
	}

}
