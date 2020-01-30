package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DepartmentMapper extends BaseDao<Department,Integer> {
}