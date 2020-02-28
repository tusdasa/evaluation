package net.tusdasa.evaluation.mq;

import net.tusdasa.evaluation.configuration.RabbitMQConfig;
import net.tusdasa.evaluation.entity.KpiScore;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.entity.TeacherEvaluation;
import net.tusdasa.evaluation.service.StudentSituationService;
import net.tusdasa.evaluation.service.TeacherSituationService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RabbitMQ 消息接收处理类
 *
 * @Author: tusdasa
 * @Date: 2020-02-22 11:26 AM
 */


@Component
public class ResultReceiver {


    private StudentSituationService studentEvaluationService;

    private TeacherSituationService teacherSituationService;

    public ResultReceiver(StudentSituationService studentEvaluationService, TeacherSituationService teacherSituationService) {
        this.studentEvaluationService = studentEvaluationService;
        this.teacherSituationService = teacherSituationService;
    }

    /**
     * 学生评价通知
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = RabbitMQConfig.QUEUE_STUDENT, declare = "false"), exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_STUDENT, type = ExchangeTypes.TOPIC), key = RabbitMQConfig.ROUTE_KEY_STUDENT)
    })
    public void countStudentEvaluation(StudentEvaluation studentEvaluation) {
        // 计算成绩
        List<KpiScore> kpiScoreList = studentEvaluation.getKpiScoreList();
        int totalScore = 0;
        for (int i = 0; i < kpiScoreList.size(); i++) {
            KpiScore kpiScore = kpiScoreList.get(i);
            totalScore += kpiScore.getScore();
        }
        studentEvaluation.setTotal(totalScore);

        this.studentEvaluationService.addStudentEvaluation(studentEvaluation);
    }

    /**
     * 教师评价通知
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = RabbitMQConfig.QUEUE_TEACHER, declare = "false"),
                    exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_TEACHER, type = ExchangeTypes.TOPIC), key = RabbitMQConfig.ROUTE_KEY_TEACHER)
    })
    public void countTeacherEvaluation(TeacherEvaluation teacherEvaluation) {
        // 计算成绩
        List<KpiScore> kpiScoreList = teacherEvaluation.getSecondKpiList();
        int totalScore = 0;
        for (int i = 0; i < kpiScoreList.size(); i++) {
            totalScore += kpiScoreList.get(i).getScore();
        }
        teacherEvaluation.setTotal(totalScore);

        this.teacherSituationService.addTeacherSituation(teacherEvaluation);
    }
}
