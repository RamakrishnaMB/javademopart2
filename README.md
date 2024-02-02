steps to run the project:

1.To run database go to folder TicketArtifcat\docker-compose.yml , open this folder- TicketArtifcat in terminal then run this command : docker compose up -d

2.java run in through terminal 

W:\BenchWork\SpringBootLearning\tickets\tickets> ./mvnw spring-boot:run


to run web api in java - intellij configuration

    Open the "Run/Debug Configurations" Dialog:
        Click on the dropdown menu next to the run configuration in the top-right corner of the IntelliJ IDEA window.
        Select "Edit Configurations."

    Create a New Maven Configuration:
        Click on the "+" icon to create a new configuration.
        Choose "Maven" from the list of configurations.

    Configure the Maven Run Configuration:
        Name: Enter a name for your configuration (e.g., "Spring Boot").
        Command line: Enter spring-boot:run in the "Command line" field.

    Apply and Run:
        Click "Apply" and then "OK" to close the dialog.
        Now, you should see your new Maven configuration in the dropdown menu next to the run configuration.
        Select it and click the green "Run" button to run your Spring Boot application.


  
To check PostgreSQL Data in Docker container

1. Once you run "docker compose up -d" in terminal, check PostgrsSql container is running
2. To enter PostgreSQL shell, use this command : docker exec -it tickets-postgres-1 psql -U pluralsight -d pluralsight
3. In the shell use "\dt" see that table created through seed data
4. use following queies to query data in it
   select * from events;

3.To acces the swagger use : http://localhost:9090/swagger-ui/index.html
