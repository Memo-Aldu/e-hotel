FROM maven:3.8.5 AS maven
LABEL authors="memo, senoussi, ismael"
LABEL MAINTAINER="maldu064@uOttawa.com"
EXPOSE 8080
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package
# For Java 17,
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=ehotelservices-1.0-SNAPSHOT-exec.jar
WORKDIR /opt/app
# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","ehotelservices-1.0-SNAPSHOT-exec.jar"]