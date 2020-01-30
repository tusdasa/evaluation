package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TeacherMapper extends BaseDao<Teacher, Integer> {
    List<Teacher> findAll(Map<String, Integer> parameter);
}