# job-application

## Running the project (IntelliJ)
Clone the repository to your local machine
Rename or make a copy of the example.properties file in src>main>resources and name it application.properties.
The username and the password will need to be updated by the one you want to use for the database user on your local machine.

You'll need to install Kafka. Start Zookeeper and Kafka before you run the application in IntelliJ.

IntelliJ should automatically pickup that this is a Spring Boot application and allow you to start the backend, which runs at localhost:8080, from the configurations menu.

For initial data to show, you'll need to run seeder.sql after you start your application. Then run Angular server. After that, you can visit localhost:4200 to view all the jobs from database.


