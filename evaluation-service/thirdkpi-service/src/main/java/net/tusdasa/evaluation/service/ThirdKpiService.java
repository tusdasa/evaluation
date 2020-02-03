package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;

import java.util.List;

public interface ThirdKpiService {
    void addThirdKpi(ThirdKpiRequest request);

    void updateThirdKpi(ThirdKpiRequest request);

    void deleteThirdKpi(Integer thirdKpiId);

    ThirdKpi findThirdKpiById(Integer thirdKpiId);

    List<ThirdKpi> findAll();

    List<ThirdKpi> findBySecondKpiId(Integer secondKpiId);

    List<ThirdKpi> findBySecondKpiIds(Integer secondKpiId, Integer[] thirdKpiIds);

    List<ThirdKpi> findBySecondKpiIdsAndIds(Integer[] secondKpiIds, Integer[] thirdKpiIds);
}
