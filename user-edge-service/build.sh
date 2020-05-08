cd ../message-common/ && mvn clean install && cd ../user-edge-service
cd ../user-common/ && mvn clean install && cd ../user-edge-service
mvn clean package
docker build -t user-edge-service:v1 .
docker run --env USER_SERVICE_HOST=10.0.179.0 --env MESSAGE_SERVICE_HOST=10.0.179.0 --env REDIS_HOST=10.0.179.0 -d -p 9193:9193 user-edge-service:v1