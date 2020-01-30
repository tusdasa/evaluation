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

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public CommonResponse<Course> findById(@PathVariable Long id){
        Course course = this.courseService.findById(id);
        if (course!=null){
            return new CommonResponse<Course>().ok().data(course);
        }else {
            return new CommonResponse<Course>().error("不存在");
        }

    }

    @GetMapping("/class/{id}")
    public CommonResponse<Course> findByClassId(@PathVariable Integer id){
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByStudentClass(id));
    }

    @GetMapping("/work/{id}")
    public CommonResponse<Course> findByWorkId(@PathVariable Integer id){
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByTeacher(id));
    }

}
