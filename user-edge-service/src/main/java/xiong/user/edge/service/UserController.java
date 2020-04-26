package xiong.user.edge.service;

import lombok.Data;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Data
class OutputData<T> {
    Integer code;
    String errMsg;
    T data;

    OutputData(T data) {
        code = 200;
        this.data = data;
    }

    OutputData(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }
}

@Data
class LoginInput {
    String username;
    String password;
}

@RestController
public class UserController {

    @Autowired
    MessageInvoker messageInvoker;

    @PostMapping("/login")
    OutputData<String> login(@RequestBody LoginInput loginInput) {
        try {
            String token = messageInvoker.login(loginInput.getUsername(), loginInput.getPassword());
            return new OutputData<String>(token);
        } catch (TException e) {
            e.printStackTrace();
            return new OutputData<>(400, "username or password is incorrect");
        }
    }

//    @PostMapping("/sendEmailCode")
//    OutputData<Object> verifyEmail(@RequestParam("email") String email) {
//
//    }
}
