package xiong.course.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xiong.course.common.CourseData;
import xiong.course.common.CourseService;
import xiong.course.service.entity.Course;
import xiong.course.service.mapper.CourseMapper;

@Service(version = "1.0.0")
@Component
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s]: Hello %S", serviceName, name);
    }

    @Override
    public Integer insertCourse(CourseData courseData) {
        Course course = new Course(null, courseData.getName(), courseData.getDescription(), courseData.getTeacherId());
        courseMapper.insert(course);
        return course.getCourseId();
    }

    @Override
    public void deleteCourse(Integer courseId) {
        courseMapper.deleteById(courseId);
    }
}
