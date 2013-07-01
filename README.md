RSS Reader Android Teamproject
===========

Components
----------

1) Home Activity ( List of Feed Sites ) - pull from RSS feed to SQLite - DONE
- YahooRSSActivity.java
- activity_yahoo_rss.xml

Owner: Jesus

2) Feed Items List Activity - Individual Items from a specific RSS Feed.  List should display Items from RSS feed.  Maybe bold or highlight unread (not clicked) items.  Must update the Feed unread count.
On click of an individual Item should spawn an Intent to go to Item Full Details View.  Must pass Item with Intent.
- ItemsActivity.java
- activity_items.xml 

Owner: ? Sameer?

3) Item Full Details View - Once an individual Feed Item is clicked, a new Activity (or Fragment) should be be launched with the Title, Description, Url (and possibly published date) with the ability to launch an Intent to load the Url in a web browser.


Owner: Lambert

4) Edit Feed - save to SQLIte - DONE

Owner: Jesus

Bonus / Additional Features
5) Search feature for other available RSS sites
6) Long Click to delete feed. - DONE
7) Or to mark an item as read. 
8) Other features?

--- Update from Jesus ----

06/30/2013
Clicking on the Plus sign to add a feed works now.  Might have issues with incorrect urls.
Long Click on a Feed will delete it from the view and SQLite database.
Added an indefinite progress bar when loading a new feed.

Still need to impelment a Refresh.

------
Added ItemsActivty.java and activity_items.xml as the next part after clicking on a feed from the main page.  Clicking on a feed on the main page passes a bundle that contains that feed object.

Database can read and write.

Loading an RSS feed from a string url works.


