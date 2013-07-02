package com.yahoo.rssreader;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yahoo.rssreader.models.Item;

public class ItemsArrayAdapter extends ArrayAdapter<Item> {

	public ItemsArrayAdapter(Context context, List<Item> items) {
		super(context, R.layout.activity_items, items);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout v = (LinearLayout) vi.inflate(R.layout.feed_item, null);
		//Log.d("test","test");
		Item item = this.getItem(position);
		TextView tvItemTitle = (TextView) v.findViewById(R.id.tvItemTitle);
		//TextView tvItemDesc = (TextView) v.findViewById(R.id.tvItemDescription);
		tvItemTitle.setText(item.getTitle());
		//tvItemDesc.setText(item.getDescription());
		return v;				
	}

}
