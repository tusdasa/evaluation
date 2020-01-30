package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StudentClassMapper extends BaseDao<StudentClass, Integer> {
    List<StudentClass> findAllByAll(Map<String, Integer> parameter);
}