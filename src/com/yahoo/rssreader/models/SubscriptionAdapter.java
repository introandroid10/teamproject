package com.yahoo.rssreader.models;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yahoo.rssreader.R;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SubscriptionAdapter extends ArrayAdapter<Feed>{
	
	public SubscriptionAdapter(Context context, List<Feed> feeds) {
		super(context, 0, feeds);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view = convertView;
		if(view == null){
			LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.subscription_item, null);
		}
		
		Feed feed = getItem(position);
		
		ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(feed.getImageUrl(), imageView);

		TextView nameView = (TextView) view.findViewById(R.id.tvName);
		String formattedName = new StringBuilder()
				.append("<h2>")
				.append(feed.getName())
				.append("</h2>").toString();
		nameView.setText(Html.fromHtml(formattedName));
		
		TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
		bodyView.setText(Html.fromHtml("<h2>" + feed.getUnreadCount() + "</h2>"));
		
		return view;
	}

}
