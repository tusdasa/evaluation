package net.tusdasa.evaluation.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.configuration.RabbitMQConfig;
import net.tusdasa.evaluation.dao.StudentEvaluationDao;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.service.StudentEvaluationService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class StudentEvaluationServiceImpl implements StudentEvaluationService {

    private final RabbitTemplate rabbitTemplate;
    private StudentEvaluationDao studentEvaluationDao;

    public StudentEvaluationServiceImpl(RabbitTemplate rabbitTemplate, StudentEvaluationDao studentEvaluationDao) {
        this.rabbitTemplate = rabbitTemplate;
        this.studentEvaluationDao = studentEvaluationDao;
    }

    @Override
    public void addStudentCourseResult(StudentEvaluation studentEvaluation, Long studentId) {
        String uuid = UUIDUtils.UUID();
        studentEvaluation.setId(uuid);
        studentEvaluation.setStudentId(studentId);
        studentEvaluationDao.insert(studentEvaluation);
        // 通知分析服务计算成绩
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "evaluation.result", studentEvaluation);
    }

    @Override
    public List<StudentEvaluation> findBydStudentId(Long studentId) {
        return studentEvaluationDao.findAllByStudentId(studentId);
    }

}
