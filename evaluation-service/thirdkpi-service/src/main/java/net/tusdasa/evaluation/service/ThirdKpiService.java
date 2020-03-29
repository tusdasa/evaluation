package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;

import java.util.List;

public interface ThirdKpiService {
    void addThirdKpi(ThirdKpiRequest request);

    void updateThirdKpi(ThirdKpiRequest request);

    void deleteThirdKpi(Integer thirdKpiId);

    ThirdKpi findThirdKpiById(Integer thirdKpiId);

    List<ThirdKpi> findAll();

    List<ThirdKpi> findAllBySecondKpiId(Integer secondKpiId);

    List<ThirdKpi> findAllBySecondKpiIdAndThirdKpiIds(Integer secondKpiId, IdsRequest thirdKpiIds);

    //Integer[] secondKpiIds, Integer[] thirdKpiIds
    List<ThirdKpi> findAllBySecondKpiIdsAndThirdKpiIds(IdsRequest thirdKpiIds);

    List<ThirdKpi> findAllBySecondKpiIds(IdsRequest secondKpiIds);
}
