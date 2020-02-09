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
    public List<Course> findCourseByStudentClassId(Integer classId) {
        return this.courseMapper.findCourseByStudentClassId(classId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findCourseByStudentClassAndTermId(Integer classId, Integer termId) {
        Map<String, Integer> parameter = new HashMap<>();
        parameter.put("classId", classId);
        parameter.put("termId", termId);
        return this.courseMapper.findCourseByStudentClassAndTermId(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findCourseByWorkId(Integer workId) {
        return this.courseMapper.findCourseByWorkId(workId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAllByPage(Integer page, Integer size) {
        Map<String, Integer> parameter = new HashMap<>();
        parameter.put("page", page);
        parameter.put("size", size);
        return this.courseMapper.findAll(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public Course findCourseById(Long courseId) {
        return this.courseMapper.selectByPrimaryKey(courseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        return this.courseMapper.findAll();
    }
}
