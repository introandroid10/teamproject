package com.yahoo.rssreader.models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Subscriptions")
public class RSSFeed {

	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unread_count")
	private int unreadCount;
	
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

}
