FROM openjdk:8-jre-slim

COPY ./target/gateway-service-1.0-SNAPSHOT.jar /usr/src/gateway-service/
WORKDIR /usr/src/gateway-service
EXPOSE 9090
CMD ["java", "-jar", "gateway-service-1.0-SNAPSHOT.jar"]