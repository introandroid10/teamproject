package com.yahoo.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yahoo.rssreader.models.Feed;
import com.yahoo.rssreader.models.Item;

public class ItemsActivity extends FragmentActivity {
	
	ArrayAdapter<Item> aItems;
	ListView lvItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			if(bundle.containsKey("feed")){
				Feed feed = (Feed)bundle.get("feed");
//				Log.d("DEBUG", "Feed{" + feed + "}");
//				Toast.makeText(this, "Feed{" + feed + "}", Toast.LENGTH_SHORT).show();
				List<Item> items = feed.items();
				aItems = new ItemsArrayAdapter(this, items);
		        
				lvItems = (ListView) findViewById(R.id.listView1);
		        lvItems.setAdapter(aItems);
		        lvItems.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
						
						Item item = aItems.getItem(position);
						//Toast.makeText(SubscriptionsFragment.this.getActivity(),"CLICKING ON ITEM " + feed, Toast.LENGTH_SHORT).show();
						/*
						 * Use this for Lamberts code
						 */
						Intent intent = new Intent(getApplicationContext(), RSSReaderDetails.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("item", item);
						intent.putExtras(bundle);
						startActivity(intent);
						
						/*
						 * Use this to directly open in WebBrowser
						 */
						//Opening a web browser:
//						Intent webPageIntent = new Intent(Intent.ACTION_VIEW);
//						webPageIntent.setData(Uri.parse(item.getLink()));
//						startActivity(webPageIntent);
					}
				});
			} else {
				Toast.makeText(this, "Feed not found in bundle", Toast.LENGTH_SHORT).show();
			}
		
		} else {
			Toast.makeText(this, "Bundle is null", Toast.LENGTH_SHORT).show();
		}
	}

}
