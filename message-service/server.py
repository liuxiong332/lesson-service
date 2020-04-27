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
    host = os.getenv("SERVER_HOST") or "127.0.0.1"
    port = os.getenv("SERVER_PORT") or 9191
    processor = MessageService.Processor(BaseMessageHandler())
    transport = TSocket.TServerSocket(host=host, port=port)
    transport_factory = TTransport.TBufferedTransportFactory()
    protocol_factory = TBinaryProtocol.TBinaryProtocolFactory()

    # server = TServer.TSimpleServer(
    #     processor, transport, transport_factory, protocol_factory)

    # server = TServer.TThreadedServer(processor, transport, transport_factory, protocol_factory)
    server = TServer.TThreadPoolServer(processor, transport, transport_factory, protocol_factory)

    print("Starting the server in {}:{}...".format(host, port))
    server.serve()
    print("Done.")
