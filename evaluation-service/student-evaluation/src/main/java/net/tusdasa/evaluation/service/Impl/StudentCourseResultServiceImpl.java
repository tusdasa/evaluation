package net.tusdasa.evaluation.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.configuration.RabbitmqConfig;
import net.tusdasa.evaluation.dao.StudentCourseResultDao;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class StudentCourseResultServiceImpl implements StudentCourseResultService {

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
        studentCourseResultDao.insert(studentCourseResult);
        // 通知分析服务计算成绩
        rabbitTemplate.convertAndSend(RabbitmqConfig.topicExchangeName, "evaluation.result", studentCourseResult);
    }

    @Override
    public List<StudentCourseResult> findBydStudentId(Long studentId) {
        return studentCourseResultDao.findAllByStudentId(studentId);
    }

}
