FROM openjdk:8-jdk-alpine
COPY ./target/user-edge-service-1.0-SNAPSHOT.jar /user-edge-service.jar

ENV USER_SERVICE_HOST=localhost USER_SERVICE_PORT=9192
ENV MESSAGE_SERVICE_HOST=localhost MESSAGE_SERVICE_PORT=9191
ENV REDIS_HOST=localhost REDIS_PORT=6379
ENV SERVER_PORT=9193
ENTRYPOINT ["java","-jar","/user-edge-service.jar"]