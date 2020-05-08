FROM openjdk:8-jdk-alpine
COPY ./target/user-service-1.0-SNAPSHOT.jar /user-service.jar
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
EXPOSE 8080/udp

ENV MYSQL_HOST=localhost MYSQL_PORT=3306 REDIS_HOST=localhost REDIS_PORT=6379
ENV MESSAGE_SERVICE_HOST=localhost MESSAGE_SERVICE_PORT=9191
ENV SERVER_PORT=9192
ENTRYPOINT ["java","-jar","/user-service.jar"]