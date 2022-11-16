FROM maven:3.8-openjdk-17 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17-jdk-slim
COPY --from=build /usr/src/app/target/spring-*-SNAPSHOT.jar spring.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring.jar"]