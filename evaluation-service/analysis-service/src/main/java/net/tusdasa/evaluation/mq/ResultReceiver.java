package net.tusdasa.evaluation.mq;

import net.tusdasa.evaluation.entity.KpiScore;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.service.StudentEvaluationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultReceiver {

    private StudentEvaluationService studentEvaluationService;

    public ResultReceiver(StudentEvaluationService studentEvaluationService) {
        this.studentEvaluationService = studentEvaluationService;
    }

    // 计算总成绩
    public void receiveMessage(StudentEvaluation studentEvaluation) {
        List<KpiScore> kpiScoreList = studentEvaluation.getKpiScoreList();
        int totalScore = 0;
        for (int i = 0; i < kpiScoreList.size(); i++) {
            KpiScore kpiScore = kpiScoreList.get(i);
            totalScore += kpiScore.getScore().intValue();
        }
        studentEvaluation.setTotal(totalScore);
        studentEvaluationService.updateCourseResultTotal(studentEvaluation);
    }
}
