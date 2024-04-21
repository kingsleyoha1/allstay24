# Base Image for Development
FROM eclipse-temurin:21-jdk-alpine as development

# Install necessary packages, including Maven
RUN apk update \
    && apk add --no-cache curl tar bash nodejs npm \
    && apk add --no-cache maven

# Install nodemon globally for development purposes
RUN npm install -g nodemon

# Prepare the working directory for the app
RUN mkdir /app
WORKDIR /app

# Copy only the POM file first to fetch dependencies
COPY pom.xml ./
# Go offline to download all required Maven dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the application
COPY src ./src
COPY docker-entrypoint.sh ./

# Set the entrypoint script as executable
RUN chmod +x ./docker-entrypoint.sh

# Build the application without running tests to speed up the build
RUN mvn clean install -DskipTests

# Use the entrypoint script to start the application with nodemon
ENTRYPOINT ["./docker-entrypoint.sh"]

##
## Build stage for production
##
FROM eclipse-temurin:21-jdk-alpine as production

ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

# Copy entire project from the development stage
COPY --from=development /app $HOME

# Utilize Maven to build the project
RUN mvn clean package -DskipTests

# Assuming only one jar file with a unique name gets generated
COPY target/*.jar /app/allstay24.jar

# Set the Java command to run the application
ENTRYPOINT ["java", "-jar", "/app/allstay24.jar"]
