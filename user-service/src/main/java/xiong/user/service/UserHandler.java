package xiong.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xiong.user.UserService;
import xiong.user.service.entity.User;
import xiong.user.service.mapper.UserMapper;

import java.util.Base64;
import java.util.Date;

@Component
public class UserHandler implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void ping() throws TException {
        System.out.println("ping()");
    }

    private String genCode(User user) throws TException {
        if (user != null) {
            String tokenStr = String.format("%d-%d", user.getUserId(), new Date().getTime());
            return Base64.getEncoder().encodeToString(tokenStr.getBytes());
        }
        throw new TException("The username or password is not valid!");
    }

    @Override
    public String login(String username, String password) throws TException {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("name", username);
        User user = userMapper.selectOne(query);
        return genCode(user);
    }

    @Override
    public String signup(String username, String email, String phone, String password) throws TException {
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.eq("name", username);
        if (userMapper.selectCount(userQuery) > 0) {
            throw new TException("The username has registered!");
        }

        User user = new User(null, username, email, phone, password, new Date(), new Date());
        userMapper.insert(user);
        return genCode(user);
    }
}
