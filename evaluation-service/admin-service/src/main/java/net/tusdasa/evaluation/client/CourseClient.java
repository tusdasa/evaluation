package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.CourseClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.vo.CourseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:23 PM
 */

@FeignClient(value = "evaluation-course-service", path = "/course", fallback = CourseClientImpl.class)
public interface CourseClient {

    @GetMapping("/{courseId}")
    CommonResponse<Course> findByCourseId(@PathVariable("courseId") Long courseId);

    @GetMapping("/")
    CommonResponse<Course> findAllCourse(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                         @RequestParam(name = "size", defaultValue = "20", required = false) Integer size);

    @PostMapping("/")
    CommonResponse<String> updateCourse(@RequestBody CourseRequest request);

    @PutMapping("/")
    CommonResponse<String> createCourse(@RequestBody CourseRequest request);
}
