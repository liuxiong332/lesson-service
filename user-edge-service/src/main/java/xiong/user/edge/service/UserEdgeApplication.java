package xiong.user.edge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xiong.user.edge.service.annotation.EnableRetryInit;

@SpringBootApplication
@EnableRetryInit
public class UserEdgeApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(UserEdgeApplication.class, args);
    }

    @Autowired
    UserInvoker userInvoker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userInvoker.ping();
        System.out.println("Ping");
    }
}
