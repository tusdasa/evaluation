package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.ThirdKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ThirdKpiMapper extends BaseDao<ThirdKpi, Integer> {

    List<ThirdKpi> findBySecondKpiId(Integer secondKpiId);

    List<ThirdKpi> findBySecondKpiIds(Map<String, Object> parameter);

    List<ThirdKpi> findBySecondKpiIdsAndIds(Map<String, Object> parameter);
}