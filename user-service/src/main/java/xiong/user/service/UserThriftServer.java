package xiong.user.service;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import xiong.user.UserService;

@Component
public class UserThriftServer implements ApplicationRunner {

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private UserHandler userHandler;

    private void runServer() {
        try {
            UserService.Processor<UserHandler> processor = new UserService.Processor<>(userHandler);

            TServerSocket serverSocket = new TServerSocket(serverPort);
            // TServer server = new TSimpleServer(new TServer.Args(serverSocket).processor(processor));
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverSocket).processor(processor));

            System.out.println(String.format("Starting the user server at %d ...", serverPort));
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        new Thread(this::runServer).start();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        run();
    }
}
