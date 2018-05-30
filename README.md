# Automobile Maintenance Tracker

## Pre-install

1. install jdk8
    - set `JAVA_HOME` environment variable
1. install MAVEN
    	
1. install MongoDB
    - [Install MongoDB Community Edition](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/)
1. install Angular
	- npm install -g @angular/cli@1.7.4

	
	
## Install

	$ git clone https://github.com/a-hosseini/Automobile-Maintenance-Tracker.git
	

## Run Test Cases
	$ mvn test


## Build & Run

1. Tomcat is the embedded web server in this application.
1. Open the project in an IDE like NetBeans. 
1. Update src/main/resources/application.yml to connect to your MongoDB
	- The application create mydb schema in your MongoDB.
	``` 
		spring:
			data:
				mongodb:
					database: mydb
					host: 172.17.117.175
					port: 27017	
		
	```
1. Build and run the project from the IDE.
1. Use Postman to Get, Post, Put and Delete entities.
1. This is a full stack application and the software works end to end.
1. The UI is build using Angular 5. 
```
	\> cd \<PROJECT_ROOT_FOLDER>\client 
	\> ng serve
```
### Now, UI application is http://localhost:4200 and the server is http://localhost:8080.

### Insert: How to Post a new entity into the ReST endpoint:
  -	http://localhost:8080/car
```json

{
    "vin": "ABC",
    "make": "Honda",
    "model": "Accord",
    "year": 2010,
    "mileage": 100000,
    "color": "Red",
    "trim": "Ex-V6",
	type: "Electric"
}

```

![Post](post.png)



### Find: How to Get an entity from the ReST endpoint:
  - http://localhost:8080/car?VIN={ABC}

![Get](get.png)

### Update: How to Put a new entity into the ReST endpoint:
	- http://localhost:8080/car
```json

{
    "vin": "ABC",
    "make": "Honda",
    "model": "Accord",
    "year": 2010,
    "mileage": 100000,
    "color": "Blue",
    "trim": "Ex-V4",
	type: "Diesel"
}

```

![Put](put.png)


### Delete: How to Delete a new entity into the ReST endpoint:
	- http://localhost:8080/car
```json

{
    "vin": "ABC",
    "make": "Honda",
    "model": "Accord",
    "year": 2010,
    "mileage": 100000,
    "color": "Blue",
    "trim": "Ex-V4",
	type: "Diesel"
}

```
![Delete](delete.png)