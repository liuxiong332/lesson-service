package xiong.user.common.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("lesson_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;

    private String name;
    private String email;
    private String phone;
    private String password;

    private Date createTime;
    private Date updateTime;
}
