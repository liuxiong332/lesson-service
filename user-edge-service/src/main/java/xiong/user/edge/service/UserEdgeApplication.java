package xiong.user.edge.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xiong.user.edge.service.annotation.EnableRetryInit;

@SpringBootApplication
@EnableRetryInit
public class UserEdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserEdgeApplication.class, args);
    }
}
