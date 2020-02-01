package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.ThirdKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ThirdKpiMapper extends BaseDao<ThirdKpi, Integer> {
    List<ThirdKpi> findBySecondKpiId(Integer secondKpiId);
}