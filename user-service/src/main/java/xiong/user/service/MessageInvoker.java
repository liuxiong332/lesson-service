package xiong.user.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xiong.message.MessageService;

import javax.annotation.PostConstruct;

@Component
public class MessageInvoker {
    MessageService.Client client;

    @Value("${message-service.host}")
    private String messageServiceHost;

    @Value("${message-service.port}")
    private int messageServicePort;

    @PostConstruct
    void init() {
        try {
            TSocket tSocket = new TSocket(messageServiceHost, messageServicePort);
            tSocket.setConnectTimeout(2000);  // 设置连接的超时时间
            tSocket.open();

            TProtocol protocol = new TBinaryProtocol(tSocket);
            client = new MessageService.Client(protocol);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    void ping() throws TException {
        client.ping();
    }

    void sendEmailMsg(String email, String code) throws TException {
        boolean isSuc = client.sendEmailMsg(email, code);
        if (!isSuc) {
            throw new RuntimeException("Send email failed!");
        }
    }

    void sendPhoneMsg(String phone, String code) throws TException {
        boolean isSuc = client.sendPhoneMsg(phone, code);
        if (!isSuc) {
            throw new RuntimeException("Send email failed!");
        }
    }
}