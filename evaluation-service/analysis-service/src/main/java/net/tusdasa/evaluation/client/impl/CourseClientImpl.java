package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseClientImpl implements CourseClient {
    @Override
    public CommonResponse<Course> findByCourseId(Long courseId) {
        return new CommonResponse<Course>().busy();
    }
}
