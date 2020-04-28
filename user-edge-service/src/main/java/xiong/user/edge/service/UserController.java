package xiong.user.edge.service;

import lombok.Data;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

@Data
class SignupInput {
    String username;
    String email;
    String phone;
    String code;
    String password;
}

@RestController
public class UserController {

    @Autowired
    private UserInvoker userInvoker;

    @Autowired
    private MessageInvoker messageInvoker;

    @Autowired
    RedisTemplate<String, String> stringRedisTemplate;

    private String genVerifyCode(String name) {
        int randomVal = (int) (Math.random() * 1000000);
        String code = String.format("%06d", randomVal);
        stringRedisTemplate.opsForValue().set(String.format("%s-code", name), code);
        return code;
    }

    @PostMapping("/sendEmailCode")
    OutputData<Object> verifyEmail(@RequestParam("email") String email) {
        try {
            messageInvoker.sendEmailMsg(email, genVerifyCode(email));
        } catch (TException e) {
            return new OutputData<>(500, "Send Verification code failed");
        }
        return new OutputData<>(null);
    }

    @PostMapping("/sendPhoneCode")
    OutputData<Object> verifyPhone(@RequestParam("phone") String phone) {
        try {
            messageInvoker.sendPhoneMsg(phone, genVerifyCode(phone));
        } catch (TException e) {
            return new OutputData<>(500, "Send Verification code failed");
        }
        return new OutputData<>(null);
    }

    @PostMapping("/login")
    OutputData<String> login(@RequestBody LoginInput loginInput) {
        try {
            String token = userInvoker.login(loginInput.getUsername(), loginInput.getPassword());
            return new OutputData<String>(token);
        } catch (TException e) {
            e.printStackTrace();
            return new OutputData<>(400, "username or password is incorrect");
        }
    }

    private String getCodeFromRedis(String name) {
        return stringRedisTemplate.opsForValue().get(String.format("%s-code", name));
    }

    @PostMapping("/signup")
    OutputData<String> signup(@RequestBody SignupInput signupInput) {
        if (signupInput.email != null && signupInput.code.equals(getCodeFromRedis(signupInput.email))
            || signupInput.phone != null && signupInput.code.equals(getCodeFromRedis(signupInput.phone))) {
            try {
                String token = userInvoker.signup(signupInput.getUsername(), signupInput.getEmail(), signupInput.getPhone(), signupInput.password);
                return new OutputData<String>(token);
            } catch (TException e) {
                e.printStackTrace();
                return new OutputData<>(400, "Sig nup failed");
            }
        } else {
            return new OutputData<>(400, "Verify code is not match");
        }
    }
}
