mkdir gen_py
thrift -gen py --out gen_py  user_service.thrift
thrift -gen java --out ../user-common/src/main/java user_service.thrift
