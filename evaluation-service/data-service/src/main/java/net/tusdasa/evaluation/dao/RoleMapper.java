package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper extends BaseDao<Role, Integer> {

}