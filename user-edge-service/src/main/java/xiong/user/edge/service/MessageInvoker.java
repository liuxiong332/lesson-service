package xiong.user.edge.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Component;
import xiong.user.UserService;

import javax.annotation.PostConstruct;

@Component
public class MessageInvoker {
    UserService.Client client;

    @PostConstruct
    void init() {
        try {
            TSocket tSocket = new TSocket("localhost", 9192);
            tSocket.setConnectTimeout(2000);  // 设置连接的超时时间
            tSocket.open();

            TProtocol protocol = new TBinaryProtocol(tSocket);
            client = new UserService.Client(protocol);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    void ping() throws TException {
        client.ping();
    }

    String login(String username, String password) throws TException {
        try {
            return client.login(username, password);
        } catch (TTransportException e) {
            e.printStackTrace();
            init();
            return login(username, password);
        }
    }

    String signup(String username, String email, String phone, String password) throws TException {
        try  {
            return client.signup(username, email, phone, password);
        } catch (TTransportException e) {
            e.printStackTrace();
            init();
            return signup(username, email, phone, password);
        }
    }
}