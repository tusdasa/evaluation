package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentClass;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentClassMapper extends BaseDao<StudentClass, Integer> {
}