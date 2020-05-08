package xiong.user.edge.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xiong.user.UserService;
import xiong.user.edge.service.annotation.RetryInit;

import javax.annotation.PostConstruct;

@Component
public class UserInvoker {
    UserService.Client client;

    @Value("${user-service.host}")
    String userServiceHost;

    @Value("${user-service.port}")
    int userServicePort;

    @PostConstruct
    public void init() {
        try {
            TSocket tSocket = new TSocket(userServiceHost, userServicePort);
            tSocket.setConnectTimeout(2000);  // 设置连接的超时时间
            tSocket.open();

            TProtocol protocol = new TBinaryProtocol(tSocket);
            client = new UserService.Client(protocol);
            client.ping();
            System.out.println("Connect to user service successfully!");
        } catch (TException e) {
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