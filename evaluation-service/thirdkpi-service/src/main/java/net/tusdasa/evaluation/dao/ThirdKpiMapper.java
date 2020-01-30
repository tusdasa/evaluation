package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.ThirdKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ThirdKpiMapper extends BaseDao<ThirdKpi, Integer> {
}