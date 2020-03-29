package net.tusdasa.evaluation.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.configuration.RabbitMQConfig;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.service.StudentEvaluationInfoService;
import net.tusdasa.evaluation.service.StudentEvaluationService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Slf4j
@Transactional
@Service
public class StudentEvaluationServiceImpl implements StudentEvaluationService {

    private AcademicYearClient academicYearClient;

    private ThirdKpiClient thirdKpiClient;

    private RightClient rightClient;

    private CourseClient courseClient;

    private RabbitTemplate rabbitTemplate;

    private StudentEvaluationInfoService studentEvaluationInfoService;

    public StudentEvaluationServiceImpl(AcademicYearClient academicYearClient, ThirdKpiClient thirdKpiClient, RightClient rightClient, CourseClient courseClient, RabbitTemplate rabbitTemplate, StudentEvaluationInfoService studentEvaluationInfoService) {
        this.academicYearClient = academicYearClient;
        this.thirdKpiClient = thirdKpiClient;
        this.rightClient = rightClient;
        this.courseClient = courseClient;
        this.rabbitTemplate = rabbitTemplate;
        this.studentEvaluationInfoService = studentEvaluationInfoService;
    }

    /**
     * 获取学生信息
     */
    private Student getStudent(Long studentId) {
        CommonResponse<Student> studentCommonResponse = this.rightClient.checkStudent(String.valueOf(studentId));
        if (studentCommonResponse.success()) {
            return studentCommonResponse.getData();
        } else {
            log.info(studentCommonResponse.getMessage());
            return null;
        }
    }

    /**
     * 获取当前学期信息
     */
    private Term getTerm() {
        CommonResponse<Term> termCommonResponse = this.academicYearClient.currentTerm();
        if (termCommonResponse.success()) {
            return termCommonResponse.getData();
        } else {
            log.info(termCommonResponse.getMessage());
            return null;
        }
    }


    private List<Course> getCourseList(Integer classId, Integer termId) {
        CommonResponse<Course> courseCommonResponse = this.courseClient.findCourseByClassIdAndTermId(classId, termId);
        if (courseCommonResponse.success()) {
            return courseCommonResponse.getTable();
        } else {
            log.info(courseCommonResponse.getMessage());
            return Collections.emptyList();
        }
    }

    private Right getRight(Integer role) {
        CommonResponse<Right> rightCommonResponse = this.rightClient.findRightById(role);
        if (rightCommonResponse.success()) {
            return rightCommonResponse.getData();
        } else {
            log.info(rightCommonResponse.getMessage());
            return null;
        }
    }

    private CommonResponse<ThirdKpi> getThirdKpiList(Right right) {
        return this.thirdKpiClient.findAllBySecondKpiIds(new IdsRequest().addFirstIds(right.getSecondKpiId()).addSecondIds(right.getThirdKpiId()));
    }

    @Override
    public CommonResponse<String> addStudentCourseResult(StudentEvaluation studentEvaluation, Long studentId) {

        Student student = this.getStudent(studentId);
        if (student != null) {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_STUDENT,
                    RabbitMQConfig.ROUTE_KEY_STUDENT,
                    this.studentEvaluationInfoService.addStudentCourseResult(studentEvaluation, student)
            );
            return new CommonResponse<String>().ok();
        } else {
            return new CommonResponse<String>().error();
        }
    }

    @Override
    public CommonResponse<ThirdKpi> findAll(Integer role) {
        Right right = this.getRight(role);
        if (right != null) {
            if (right.checkRight()) {
                return this.getThirdKpiList(right);
            } else {
                return new CommonResponse<ThirdKpi>().bad();
            }
        } else {
            return new CommonResponse<ThirdKpi>().busy();
        }
    }


    @Override
    public CommonResponse<Course> currentCourse(Long studentId) {
        Student student = this.getStudent(studentId);
        Term currentTerm = this.getTerm();
        if (student != null && currentTerm != null) {
            List<Course> courseList = this.getCourseList(student.getStudentClass().getClassId(), currentTerm.getTermId());
            List<Course> currentCourse = studentEvaluationInfoService.checkAllCourse(
                    courseList,
                    this.studentEvaluationInfoService.findAllByStudentIdAndAndTermId(
                            studentId,
                            currentTerm.getTermId()
                    )
            );
            if (!currentCourse.isEmpty()) {
                return new CommonResponse<Course>().ok().table(currentCourse);
            } else {
                return new CommonResponse<Course>().error("您已经完成所有评价");
            }
        } else {
            return new CommonResponse<Course>().error();
        }
    }

    @Override
    public long countAllByTermId(Integer termId) {
        return studentEvaluationInfoService.countAllByTermId(termId);
    }

    @Override
    public void deleteAllByTermId(Integer termId) {
        studentEvaluationInfoService.deleteAllByTermId(termId);
    }

    @Override
    public long countAllByTermIdAndAndDepartmentId(Integer termId, Integer departmentId) {
        return studentEvaluationInfoService.countAllByTermIdAndAndDepartmentId(termId, departmentId);
    }

}
