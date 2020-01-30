package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GradeMapper extends BaseDao<Grade, Integer> {
}