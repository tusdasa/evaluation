package net.tusdasa.evaluation.mq;

import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.configuration.RabbitMQConfig;
import net.tusdasa.evaluation.dao.StudentTeachingSituationDao;
import net.tusdasa.evaluation.entity.KpiScore;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.entity.TeacherEvaluation;
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

    private StudentTeachingSituationDao studentTeachingSituationDao;

    private CourseClient courseClient;

    public ResultReceiver(StudentTeachingSituationDao studentTeachingSituationDao) {
        this.studentTeachingSituationDao = studentTeachingSituationDao;
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
        // 根据课程Id 获得 工号

        Long courseId = studentEvaluation.getCourseId();

        /*
        CommonResponse<Course> courseCommonResponse = courseClient.findByCourseId(courseId);

        if (courseCommonResponse.success()){
            StudentTeachingSituation studentTeachingSituation = studentTeachingSituationDao.findById(courseCommonResponse.getData().getTeacherWorkId()).get();
            if (studentTeachingSituation != null){
                List<FactorCourse> factorCourseList = studentTeachingSituation.getFactorCourseList();
                if (factorCourseList !=null){

                }else {
                }
            }else {
                studentTeachingSituation = new StudentTeachingSituation();
            }
        }else {
            // 无法计算
        }
         */

        // 工号查询 是否存在studentTeachingSituation
        // 不存在
        // 创建对象
        // 存在 根据学生ID 获得班级学院 遍历List<FactorCourse> factorCourseList 遍历课程ID
        // 找到对应的课程 再遍历List<FactorClasses> classesList 根据班级ID 找到班级
        // 加入总分

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
    }
}
