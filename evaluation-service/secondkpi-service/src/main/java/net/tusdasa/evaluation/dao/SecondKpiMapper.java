package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.SecondKpi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SecondKpiMapper extends BaseDao<SecondKpi, Integer> {
    List<SecondKpi> findAllByFirstKpiId(Integer firstKpiId);

    List<SecondKpi> findAllByFirstKpiIds(Map<String, Object> parameter);

    List<SecondKpi> finAllFirstKoiIdsAndIds(Map<String, Object> parameter);

    List<SecondKpi> findAllByFirstKpi(@Param("firstKpiIds") Integer[] firstKpiIds);
}