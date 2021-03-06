package xiong.user.edge.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xiong.message.MessageService;
import xiong.user.edge.service.annotation.RetryInit;

import javax.annotation.PostConstruct;

@Component
public class MessageInvoker {
    MessageService.Client client;

    @Value("${message-service.host}")
    String messageServiceHost;

    @Value("${message-service.port}")
    int messageServicePort;

    @PostConstruct
    public void init() {
        try {
            TSocket tSocket = new TSocket(messageServiceHost, messageServicePort);
            tSocket.setConnectTimeout(2000);  // 设置连接的超时时间
            tSocket.open();

            TProtocol protocol = new TBinaryProtocol(tSocket);
            client = new MessageService.Client(protocol);
            client.ping();
            System.out.println("Connect to message service successfully!");
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    void ping() throws TException {
        client.ping();
    }

    @RetryInit
    public void sendEmailMsg(String email, String code) throws TException {
        boolean isSuc = client.sendEmailMsg(email, code);
        if (!isSuc) {
            throw new RuntimeException("Send email failed!");
        }
    }

    @RetryInit
    public void sendPhoneMsg(String phone, String code) throws TException {
        boolean isSuc = client.sendPhoneMsg(phone, code);
        if (!isSuc) {
            throw new RuntimeException("Send email failed!");
        }
    }
}