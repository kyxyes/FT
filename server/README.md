#Backend Usage

##Introduction

The backend of walletdog project includes two parts : 1. core business logic; 2. Web server. 

We use dropwizard as our web server, SQLite as our database.

##Configure

Preparation: Install Maven to your computer.

Procedures:

1. cd into walletdog-core project, using command **mvn install** to install the core-lib to your local maven repository.

2. cd into walletdog-web project, change the file **walletdog.yml** for the location of local database, the server path and port.

##Compile

1. cd into walletdog-web project

2. Use Maven command to compile and build the jar file

```
mvn package
```

The compiled jar file will be in {project_base_dir}/target

##Run

Run this server using java command :

```
java -jar {project_base_dir}/target/walletdog-api-1.0-SNAPSHOT.jar server walletdog.yml
```
