# vnairlines-backend-core
# Java 21 Installation

Make sure Java 21 is installed:

On Windows:

Download from: https://jdk.java.net/21/

Add JAVA_HOME and update PATH accordingly.

To verify: java -version

#Edit Database Config in application.properties

Path: src/main/resources/application.properties
Replace your db information

# Build project
./mvn clean install

# Run project
./mvn spring-boot:run


# API Access

After running:

Base URL: http://localhost:8080

Health check: GET /actuator/health

Example: GET /api/flights