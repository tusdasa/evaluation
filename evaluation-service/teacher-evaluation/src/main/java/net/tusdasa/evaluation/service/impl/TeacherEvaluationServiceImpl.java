package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.configuration.RabbitMQConfig;
import net.tusdasa.evaluation.dao.TeacherEvaluationDao;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.service.TeacherEvaluationService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TeacherEvaluationServiceImpl implements TeacherEvaluationService {

    private static final Integer TEACHER_ROLE = 2;

    private AcademicYearClient academicYearClient;

    private RightClient rightClient;

    private ThirdKpiClient thirdKpiClient;

    private TeacherEvaluationDao teacherEvaluationDao;

    private TeacherClient teacherClient;

    private RabbitTemplate rabbitTemplate;

    public TeacherEvaluationServiceImpl(AcademicYearClient academicYearClient, RightClient rightClient, ThirdKpiClient thirdKpiClient, TeacherEvaluationDao teacherEvaluationDao, TeacherClient teacherClient, RabbitTemplate rabbitTemplate) {
        this.academicYearClient = academicYearClient;
        this.rightClient = rightClient;
        this.thirdKpiClient = thirdKpiClient;
        this.teacherEvaluationDao = teacherEvaluationDao;
        this.teacherClient = teacherClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @see TeacherEvaluationService#findAllThirdKpi(Integer)
     */
    @Override
    public CommonResponse<ThirdKpi> findAllThirdKpi(Integer role) {

        CommonResponse<Term> termCommonResponse = academicYearClient.currentTerm();

        if (termCommonResponse.success()) {
            CommonResponse<Right> rightCommonResponse = rightClient.findRightById(role);
            if (rightCommonResponse.success()) {
                Right right = rightCommonResponse.getData();
                if (right != null && right.checkRight()) {
                    CommonResponse<ThirdKpi> thirdKpiCommonResponse = thirdKpiClient.findAllBySecondKpiIds(new IdsRequest()
                            .addFirstIds(right.getSecondKpiId())
                            .addSecondIds(right.getThirdKpiId())
                    );
                    if (thirdKpiCommonResponse.success()) {
                        return thirdKpiCommonResponse;
                    } else {
                        log.info("未得到三级指标");
                        return new CommonResponse<ThirdKpi>().error(thirdKpiCommonResponse.getMessage());
                    }
                } else {
                    return new CommonResponse<ThirdKpi>().error("您没有权限");
                }
            } else {
                log.info("未获得权限");
                // 未获得权限
                return new CommonResponse<ThirdKpi>().error(rightCommonResponse.getMessage());
            }

        } else {
            log.info("未获取到当前学期");
            // 未获取到当前学期
            return new CommonResponse<ThirdKpi>().error(termCommonResponse.getMessage());
        }

    }

    /**
     * 此方法已经开启事务
     * 暂时未认证权限
     *
     * @see TeacherEvaluationService#addTeacherEvaluation(TeacherEvaluation, Integer, Integer)
     */
    @Transactional
    @Override
    public boolean addTeacherEvaluation(TeacherEvaluation teacherEvaluation, Integer workId, Integer role) {
        CommonResponse<Term> termCommonResponse = academicYearClient.currentTerm();
        if (termCommonResponse.success()) {
            Term term = termCommonResponse.getData();
            teacherEvaluation.setId(UUIDUtils.UUID());
            // 设置评价者工号
            teacherEvaluation.setTeacherId(workId);

            teacherEvaluation.setTermId(term.getTermId());
            // 插入MongoDB
            teacherEvaluationDao.insert(teacherEvaluation);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TEACHER, RabbitMQConfig.ROUTE_KEY_TEACHER, teacherEvaluation);
            return true;
        }
        return false;
    }

    @Override
    public CommonResponse<Teacher> findAllTeacher(Integer workId, Integer role) {
        CommonResponse<Right> rightCommonResponse = rightClient.findRightById(role);
        if (rightCommonResponse.success() && rightCommonResponse.getData() == null && rightCommonResponse.getData().checkRight()) {
            return new CommonResponse<Teacher>().error("您没有权限");
        } else {
            CommonResponse<Teacher> teacherResponse = this.rightClient.checkTeacher(workId);
            if (teacherResponse.success()) {
                Teacher teacher = teacherResponse.getData();
                CommonResponse<Teacher> teacherCommonResponse = this.teacherClient.findTeacher(
                        teacher.getDepartment().getDepartmentId(),
                        TEACHER_ROLE,
                        teacher.getState().getStateId()
                );
                if (teacherCommonResponse.success()) {
                    List<Teacher> teacherList = this.clear(this.teacherEvaluationDao.findAllByTeacherId(workId), teacherCommonResponse.getTable());
                    return new CommonResponse<Teacher>().ok().table(teacherList);
                } else {
                    return new CommonResponse<Teacher>().error(teacherCommonResponse.getMessage());
                }
            } else {
                return new CommonResponse<Teacher>().error(teacherResponse.getMessage());
            }
        }
    }

    private List<Teacher> clear(List<TeacherEvaluation> teacherEvaluationList, List<Teacher> teacherList) {
        if (teacherEvaluationList == null && teacherEvaluationList.isEmpty()) {
            return teacherList;
        } else {
            List<Teacher> newTeacherList = new ArrayList<>(teacherList.size() - teacherEvaluationList.size() + 1);
            boolean flag = false;
            int j = 0, i = 0;
            for (; i < teacherList.size(); i++) {
                Teacher t = teacherList.get(i);
                for (; j < teacherEvaluationList.size(); j++) {
                    TeacherEvaluation teacherEvaluation = teacherEvaluationList.get(j);
                    if (teacherEvaluation.getWorkId().intValue() == t.getWorkId().intValue()) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    newTeacherList.add(t);
                }
                flag = false;
                j = 0;
            }
            return newTeacherList;
        }
    }

}
