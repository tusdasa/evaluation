package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.FirstKpiMapper;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.service.FirstKpiService;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FirstKpiServiceImpl implements FirstKpiService {

    private FirstKpiMapper firstKpiMapper;

    public FirstKpiServiceImpl(FirstKpiMapper firstKpiMapper) {
        this.firstKpiMapper = firstKpiMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<FirstKpi> findAllFirstKpiByAcademicYear(Integer academicYearId) {
        return this.firstKpiMapper.findAllByAcademicYear(academicYearId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FirstKpi> findAll() {
        return this.firstKpiMapper.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public FirstKpi findById(Integer firstKpiId) {
        return this.firstKpiMapper.selectByPrimaryKey(firstKpiId);
    }

    @Transactional
    @Override
    public void updateFirstKpi(FirstKpiRequest request) {
        FirstKpi firstKpi = this.firstKpiMapper.selectByPrimaryKey(request.getFirstKpiId());
        if (firstKpi != null && firstKpi.compareTo(request) != 0) {
            this.firstKpiMapper.insertSelective(request.build());
        }
    }

    @Transactional
    @Override
    public void addFirstKpi(FirstKpiRequest request) {
        this.firstKpiMapper.insert(request.build());
    }

    @Transactional
    @Override
    public void deleteFirstKpi(Integer firstKpiId) {
        this.firstKpiMapper.deleteByPrimaryKey(firstKpiId);
    }
}
