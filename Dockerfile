#
# Build stage
#
FROM maven:3-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/pfeApi-0.0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]