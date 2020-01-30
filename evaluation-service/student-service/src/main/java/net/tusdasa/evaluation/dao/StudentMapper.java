package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StudentMapper extends BaseDao<Student, Long> {
    List<Student> findAll(Map<String, Integer> parameter);
    /*
    List<Student> findAllByClass(Map<String, Integer> parameter);
    List<Student> findAllByDepartment(Map<String, Integer> parameter);
    List<Student> findAllByMajor(Map<String, Integer> parameter);
    List<Student> findAllByGrade(Map<String, Integer> parameter);
     */
}