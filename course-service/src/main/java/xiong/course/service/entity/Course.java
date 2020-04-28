package xiong.course.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("lesson_course")
public class Course {

    @TableId(type = IdType.AUTO)
    private Integer courseId;

    private String name;
    private String description;
    private Integer teacherId;
}
