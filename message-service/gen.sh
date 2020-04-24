mkdir -p gen_py
thrift -gen py --out gen_py  message_service.thrift
mkdir -p ../message-common/src/main/java
thrift -gen java --out ../message-common/src/main/java message_service.thrift
