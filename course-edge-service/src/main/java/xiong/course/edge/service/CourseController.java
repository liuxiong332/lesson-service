package xiong.course.edge.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import xiong.course.common.CourseData;
import xiong.course.common.CourseService;

@RestController
public class CourseController {

    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private CourseService courseService;

    @GetMapping("/sayHello")
    String sayHello() {
        return courseService.sayHello("Hello");
    }

    @PostMapping("/courses")
    Integer insertCourse(@RequestBody CourseData courseData) {
        return courseService.insertCourse(courseData);
    }

    @DeleteMapping("/courses/{id}")
    void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}
