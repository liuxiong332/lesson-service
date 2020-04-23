package xiong.user.service;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xiong.user.service.mapper")
public class UserApplication implements ApplicationRunner {

    @Autowired
    MessageInvoker messageInvoker;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {

            messageInvoker.ping();

            System.out.println("ping()");

            messageInvoker.sendEmailMsg("lx@163.com", "Hello world");
            System.out.println("Send email ");

            messageInvoker.sendPhoneMsg("190", "hello");
            System.out.println("Send phone " );
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
