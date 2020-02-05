package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.ThirdKpi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ThirdKpiMapper extends BaseDao<ThirdKpi, Integer> {

    List<ThirdKpi> findAllBySecondKpiId(Integer secondKpiId);

    List<ThirdKpi> findAllBySecondKpiIdAndThirdKpiIds(Map<String, Object> parameter);

    List<ThirdKpi> findAllBySecondKpiIdsAndThirdKpiIds(Map<String, Object> parameter);

    List<ThirdKpi> findAllBySecondKpiIds(@Param("secondKpiIds") Integer[] secondKpiIds);
}