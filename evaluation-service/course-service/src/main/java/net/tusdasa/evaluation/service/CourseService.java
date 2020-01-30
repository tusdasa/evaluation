package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findCourseByStudentClass(Integer classId);
    List<Course> findCourseByTeacher(Integer workId);
    List<Course> findAll(Integer page, Integer size);
    Course findById(Long courseId);
}
