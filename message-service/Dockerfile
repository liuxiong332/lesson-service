FROM thrift as build
WORKDIR /data
COPY message_service.thrift /data/message_service.thrift
RUN mkdir gen_py
RUN thrift -gen py --out gen_py  message_service.thrift

FROM python:3.7-alpine
WORKDIR /app
COPY --from=build /data/gen_py ./gen_py/
COPY requirement.txt ./requirement.txt
RUN pip install -r requirement.txt
COPY server.py server.py
ENV SERVER_HOST=localhost SERVER_PORT=9090
CMD ["python", "-u", "server.py"]
