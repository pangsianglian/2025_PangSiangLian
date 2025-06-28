# Use an official Java 17 runtime
FROM eclipse-temurin:17-jre

# Set working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/MinCoinExchange-1.0-SNAPSHOT.jar app.jar

# ✅ Copy your config.yml into the container
COPY src/main/resources/config.yml config.yml

# Expose port 8080
EXPOSE 8080

# Command to run your Dropwizard app
ENTRYPOINT ["java", "-jar", "app.jar", "server", "config.yml"]
