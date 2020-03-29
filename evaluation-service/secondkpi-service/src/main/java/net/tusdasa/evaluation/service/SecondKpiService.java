package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import net.tusdasa.evaluation.vo.SecondKpiRequest;

import java.util.List;

public interface SecondKpiService {

    void addSecondKpi(SecondKpiRequest request);

    void updateSecondKpi(SecondKpiRequest request);

    void deleteSecondKpi(Integer secondKpiId);

    SecondKpi findSecondKpiById(Integer secondKpiId);

    List<SecondKpi> findAll();

    List<SecondKpi> findAllByFirstKpiId(Integer firstKpiId);

    List<SecondKpi> findAllByFirstKpiIdAndSecondKpiIds(Integer firstKpiId, IdsRequest secondKpiIds);

    List<SecondKpi> finAllFirstKpiIdsAndSecondKpiIds(IdsRequest Ids);

    List<SecondKpi> findAllByFirstKpiIds(IdsRequest firstKpiIds);

}
