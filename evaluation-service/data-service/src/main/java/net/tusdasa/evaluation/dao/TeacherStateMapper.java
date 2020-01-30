package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.TeacherState;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherStateMapper extends BaseDao<TeacherState, Integer> {
}