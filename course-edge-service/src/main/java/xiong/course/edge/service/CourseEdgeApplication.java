package xiong.course.edge.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xiong.course.common.CourseService;

@SpringBootApplication
public class CourseEdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseEdgeApplication.class, args);
    }
}
