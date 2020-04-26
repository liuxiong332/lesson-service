package xiong.user.edge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserEdgeApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(UserEdgeApplication.class, args);
    }

    @Autowired
    MessageInvoker messageInvoker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageInvoker.ping();
        System.out.println("Ping");
    }
}
