FROM openjdk:8-jre-slim
MAINTAINER kamfret

ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar

CMD /usr/bin/java $JAVA_OPTS -jar /app.jar