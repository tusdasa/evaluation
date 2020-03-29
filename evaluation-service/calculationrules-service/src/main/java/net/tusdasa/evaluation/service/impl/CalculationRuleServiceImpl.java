package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.CalculationRuleMapper;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.service.CalculationRuleService;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CalculationRuleServiceImpl implements CalculationRuleService {

    private CalculationRuleMapper calculationRuleMapper;

    public CalculationRuleServiceImpl(CalculationRuleMapper calculationRuleMapper) {
        this.calculationRuleMapper = calculationRuleMapper;
    }

    @Cacheable(value = "findCalculationRuleById", key = "methodName + #calculationRuleId")
    @Transactional(readOnly = true)
    @Override
    public CalculationRule findCalculationRuleById(Integer calculationRuleId) {
        return this.calculationRuleMapper.selectByPrimaryKey(calculationRuleId);
    }

    @Cacheable(value = "findAll", key = "methodName")
    @Transactional(readOnly = true)
    @Override
    public List<CalculationRule> findAll() {
        return this.calculationRuleMapper.findAll();
    }

    @Caching(evict = {
            @CacheEvict(value = "findAll", allEntries = true),
            @CacheEvict(value = "findCalculationRuleById", allEntries = true)
    })
    @Transactional
    @Override
    public void updateCalculationRule(CalculationRuleRequest ruleRequest) {
        this.calculationRuleMapper.updateByPrimaryKey(ruleRequest);
    }

    @Caching(evict = {
            @CacheEvict(value = "findAll", allEntries = true),
            @CacheEvict(value = "findCalculationRuleById", allEntries = true)
    })
    @Transactional
    @Override
    public void addCalculationRule(CalculationRuleRequest ruleRequest) {
        this.calculationRuleMapper.insert(ruleRequest);
    }

    @Caching(evict = {
            @CacheEvict(value = "findAll", allEntries = true),
            @CacheEvict(value = "findCalculationRuleById", allEntries = true)
    })
    @Transactional
    @Override
    public void deleteCalculationRule(Integer calculationRuleId) {
        this.calculationRuleMapper.deleteByPrimaryKey(calculationRuleId);
    }
}
