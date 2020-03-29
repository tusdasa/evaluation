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

    List<SecondKpi> findAllByFirstKpiIdAndSecondKpiIds(Map<String, Object> parameter);

    List<SecondKpi> finAllFirstKpiIdsAndSecondKpiIds(Map<String, Object> parameter);

    List<SecondKpi> findAllByFirstKpiIds(@Param("firstKpiIds") Integer[] firstKpiIds);
}