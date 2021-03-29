A Code Challenge 
Base on spring MVC, hibernate, Postgresql 

1. database
the default setting for the database is:
PostgreSQL with the open port of 6543. 
with username: postgres
and password: password 
In application.properties the setting can change.
Although it is not a good approach the save username and password in the properties file, for the project is ok.
the driver of MySQL has been added to the pom file already. if you prefer to use just need to change the URL of the database and username or password in application.properties.
