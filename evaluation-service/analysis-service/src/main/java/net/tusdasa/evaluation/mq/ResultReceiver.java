package net.tusdasa.evaluation.mq;

import net.tusdasa.evaluation.configuration.RabbitMQConfig;
import net.tusdasa.evaluation.dao.teacher.TeacherEvaluationDao;
import net.tusdasa.evaluation.entity.KpiScore;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.entity.TeacherEvaluation;
import net.tusdasa.evaluation.service.StudentEvaluationService;
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
    private StudentEvaluationService studentEvaluationService;
    private TeacherEvaluationDao teacherEvaluationDao;

    public ResultReceiver(StudentEvaluationService studentEvaluationService, TeacherEvaluationDao teacherEvaluationDao) {
        this.studentEvaluationService = studentEvaluationService;
        this.teacherEvaluationDao = teacherEvaluationDao;
    }

    // 计算总成绩
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = RabbitMQConfig.QUEUE_STUDENT, declare = "false"), exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_STUDENT, type = ExchangeTypes.TOPIC), key = RabbitMQConfig.ROUTE_KEY_STUDENT)
    })
    public void countStudentEvaluation(StudentEvaluation studentEvaluation) {
        List<KpiScore> kpiScoreList = studentEvaluation.getKpiScoreList();
        int totalScore = 0;
        for (int i = 0; i < kpiScoreList.size(); i++) {
            KpiScore kpiScore = kpiScoreList.get(i);
            totalScore += kpiScore.getScore().intValue();
        }
        studentEvaluation.setTotal(totalScore);
        studentEvaluationService.updateCourseResultTotal(studentEvaluation);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = RabbitMQConfig.QUEUE_TEACHER, declare = "false"),
                    exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_TEACHER, type = ExchangeTypes.TOPIC), key = RabbitMQConfig.ROUTE_KEY_TEACHER)
    })
    public void countTeacherEvaluation(TeacherEvaluation teacherEvaluation) {
        List<KpiScore> kpiScoreList = teacherEvaluation.getSecondKpiList();
        int totalScore = 0;
        for (int i = 0; i < kpiScoreList.size(); i++) {
            totalScore += kpiScoreList.get(i).getScore().intValue();
        }
        teacherEvaluation.setTotal(totalScore);
        teacherEvaluationDao.save(teacherEvaluation);
    }
}
