package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.SecondKpiMapper;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.service.SecondKpiService;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondKpiServiceImpl implements SecondKpiService {

    private SecondKpiMapper secondKpiMapper;

    public SecondKpiServiceImpl(SecondKpiMapper secondKpiMapper){
        this.secondKpiMapper = secondKpiMapper;
    }

    @Override
    public void addSecondKpi(SecondKpiRequest request) {

    }

    @Override
    public void updateSecondKpi(SecondKpiRequest request) {

    }

    @Override
    public void deleteSecondKpi(Integer secondKpiId) {
        this.secondKpiMapper.deleteByPrimaryKey(secondKpiId);
    }

    @Override
    public List<SecondKpi> findAll() {
        return this.secondKpiMapper.findAll();
    }

    @Override
    public List<SecondKpi> findAllByFirstKpi(Integer firstKpiId) {
        return this.secondKpiMapper.findAllByFirstKpiId(firstKpiId);
    }
}
