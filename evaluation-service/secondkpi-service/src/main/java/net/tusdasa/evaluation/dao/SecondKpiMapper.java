package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.SecondKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SecondKpiMapper extends BaseDao<SecondKpi, Integer> {
    List<SecondKpi> findAllByFirstKpiId(Integer firstKpiId);
}