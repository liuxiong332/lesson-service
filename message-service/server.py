from gen_py.message_service import MessageService
from thrift.transport import TTransport
from thrift.transport import TSocket
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
import os


class BaseMessageHandler(MessageService.Iface):
    def ping(self):
        print('ping()')

    def sendPhoneMsg(self, phone, msg):
        print("Get Phone {} message {}".format(phone, msg))
        return True

    def sendEmailMsg(self, email, msg):
        print("Get Email {} message {}".format(email, msg))
        return True


if __name__ == "__main__":
    host = os.getenv("SERVER_HOST") or "localhost"
    port = os.getenv("SERVER_PORT") or 9090
    processor = MessageService.Processor(BaseMessageHandler())
    transport = TSocket.TServerSocket(host="127.0.0.1", port=9191)
    transport_factory = TTransport.TBufferedTransportFactory()
    protocol_factory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(
        processor, transport, transport_factory, protocol_factory)

    print("Starting the server...")
    server.serve()
    print("Done.")
