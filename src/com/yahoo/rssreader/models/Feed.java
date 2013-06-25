package com.yahoo.rssreader.models;

import java.util.Iterator;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Feeds")
public class Feed extends Model implements Iterable<Item> {

	@Column(name = "imageUrl")
	private String imageUrl;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unreadCount")
	private int unreadCount;
	
	private List<Item> items;
	
	public List<Item> items(){
		return getMany(Item.class, "Feed");
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

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public static List<Feed> getAll() {
		return new Select()
				.from(Feed.class)
				.execute();
	}

}
