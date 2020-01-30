package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CourseMapper extends BaseDao<Course, Long> {
    List<Course> findCourseByStudentClass(Integer classId);
    List<Course> findCourseByTeacher(Integer workId);
    int insertClassAndCourse(Map<String,Long> parameter);
}