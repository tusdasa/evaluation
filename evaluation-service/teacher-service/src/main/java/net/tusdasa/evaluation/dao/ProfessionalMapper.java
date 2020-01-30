package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Professional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ProfessionalMapper extends BaseDao<Professional, Integer> {
}