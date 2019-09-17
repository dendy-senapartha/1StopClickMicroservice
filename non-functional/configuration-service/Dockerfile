FROM openjdk:8-jre-slim

COPY ./target/configuration-service-1.0-SNAPSHOT.jar /usr/src/configuration-service/
WORKDIR /usr/src/configuration-service
EXPOSE 9090
CMD ["java", "-jar", "configuration-service-1.0-SNAPSHOT.jar"]