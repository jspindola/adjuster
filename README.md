# adjuster
Adjuster Homework

_Description_

A client wants you to retrieve data from a RESTful API. The HTTP API has two endpoints to gather data . The relationship between the two types of data are that of a parent and child relationship, Campaign is the parent and the Creative is the child. The Creatives contain metric data that can be used to describe the overall performance of the parent Campaign item. 

API Endpoints:
* http://homework.ad-juster.com/api/campaigns GET

* http://homework.ad-juster.com/api/creatives GET 

Problem 1.

Write a HTTP Client, in Java, that pulls the client’s data from the API, and save it locally to a database of choice. 

Problem 2.

Write a database command/query (using your database of choice) to calculate total clicks and views at the campaign level per child creatives. 

Problem 3.

Output the campaign data results from Problem 2 in a CSV file.

Extra Credit: Revenue is calculated using CPM and views.  Using the formula CPM * views / 1000 please include revenue data at the campaign level in the CSV file.




Please email a compressed copy of your source code to homework@ad-juster.com with the subject: HOMEWORK CODE Lastname_FirstName 

Any questions? email homework@ad-juster.com
