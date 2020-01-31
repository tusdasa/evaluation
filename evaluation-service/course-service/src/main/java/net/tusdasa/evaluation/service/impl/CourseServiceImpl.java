package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.CourseMapper;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findCourseByStudentClass(Integer classId) {
        return this.courseMapper.findCourseByStudentClass(classId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findCourseByTeacher(Integer workId) {
        return this.courseMapper.findCourseByTeacher(workId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll(Integer page, Integer size) {
        Map<String, Integer> parameter = new HashMap<>();
        parameter.put("page", page);
        parameter.put("size", size);
        return this.courseMapper.findAll(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(Long courseId) {
        return this.courseMapper.selectByPrimaryKey(courseId);
    }
}
