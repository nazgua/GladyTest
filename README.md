# GladyTest
The project is a Spring Boot application for the Backend Challenge.

## Description
The project demonstrates how a company can distribute deposits to users, including gift and meal deposits, with proper expiration dates and balance calculations. It showcases various features and design patterns.

## Design Patterns Used
- Factory Design Pattern: The project utilizes the Factory design pattern to create deposits. This pattern helps in creating different types of deposits based on specific type Meal or Gift.
## Data Storage
- Data Simulation: Instead of using a database like H2 Database, the project simulates data using classes that implement repositories and Mockito for few cases. This approach allows for easy testing and flexibility in data manipulation.
## Future Database Integration
- JPA Integration: In the future, the project can be extended to use JPA for database integration. By adding the necessary annotations and implementations, you can seamlessly switch to using a database for persistent storage.
## Testing Framework and Libraries
- Java 11: The project is developed using Java 11, benefiting from its new features and enhancements.
- The project utilizes MapStruct, a powerful mapping framework, to automatically generate mappers between domain objects and DTOs, simplifying the data conversion process.
- Mockito: The project utilizes Mockito, a popular mocking framework, for creating test doubles and performing unit tests.
- JUnit 5: The project employs JUnit 5, the latest version of the JUnit testing framework, for writing unit tests and assertions.
## Prerequisites
Before running the project, ensure you have the following:

- Java 11 installed on your machine.
- Maven (https://maven.apache.org) installed and configured
- Any required dependencies mentioned in the project's build file (e.g., pom.xml for Maven).
## Getting Started
To get started with the project, follow these steps:

1. Clone the repository: git clone https://github.com/nazgua/GladyTest
2. Navigate to the project directory: `cd gladyTest`
3. Build and install the project dependencies: `mvn clean install`
## Additional Notes
- Feel free to explore and modify the project as per your requirements.
- If you encounter any issues or have suggestions for improvement, please open an issue or submit a pull request.
