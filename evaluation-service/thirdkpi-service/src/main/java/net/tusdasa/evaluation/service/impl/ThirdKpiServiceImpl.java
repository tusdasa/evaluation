package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.ThirdKpiMapper;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.ThirdKpiService;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ThirdKpiServiceImpl implements ThirdKpiService {

    private ThirdKpiMapper thirdKpiMapper;

    public ThirdKpiServiceImpl(ThirdKpiMapper thirdKpiMapper) {
        this.thirdKpiMapper = thirdKpiMapper;

    }

    @Transactional
    @Override
    public void addThirdKpi(ThirdKpiRequest request) {
        this.thirdKpiMapper.insert(request.build());
    }

    @Transactional
    @Override
    public void updateThirdKpi(ThirdKpiRequest request) {
        ThirdKpi thirdKpi = this.thirdKpiMapper.selectByPrimaryKey(request.getThirdKpiId());
        if (thirdKpi.compareTo(request) != 0) {
            this.thirdKpiMapper.updateByPrimaryKeySelective(request.build());
        }
    }

    @Transactional
    @Override
    public void deleteThirdKpi(Integer thirdKpiId) {
        this.thirdKpiMapper.deleteByPrimaryKey(thirdKpiId);
    }

    @Transactional(readOnly = true)
    @Override
    public ThirdKpi findThirdKpiById(Integer thirdKpiId) {
        return this.thirdKpiMapper.selectByPrimaryKey(thirdKpiId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findAll() {
        return this.thirdKpiMapper.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findBySecondKpiId(Integer secondKpiId) {
        return this.thirdKpiMapper.findBySecondKpiId(secondKpiId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findBySecondKpiIds(Integer secondKpiId, Integer[] thirdKpiIds) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("secondKpiId", secondKpiId);
        parameter.put("thirdKpiIds", thirdKpiIds);
        return this.thirdKpiMapper.findBySecondKpiIds(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findBySecondKpiIdsAndIds(Integer[] secondKpiIds, Integer[] thirdKpiIds) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("thirdKpiIds", thirdKpiIds);
        parameter.put("secondKpiIds", secondKpiIds);
        return this.thirdKpiMapper.findBySecondKpiIdsAndIds(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findBySecondKpiIdAll(Integer[] secondKpiIds) {
        return this.thirdKpiMapper.findBySecondKpiIdAll(secondKpiIds);
    }
}
