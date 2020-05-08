docker build -t message-service:v1 .
docker run -d -p 9191:9191 message-service:v1