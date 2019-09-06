# A VPN back-end server

 ##Overview

* Project Technical Overview:

This application is developed in Spring Framework by using Spring Boot, Spring Data, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.


* Project Business Rules:

 1. Object: User, Server, Region, Authority.

 2. Relationships:
 
    1. One user could have one Authority role.
    
    1. One region could have many servers.
    
    1. One user could place one order each time.
    
* Project Approach:

    1. Created User, Server, Region, Authority.

    1. Used Hibernate to do the database schema migration

    1. Used JDBC to connect project with Postgres

    1. Configured Spring Security for Authentication

    1. Created repository, service and did test

    1. Did mock test for AWS S3 Storage service

    1. Created Controllers and Restful APIs

    1. Integrated third-party application AWS SQS and did Mock test

    1. Used Postman to interact with back-end project

    1. Package my project into a Docker image

## Configure local environment

```
docker pull postgres

docker run --name ${PostgresContainerName} -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p ${hostport}:${containerport} -d postgres
```
## Environment properity configuration

```
location:./src/main/resources/META-INF/env
   
Template:
database.driverName=${driverName}
database.url=${url}
database.port=${port}
database.name=${name}
database.username=${username}
database.password=${password}
   
mvn compile -Dspring.profiles.active=${env}
```

## Flyway migration

```$xslt
mvn compile flyway:migrate -P unit -Ddb_username=${username} -Ddb_url=localhost:${containerport}/${databasename} -Ddb_password=${password} 
```

## Testing
* Package and install the folder before unit test.

```mvn clean compile install -DskipTests=true```
* Tests are done using JUnit and Mockito. Tests are run using the command:
```$xslt
mvn compile test -Dspring.profiles.active=${env} -P ${env}
mvn compile test -Dspring.profiles.active=${unit} -Daws.region=${region} -Ddb_url=${localhost:5432/pigge_unit} -Ddb.username=${username} -Ddb.password=${password} 
```
```$xslt
location:./src/main/resources/META-INF/env

Template:
database.driverName=${driverName}
database.url=${url}
database.port=${port}
database.name=${name}
database.username=${username}
database.password=${password}
```

## Create war package file
```$xslt
mvn compile package -P dev -DskipTests=true
```
## Reference Demo
### User sign up
```$xslt
POST - http://localhost:8080/api/users/signup
```
 Requestbody
 ```$xslt
{
	"email":"lbj@gmail.com",
	"firstName":"Lebron ",
	"lastName":"James",
	"username":"lbj",
	"password":"123456"
}
```

 Responsebody
```$xslt
{
    "id": 18,
    "email": "lbj@gmail.com",
    "password": "$2a$10$18Jfowx2u1g2yjgb39qhG.wWxveqiao0tuccldphWAERavGuoIeFO",
    "lastName": "James",
    "firstName": "Lebron ",
    "username": "lbj"
}
```
Postman snapshoot for user sign up

![](https://vpnlyu-dev.s3.amazonaws.com/Screen+Shot+2019-09-05+at+20.09.19.png)
### User login

```$xslt
POST http://localhost:8080/api/users/login
```
 Requestbody
 ```$xslt
{
	"username" : "lbj",
	"password" : "123456"
}
```
Responsebody
```$xslt
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsYmoiLCJjcmVhdGVkIjoxNTY3NzI4ODIwNjA3LCJleHAiOjE1Njc4MTUyMjB9.xk0Yy-XXuubz6BaFdsB9fRJ4IM-cqtrcmi83npqepCZA842rW3HhBEfzxVIFHDIbeKNEAWKxvVBXTUO5Dz-rDg"
}
```

Postman snapshoot for user login
![](https://vpnlyu-dev.s3.amazonaws.com/Screen+Shot+2019-09-05+at+20.14.29.png)

### post image to AWS S3

```$xslt
POST http://localhost:8080/api/image
```
Postman snapshoot for uploading a image to S3

![](https://github.com/di1025/NationalResortBooking/blob/master/READMESnapshoot/AWS%20S3.png?raw=true)

### send message to AWS SQS

```$xslt
POST http://localhost:8080/api/message
```

Postman snapshoot for sending messages to SQS
![](https://github.com/di1025/NationalResortBooking/blob/master/READMESnapshoot/SQS%20consumer.png?raw=true)
