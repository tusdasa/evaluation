package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CalculationRuleMapper {

    int deleteByPrimaryKey(Integer calculationRuleId);

    int insert(CalculationRuleRequest record);

    int insertSelective(CalculationRuleRequest record);

    CalculationRule selectByPrimaryKey(Integer calculationRuleId);

    int updateByPrimaryKeySelective(CalculationRuleRequest record);

    int updateByPrimaryKey(CalculationRuleRequest record);

    List<CalculationRule> findAll();
}