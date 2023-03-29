# Installation manual



## Foreword

This project is the backend part of the QuizGame application. It was not meant to be installed with each component separately, but rather via Docker Compose solution of our app, the project of which is available here: https://github.com/realraec/quizgame-docker-compose . We strongly encourage you to follow the installation manual there instead of this one.

All the commands thereafter will have to be typed in Windows Terminal, in the WSL environment you can install following the tutorial to be found here : https://learn.microsoft.com/en-us/windows/wsl/install



## Prerequisites

The first five steps might be skippable -- depending on whether you have already installed the necessary tools for other projects -- but the last two are needed: the app needs a user, a database, for that user to have enough rights on this database, and a running PostgreSQL DBMS to work properly.

* Install git:

  ```
  sudo apt install git
  ```

* Install PostgreSQL 15 and name the main user `postgres`:

  ```
  sudo apt install postgresql-15
  ```

* Install Java 17:

  ```
  sudo apt install openjdk-17-jre
  ```

* Install Maven:

  ```
  sudo apt install maven
  ```

* Install Newman:

  ```
  sudo apt install newman
  ```

* Configure the database:
  ```
  sudo -u postgres createuser dev;
  sudo -u postgres psql -c "ALTER USER dev WITH ENCRYPTED PASSWORD 'password123'";
  sudo -u postgres createdb quizgame -O dev;
  ```

* Start the database:

  ```
  sudo service postgresql start
  ```

With all this done, you should be able to follow the next steps to get a functional backend application running.



## The app itself

* Clone this repository:

  ```
  git clone https://github.com/realraec/quizgame-backend.git .
  ```

* Navigate your way to the root of the application:

  ```
  cd ./quizgame-backend
  ```

* Package the application into a runnable .JAR file:

  ```
  mvn package
  ```

* Start the application:

  ```
  java -jar ./target/quizgame-0.0.1-SNAPSHOT.jar
  ```
  
* Populate the database:

  ```
  newman run initialization.json
  ```

Congratulations, the app is now running and listening on your port 8080, and your database should have been populated.
All of the APIs are accessible via the Swagger here : http://localhost:8080/swagger-ui/index.html -- but keep in mind that most will not work if you are not logged in, and even then, this interface is not supposed to be used to perform requests on its own.



## Known bugs and workarounds

* If one or several of the installations do not work, after the `sudo apt install` commands, try:

  ```
  sudo apt update
  sudo apt upgrade
  ```

* If you cannot configure the database from your CLI, open pgAdmin4 or any other management tool for PostgreSQL, query your main database (right click > query tool), and try:

  ```
  CREATE USER dev;
  ALTER USER dev WITH ENCRYPTED PASSWORD 'password123';
  CREATE DATABASE quizgame WITH OWNER dev;
  ```

* If you encounter a problem and all else fails, please consider opting for the Docker Compose solution of our app, available here:

  https://github.com/realraec/quizgame-docker-compose

