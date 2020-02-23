package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.TeacherEvaluationDao;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.service.TeacherEvaluationService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TeacherEvaluationServiceImpl implements TeacherEvaluationService {

    private static final Integer TEACHER_ROLE = 2;

    private AcademicYearClient academicYearClient;

    private RightClient rightClient;

    private ThirdKpiClient thirdKpiClient;

    private TeacherEvaluationDao teacherEvaluationDao;

    private TeacherClient teacherClient;

    public TeacherEvaluationServiceImpl(AcademicYearClient academicYearClient,
                                        RightClient rightClient,
                                        ThirdKpiClient thirdKpiClient,
                                        TeacherEvaluationDao teacherEvaluationDao,
                                        TeacherClient teacherClient
    ) {
        this.academicYearClient = academicYearClient;
        this.rightClient = rightClient;
        this.thirdKpiClient = thirdKpiClient;
        this.teacherEvaluationDao = teacherEvaluationDao;
        this.teacherClient = teacherClient;
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
     *
     * @see TeacherEvaluationService#addTeacherEvaluation(TeacherEvaluation, Integer)
     */
    @Transactional
    @Override
    public void addTeacherEvaluation(TeacherEvaluation teacherEvaluation, Integer workId) {
        // 生成主键
        teacherEvaluation.setId(UUIDUtils.UUID());
        // 设置评价者工号
        teacherEvaluation.setTeacherId(workId);
        // 插入MongoDB
        teacherEvaluationDao.insert(teacherEvaluation);
        // 通过消息队列通知分析微服务计算
    }

    @Override
    public CommonResponse<Teacher> findAllTeacher(Integer workId) {
        CommonResponse<Teacher> teacherResponse = this.rightClient.checkTeacher(workId);
        if (teacherResponse.success()) {
            Teacher teacher = teacherResponse.getData();
            CommonResponse<Teacher> teacherCommonResponse = this.teacherClient.findTeacher(
                    teacher.getDepartment().getDepartmentId(),
                    TEACHER_ROLE,
                    teacher.getState().getStateId()
            );
            if (teacherCommonResponse.success()) {
                return teacherCommonResponse;
            } else {
                return new CommonResponse<Teacher>().error(teacherCommonResponse.getMessage());
            }
        } else {
            return new CommonResponse<Teacher>().error(teacherResponse.getMessage());
        }
    }

}
