package net.tusdasa.evaluation.vo;

import net.tusdasa.evaluation.entity.Course;

public class CourseRequest implements CommonRequest<Course> {
    @Override
    public Course build() {
        return null;
    }

    @Override
    public boolean isUpdateRequest() {
        return false;
    }

    @Override
    public boolean isCreateRequest() {
        return false;
    }
}
