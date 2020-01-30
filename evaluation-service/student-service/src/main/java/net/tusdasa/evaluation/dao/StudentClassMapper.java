package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentClassMapper extends BaseDao<StudentClass, Integer> {
}