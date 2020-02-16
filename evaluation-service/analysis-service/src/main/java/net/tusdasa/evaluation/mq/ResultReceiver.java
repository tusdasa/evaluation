package net.tusdasa.evaluation.mq;

import net.tusdasa.evaluation.entity.KpiScore;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultReceiver {

    private StudentCourseResultService studentCourseResultService;

    public ResultReceiver(StudentCourseResultService studentCourseResultService) {
        this.studentCourseResultService = studentCourseResultService;
    }

    public void receiveMessage(StudentCourseResult studentCourseResult) {
        List<KpiScore> scores = studentCourseResult.getScores();
        int totalScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            KpiScore kpiScore = scores.get(i);
            totalScore += kpiScore.getScore().intValue();
        }
        studentCourseResult.setTotal(totalScore);
        studentCourseResultService.addStudentCourseResult(studentCourseResult);
    }
}
