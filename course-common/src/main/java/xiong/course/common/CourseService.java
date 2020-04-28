package xiong.course.common;

public interface CourseService {
    String sayHello(String name);

    Integer insertCourse(CourseData courseData);

    void deleteCourse(Integer courseId);
}
