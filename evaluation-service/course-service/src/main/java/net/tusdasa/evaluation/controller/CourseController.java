package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.service.CourseService;
import net.tusdasa.evaluation.vo.CourseRequest;
import org.springframework.web.bind.annotation.*;


@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public CommonResponse<Course> findByCourseId(@PathVariable("courseId") Long courseId) {
        Course course = this.courseService.findCourseById(courseId);
        if (course != null) {
            return new CommonResponse<Course>().ok().data(course);
        }
        return new CommonResponse<Course>().error("不存在");
    }

    @GetMapping("/class/{classId}")
    public CommonResponse<Course> findCourseByClassId(@PathVariable("classId") Integer classId) {
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByStudentClassId(classId));
    }

    @GetMapping("/class/{classId}/term/{termId}")
    public CommonResponse<Course> findCourseByClassIdAndTermId(@PathVariable("classId") Integer classId, @PathVariable("termId") Integer termId) {
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByStudentClassAndTermId(classId, termId));
    }

    @GetMapping("/work/{workId}")
    public CommonResponse<Course> findCourseByWorkId(@PathVariable("workId") Integer workId) {
        return new CommonResponse<Course>().ok().table(this.courseService.findCourseByWorkId(workId));
    }

    @GetMapping("/")
    public CommonResponse<Course> findAllCourse(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                                @RequestParam(name = "size", defaultValue = "20", required = false) Integer size) {
        return new CommonResponse<Course>().ok().table(this.courseService.findAllByPage(page, size));
    }

    @PostMapping("/")
    public CommonResponse<String> updateCourse(@RequestBody CourseRequest request) {
        return new CommonResponse<String>().ok();
    }

    @PutMapping("/")
    public CommonResponse<String> createCourse(@RequestBody CourseRequest request) {
        return new CommonResponse<String>().ok();
    }

}
