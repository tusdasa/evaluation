package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.SecondKpiRequest;

import java.util.List;

public interface SecondKpiService {

    void addSecondKpi(SecondKpiRequest request);

    void updateSecondKpi(SecondKpiRequest request);

    void deleteSecondKpi(Integer secondKpiId);

    SecondKpi findSecondKpiById(Integer secondKpiId);

    List<SecondKpi> findAll();

    List<SecondKpi> findAllByFirstKpi(Integer firstKpiId);

    List<SecondKpi> findAllByFirstKpiAndIds(Integer firstKpiId, Integer[] ids);

    List<SecondKpi> finAllFirstKoiIdsAndIds(Integer[] firstKpiIds, Integer[] secondKpiIds);

    List<SecondKpi> findAllByFirstKpi(Integer[] firstKpiIds);

}
