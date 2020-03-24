package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.CalculationRuleClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.entity.Evidence;
import net.tusdasa.evaluation.entity.Level;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:53 PM
 */

@Component
public class CalculationRuleClientImpl implements CalculationRuleClient {
    @Override
    public CommonResponse<CalculationRule> findCalculationRuleById(Integer id) {
        return new CommonResponse<CalculationRule>().busy();
    }

    @Override
    public CommonResponse<CalculationRule> findAllCalculationRule() {
        return new CommonResponse<CalculationRule>().busy();
    }

    @Override
    public CommonResponse<String> updateCalculationRule(CalculationRuleRequest ruleRequest) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> createCalculationRule(CalculationRuleRequest ruleRequest) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> deleteCalculationRule(Integer id) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<Evidence> findEvidenceById(Integer id) {
        return new CommonResponse<Evidence>().busy();
    }

    @Override
    public CommonResponse<Evidence> findAllEvidence() {
        return new CommonResponse<Evidence>().busy();
    }

    @Override
    public CommonResponse<String> createEvidence(Evidence evidence) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> updateEvidence(Evidence evidence) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> deleteEvidence(Integer id) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<Level> findAllLevel() {
        return new CommonResponse<Level>().busy();
    }

    @Override
    public CommonResponse<Level> findLevelById(Integer id) {
        return new CommonResponse<Level>().busy();
    }

    @Override
    public CommonResponse<String> updateLevel(Level level) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> createLevel(Level level) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> deleteLevel(Integer id) {
        return new CommonResponse<String>().busy();
    }
}
