from gen_py.message_service import MessageService
from thrift.transport import TTransport
from thrift.transport import TSocket
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
import os


class BaseMessageHandler(MessageService.Iface):
    def sendPhoneMsg(self, msg):
        print("Get Phone message %s".format(msg))

    def sendEmailMsg(self, msg):
        print("Get Email message %s".format(msg))


if __name__ == "__main__":
    host = os.getenv("SERVER_HOST") or "localhost"
    port = os.getenv("SERVER_PORT") or 9090
    processor = MessageService.Processor(BaseMessageHandler())
    transport = TSocket.TServerSocket(host=host, port=int(port))
    transport_factory = TTransport.TBufferedTransportFactory()
    protocol_factory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, transport_factory, protocol_factory)

    print("Starting the server...")
    server.serve()
    print("Done.")
