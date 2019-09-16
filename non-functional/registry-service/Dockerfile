FROM openjdk:8-jre-slim

COPY ./target/registry-service-1.0-SNAPSHOT.jar /usr/src/registry-service/
WORKDIR /usr/src/gateway-service
EXPOSE 9090
CMD ["java", "-jar", "registry-service-1.0-SNAPSHOT.jar"]