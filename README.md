RSS Reader Android Teamproject
===========

Components
----------

1) Home Activity ( List of Feed Sites ) - pull from RSS feed to SQLite

Owner: Jesus

2) Feed Items List Activity - Individual Items from a specific RSS Feed.  List should display Items from RSS feed.  Maybe bold or highlight unread (not clicked) items.  Must update the Feed unread count.
On click of an individual Item should spawn an Intent to go to Item Full Details View.  Must pass Item with Intent.

Owner:

3) Item Full Details View - Once an individual Feed Item is clicked, a new Activity (or Fragment) should be be launched with the Title, Description, Url (and possibly published date) with the ability to launch an Intent to load the Url in a web browser.

Owner:

4) Edit Feed - save to SQLIte

Owner: Jesus

Bonus / Additional Features
5) Search feature for other available RSS sites
6) Swipe to delete feed. Or to mark an item as read.
7) Other features?

--- Update from Jesus ----

Added ItemsActivty.java and activity_items.xml as the next part after clicking on a feed from the main page.  Clicking on a feed on the main page passes a bundle that contains that feed object.

Database can read and write.

Loading an RSS feed from a string url works.


