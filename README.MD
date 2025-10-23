# Demo Spring Boot Project

This is a simple Spring Boot project created with Java 17. It includes a minimal backend and a sample REST endpoint. The project can be used as a starting point for building web applications with Spring Boot.

---

## Features

- Spring Boot 3.5.6
- Java 17
- REST API endpoint
- Minimal setup to quickly start a Spring Boot project
- Frontend integration via static HTML or any frontend framework

---

## Project Structure

demo/
├── src/
│ ├── main/
│ │ ├── java/com/example/demo/ # Java source files
│ │ └── resources/
│ │ └── application.properties
│ └── test/
├── pom.xml # Maven configuration
└── README.md

### Prerequisites

- Java 17 or higher
- Maven 3.9+
- Optional: IDE such as IntelliJ IDEA or VS Code

### Running the Project

1. Clone the repository:

git clone https://github.com/yourusername/demo.git
cd demo
Build the project:

    mvn clean package -DskipTests

Run the Spring Boot app:

    mvn spring-boot:run

Or directly run the jar:

    java -jar target/demo-0.0.1-SNAPSHOT.jar
    
Open a browser and test the API:

http://localhost:8080/hello