FROM openjdk:8-jre-slim

COPY ./target/authorization-service-1.0-SNAPSHOT.jar /usr/src/authorization-service/
WORKDIR /usr/src/authorization-service
EXPOSE 9090
CMD ["java", "-jar", "authorization-service-1.0-SNAPSHOT.jar"]