package xiong.user.edge.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Component;
import xiong.user.common.UserService;
import xiong.user.edge.service.annotation.RetryInit;

import javax.annotation.PostConstruct;

@Component
public class UserInvoker {
    UserService.Client client;

    @PostConstruct
    public void init() {
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

    @RetryInit
    public String login(String username, String password) throws TException {
        return client.login(username, password);
    }

    @RetryInit
    public String signup(String username, String email, String phone, String password) throws TException {
        return client.signup(username, email, phone, password);
    }
}