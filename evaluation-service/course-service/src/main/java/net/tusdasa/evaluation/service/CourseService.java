package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> findCourseByStudentClassId(Integer classId);

    List<Course> findCourseByStudentClassAndTermId(Integer classId, Integer termId);

    List<Course> findCourseByWorkId(Integer workId);

    List<Course> findAllByPage(Integer page, Integer size);

    Course findCourseById(Long courseId);

    List<Course> findAll();
}
