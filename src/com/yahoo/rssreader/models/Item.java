package com.yahoo.rssreader.models;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="items")
public class Item extends Model implements Serializable {

	private static final long serialVersionUID = -390001050290424159L;
	@Column(name = "title")
	private String title;
	@Column(name = "link")
	private String link;
	@Column(name = "description")
	private String description;
	@Column(name = "pubDate")
	private String pubDate;
	@Column(name = "guid")
	private String guid;
	@Column(name = "unread")
	private boolean unread = true;
	@Column(name = "Feed")
	private Feed feed;
	@Column(name = "feedUrl")
	private String feedUrl;
	
	public String getFeedUrl(){
		return feedUrl;
	}
	
	public void setFeedUrl(String feedUrl){
		this.feedUrl = feedUrl;
	}
	
	public Feed getFeed(){
		return feed;
	}
	
	public void setFeed(Feed feed){
		this.feed = feed;
		feedUrl = feed.getUrl();
	}
	
	public boolean isUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
