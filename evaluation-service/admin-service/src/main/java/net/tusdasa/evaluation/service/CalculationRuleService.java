package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.CalculationRuleClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.entity.Evidence;
import net.tusdasa.evaluation.entity.Level;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-09 12:49 PM
 */

@Service
public class CalculationRuleService {

    private CalculationRuleClient calculationRuleClient;

    public CalculationRuleService(CalculationRuleClient calculationRuleClient) {
        this.calculationRuleClient = calculationRuleClient;
    }


    public CommonResponse<CalculationRule> findCalculationRuleById(Integer id) {
        return this.calculationRuleClient.findCalculationRuleById(id);
    }

    public CommonResponse<CalculationRule> findAllCalculationRule() {
        return this.calculationRuleClient.findAllCalculationRule();
    }

    public CommonResponse<String> updateCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationRuleClient.updateCalculationRule(ruleRequest);
    }

    public CommonResponse<String> createCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationRuleClient.createCalculationRule(ruleRequest);
    }

    public CommonResponse<Evidence> findAllEvidence() {
        return this.calculationRuleClient.findAllEvidence();
    }

    public CommonResponse<Evidence> findEvidenceById(Integer id) {
        return this.calculationRuleClient.findEvidenceById(id);
    }

    public CommonResponse<Level> findAllLevel() {
        return this.calculationRuleClient.findAllLevel();
    }

    public CommonResponse<String> updateEvidence(Evidence evidence) {
        return this.calculationRuleClient.updateEvidence(evidence);
    }

    public CommonResponse<String> createEvidence(Evidence evidence) {
        return this.calculationRuleClient.createEvidence(evidence);
    }

    public CommonResponse<String> deleteEvidence(Integer id) {
        return this.calculationRuleClient.deleteEvidence(id);
    }

    public CommonResponse<String> deleteLevel(Integer id) {
        return this.calculationRuleClient.deleteLevel(id);
    }

    public CommonResponse<String> deleteCalculationRule(Integer id) {
        return this.calculationRuleClient.deleteCalculationRule(id);
    }

    public CommonResponse<Level> findLevelById(Integer id) {
        return this.calculationRuleClient.findLevelById(id);
    }

    public CommonResponse<String> updateLevel(Level level) {
        return this.calculationRuleClient.updateLevel(level);
    }

    public CommonResponse<String> createLevel(Level level) {
        return this.calculationRuleClient.createLevel(level);
    }


}
