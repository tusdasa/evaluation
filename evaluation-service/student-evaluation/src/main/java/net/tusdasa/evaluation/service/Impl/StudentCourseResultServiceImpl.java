package net.tusdasa.evaluation.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.configuration.RabbitmqConfig;
import net.tusdasa.evaluation.dao.StudentCourseResultDao;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class StudentCourseResultServiceImpl implements StudentCourseResultService, RabbitTemplate.ConfirmCallback {

    private final RabbitTemplate rabbitTemplate;
    private StudentCourseResultDao studentCourseResultDao;

    public StudentCourseResultServiceImpl(RabbitTemplate rabbitTemplate, StudentCourseResultDao studentCourseResultDao) {
        this.rabbitTemplate = rabbitTemplate;
        this.studentCourseResultDao = studentCourseResultDao;
    }

    @Override
    public void addStudentCourseResult(StudentCourseResult studentCourseResult, Long studentId) {
        String uuid = UUIDUtils.UUID();
        studentCourseResult.setId(uuid);
        studentCourseResult.setStudentId(studentId);
        rabbitTemplate.convertAndSend(RabbitmqConfig.topicExchangeName, "evaluation.result", studentCourseResult);
        rabbitTemplate.setConfirmCallback(this::confirm);
    }

    @Override
    public List<StudentCourseResult> findBydStudentId(Long studentId) {
        return studentCourseResultDao.findAllByStudentId(studentId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            log.info("message success");
        } else {
            log.info("ID: {} message failure cause {}", correlationData, s);
        }
    }
}
