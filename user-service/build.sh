cd ../message-common/ && mvn clean install && cd ../user-service
mvn clean package
docker build -t user-service:v1 .
docker run --env MYSQL_HOST=10.0.179.0 --env REDIS_HOST=10.0.179.0 --env MESSAGE_SERVICE_HOST=10.0.179.0 -d -p 9192:9192 user-service:v1