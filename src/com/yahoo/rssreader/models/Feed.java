package com.yahoo.rssreader.models;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yahoo.rssreader.fragments.SubscriptionsFragment;

@Table(name = "feeds")
public class Feed extends Model implements Iterable<Item>, Serializable {

	private static final long serialVersionUID = 8434359398233736703L;

	@Column(name = "url")
	private String url;

	@Column(name = "imageUrl")
	private String imageUrl;

	@Column(name = "name")
	private String name;

	@Column(name = "unreadCount")
	private int unreadCount;

	private List<Item> items = new ArrayList<Item>();

	public List<Item> items(){
		items = new Select()
		.from(Item.class)
		.where("feedUrl = '" + this.url + "'" )
		.execute();
		return items;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getName() {
		return name;
	}

	public int getUnreadCount() {
		return unreadCount;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Item getItem(int index){
		return items().get(index);
	}

	@Override
	public void delete() {
		super.delete();
		
		new Delete()
		.from(Item.class)
		.where("feedUrl = '" + this.url + "'" )
		.execute();
		
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public static List<Feed> getAll() {
		
		return new Select()
		.from(Feed.class)
		.execute();
	}
	
	public static void deleteAll(){
		new Delete().from(Item.class).execute();
		new Delete().from(Feed.class).execute();
	}

	public static Feed fromRssUrl(String url, final ProgressBar progressBar, final SubscriptionsFragment subscriptions){
		AsyncHttpClient client = new AsyncHttpClient();
		final Feed feed = new Feed();
		feed.setUrl(url);

		if(progressBar != null){
			progressBar.setVisibility(ProgressBar.VISIBLE);
		}
		
		client.get(url, new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(String response) {
				// Successfully got a response
				Log.d("HTTP", "onSuccess: " + response);

				try {
					parseXml(response, feed);
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				feed.save();
				for(Item item : feed){
					item.save();
				}

			}

			@Override
			public void onFailure(Throwable e, String response) {
				// Response failed :(
				Toast.makeText(null, "Could not load RSS Feed", Toast.LENGTH_SHORT).show();
				super.onFailure(e, response);
			}
			
			@Override
			public void onFinish() {
				
				if(progressBar != null){
					progressBar.setVisibility(ProgressBar.INVISIBLE);
				}				
				
				if(subscriptions != null){
					subscriptions.addFeed(feed);
				}
				
				super.onFinish();				
			}

		});

		return feed;
	}

	private static void parseXml(String response, Feed feed) throws SAXException, XmlPullParserException, IOException{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(false);
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(new StringReader(response));

		int eventType = parser.getEventType();
		String tagName;

		while(eventType != XmlPullParser.END_DOCUMENT){

			if(eventType == XmlPullParser.START_DOCUMENT) {
				Log.d("DEBUG", "Start document");

			} else if(eventType == XmlPullParser.START_TAG) {
				tagName = parser.getName();
				Log.d("DEBUG", "Start tag "+ tagName);

				if(tagName.equals("title")){
					parser.next();
					if(parser.getEventType() == XmlPullParser.TEXT){
						feed.setName(parser.getText());
					}

				} else if(tagName.equals("link")){
					parser.next();
					if(parser.getEventType() == XmlPullParser.TEXT){
						feed.setUrl(parser.getText());
					}
					
				} else if(tagName.equals("image")){

					while(eventType != XmlPullParser.END_TAG && eventType != XmlPullParser.END_DOCUMENT){
						eventType = parser.next();

						if(eventType == XmlPullParser.START_TAG && parser.getName().equals("url")){
							feed.setImageUrl(parser.nextText());
						}
					}


				} else if(tagName.equals("item")){

					Item item = new Item();
					int depthNumber = parser.getDepth();

					while(eventType != XmlPullParser.END_DOCUMENT){					

						if(eventType == XmlPullParser.START_TAG){
							if(parser.getName().equals("title")){
								parser.next();
								if(parser.getEventType() == XmlPullParser.TEXT){
									item.setTitle(parser.getText());
								}

							} else if(parser.getName().equals("link")){
								parser.next();
								if(parser.getEventType() == XmlPullParser.TEXT){
									item.setLink(parser.getText());
								}

							} else if(parser.getName().equals("description")){
								parser.next();
								if(parser.getEventType() == XmlPullParser.TEXT){
									item.setDescription(parser.getText());
								}

							} else if(parser.getName().equals("pubDate")){
								parser.next();
								if(parser.getEventType() == XmlPullParser.TEXT){
									item.setPubDate(parser.getText());
								}

							} else if(parser.getName().equals("guid")){
								parser.next();
								if(parser.getEventType() == XmlPullParser.TEXT){
									item.setGuid(parser.getText());
								}

							} 	   
							
						} 
						
						eventType = parser.next();
						if(parser.getEventType() == XmlPullParser.END_TAG && depthNumber == parser.getDepth()){
							break;
						}
					}

					feed.addItem(item);
					feed.unreadCount++;
				}

			} 

			eventType = parser.next();

		}
		Log.d("DEBUG", "End document");

	}
	
	private void addItem(Item item) {
		
		item.setFeed(this);
		items.add(item);
		
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Name:").append(name).append(",url:").append(url).append(",unread:").append(unreadCount);
		return sb.toString();
	}

}
