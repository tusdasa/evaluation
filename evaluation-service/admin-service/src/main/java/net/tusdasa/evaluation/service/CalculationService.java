package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.CalculationClient;
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
public class CalculationService {

    private CalculationClient calculationClient;

    public CalculationService(CalculationClient calculationClient) {
        this.calculationClient = calculationClient;
    }


    public CommonResponse<CalculationRule> findCalculationRuleById(Integer id) {
        return this.calculationClient.findCalculationRuleById(id);
    }

    public CommonResponse<CalculationRule> findAllCalculationRule() {
        return this.calculationClient.findAllCalculationRule();
    }

    public CommonResponse<String> updateCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationClient.updateCalculationRule(ruleRequest);
    }

    public CommonResponse<String> createCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationClient.createCalculationRule(ruleRequest);
    }

    public CommonResponse<Evidence> findAllEvidence() {
        return this.calculationClient.findAllEvidence();
    }

    public CommonResponse<Level> findAllLevel() {
        return this.calculationClient.findAllLevel();
    }

    public CommonResponse<Level> findEvidenceById(Integer id) {
        return this.calculationClient.findLevelById(id);
    }

    public CommonResponse<String> updateEvidence(Evidence evidence) {
        return this.calculationClient.updateEvidence(evidence);
    }

    public CommonResponse<String> createEvidence(Evidence evidence) {
        return this.calculationClient.updateEvidence(evidence);
    }

    public CommonResponse<String> deleteEvidence(Integer id) {
        return this.calculationClient.deleteEvidence(id);
    }

    public CommonResponse<String> deleteLevel(Integer id) {
        return this.calculationClient.deleteLevel(id);
    }

    public CommonResponse<String> deleteCalculationRule(Integer id) {
        return this.calculationClient.deleteCalculationRule(id);
    }

    public CommonResponse<Level> findLevelById(Integer id) {
        return this.calculationClient.findLevelById(id);
    }

    public CommonResponse<String> updateLevel(Level level) {
        return this.calculationClient.updateLevel(level);
    }

    public CommonResponse<String> createLevel(Level level) {
        return this.calculationClient.createLevel(level);
    }


}
