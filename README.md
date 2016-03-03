BlackJack server application and database setup guide
==============================================

Table of Contents

 1. Requirements

 2. MySQL Setup
 
 3. Application Deployment

	3.1 Deploying application using Eclipse
	
	3.2 Deploying application without Eclipse
	
	3.3 Configuring application
	
==============================================

1. Requirements:
 Tomcat 7+, MySQL 5.5+, Eclipse EE package(recommended to have m2e plugin)

2. MySQL Setup

	You need to create database called 'black'(you may set another name, see configuration section)
	and create table 'users' using sql query described in Application/SQL/createandinsert.sql file. Then, 
	create a database user or grant full privilegies to existing one, remember username and password,
	aswell as database location(adress and port)as these options will be used while configuring 
	application(section 3.3).

3. Application Deployment

	!!!Note that you need to configure application using 3.3 Configuration section before running!!!
	
	3.1 Deploying application using Eclipse
		To open application in Eclipse IDE(EE package), In eclipse Press File>import>Existing project>
	[Application/servletBlackJack location].
	Then, connect Tomcat server to Eclipse, rightclick>Add and remove> drag servletBlackJack
	into Configured>Next.Considering default Tomcat configuration,
	Application will be available at adress localhost:8080/servletBlackJack
	*If your Eclipse doesnt have m2e or there was any issue while adding dependencies, you can manually
	add them(Application/Libraries folder)
	
	===============================
	
	3.2 Deploying application without Eclipse using Tomcat
		To deploy application on Tomcat, put Application/servletBlackJack.war file into
	[Tomcat Location]/webapps folder.Considering default Tomcat configuration, Application will be available  at adress localhost:8080/servletBlackJack
	
	===============================
	
	3.3 Configuring application
		To configure application, You need to open config/db.properties(located in Java Resources/src
	in Eclipse or in WEB-INF/classes/ in WAR file) and set user name, password and host according
	to mysql user that has enough privilegies, and database location. If you have chosen database
	name different from 'black' and/or port different from 3306, you need to set up host as
	jdbc:mysql://[databaseadress]:[port]/[databasename]
	
		If you are deploying application at host different from localhost or with application name,
	different from default(servletBlackJack), you need to open constants.js	in WebContent/_js folder
	and set constant restAdress to "http:[hostadress]:[port]/[ApplicationName]*/rest
	*If you are deploying application under name ROOT, leave ApplicationName empty.

