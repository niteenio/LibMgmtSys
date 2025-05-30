# LibMgmtSys

Library Management System (LMS)

This is a simple Library Management System built using Java, Spring Boot, Spring Data JPA, MySQL, and Spring Security. It allows users to perform CRUD operations on books with proper exception handling and response structure.

Technologies Used

Java 17 / 21
Spring Boot
Spring Data JPA (Hibernate)
MySQL
Spring Security (Basic Auth)
Mockito (for unit testing)
JUnit 5
Maven
Prerequisites

Before running the project, make sure you have:

Java 17+ installed
MySQL installed and running
Maven installed
Project Setup

1.Clone the repository: ```bash git clone https://github.com/your-username/library-management-system.git cd library-management-system

2.**Configure the database spring.application.name=lms

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/lmsdb
spring.datasource.username=root
spring.datasource.password=root

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.jpa.properties.hibernate.format_sql=true
springdoc.swagger-ui.path=/swagger-ui.html

springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
Run the application

The application will start at: http://localhost:8080

This project uses Basic Authentication. Use the following credentials: Username: admin Password: password

Endpoints may require Basic Auth in the header for the PostMan tool testing

POST http://localhost:8080/saveBooks { "title": "Let us C", "author": "Yashwant Kanetkat", "publicationYear": "2025-05-11" }

GET http://localhost:8080/getAllBooks GET http://localhost:8080/book/2 PUT http://localhost:8080/book/1 DELETE http://localhost:8080/book/3

Unit tests are written using JUnit 5 and Mockito.

I tried for Swagger but unfortunately it is giving 500 error http://localhost:8080/swagger-ui/index.html

Contact:

Created by Niteen R Panchal. For issues or contributions, feel free to open a pull request or issue. email: niteenpanchal777@gmail.com
