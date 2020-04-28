package xiong.course.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseData implements Serializable {
    Integer courseId;
    String name;
    String description;
    Integer teacherId;
}