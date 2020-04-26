thrift -gen java --out src/main/java user_service.thrift
mkdir -p ../user-common/src/main/java
thrift -gen java --out ../user-common/src/main/java user_service.thrift
