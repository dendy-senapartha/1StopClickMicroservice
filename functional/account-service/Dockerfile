FROM openjdk:8-jre-slim

COPY ./target/account-service-1.0-SNAPSHOT.jar /usr/src/account-service/
WORKDIR /usr/src/account-service
EXPOSE 9090
CMD ["java", "-jar", "account-service-1.0-SNAPSHOT.jar"]