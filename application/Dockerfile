# Use the official OpenJDK image as a base
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build artifact to the container
COPY target/iot-monitoring-0.0.1-SNAPSHOT.jar iot-monitoring.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "iot-monitoring.jar"]
