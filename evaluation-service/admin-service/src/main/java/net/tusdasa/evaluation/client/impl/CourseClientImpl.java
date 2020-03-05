package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.vo.CourseRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:53 PM
 */

@Component
public class CourseClientImpl implements CourseClient {
    @Override
    public CommonResponse<Course> findByCourseId(Long courseId) {
        return new CommonResponse<Course>().busy();
    }

    @Override
    public CommonResponse<Course> findAllCourse(Integer page, Integer size) {
        return new CommonResponse<Course>().busy();
    }

    @Override
    public CommonResponse<String> updateCourse(CourseRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> createCourse(CourseRequest request) {
        return new CommonResponse<String>().busy();
    }
}
