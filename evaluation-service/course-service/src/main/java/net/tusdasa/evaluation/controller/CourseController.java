package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{classId}")
    public CommonResponse<Course> findByCourseId(@PathVariable("classId") Long classId) {
        Course course = this.courseService.findCourseById(classId);
        if (course != null) {
            return new CommonResponse<Course>().ok().data(course);
        }
        return new CommonResponse<Course>().error("不存在");
    }

    @GetMapping("/class/{classId}")
    public CommonResponse<Course> findCourseByClassId(@PathVariable("classId") Integer classId) {
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByStudentClassId(classId));
    }

    @GetMapping("/work/{workId}")
    public CommonResponse<Course> findCourseByWorkId(@PathVariable("workId") Integer workId) {
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByWorkId(workId));
    }

}
