package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.authority.Authority;
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

    // 取教师角色
    private static final Integer TEACHER_ROLE = Authority.TEACHER;

    // 取参与的教师
    private static final Integer TEACHER_STATE = Authority.ACTIVE;

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
        // 确保在评价时间内
        Term currentTerm = this.getTerm();
        // 权限
        Right right = this.getRight(role);
        if (currentTerm == null){
            return new CommonResponse<ThirdKpi>().bad("不在评价学期内");
        }
        if (right != null) {
            if (right.checkRight()) {
                return this.getThirdKpi(
                        new IdsRequest()
                                .addFirstIds(right.getSecondKpiId())
                                .addSecondIds(right.getThirdKpiId())
                );
            } else {
                return new CommonResponse<ThirdKpi>().bad();
            }
        } else {
            return new CommonResponse<ThirdKpi>().bad("没有权限");
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
    public void addTeacherEvaluation(TeacherEvaluation teacherEvaluation, Integer workId, Integer role) {
        Term currentTerm = this.getTerm();
        if (currentTerm != null) {
            teacherEvaluation.setId(UUIDUtils.UUID());
            // 设置评价者工号
            teacherEvaluation.setEvaluatorId(workId);
            teacherEvaluation.setTermId(currentTerm.getTermId());
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_TEACHER,
                    RabbitMQConfig.ROUTE_KEY_TEACHER,
                    teacherEvaluationDao.insert(teacherEvaluation)
            );
        }
    }

    @Override
    public CommonResponse<Teacher> findAllTeacher(Integer workId, Integer role) {
        Right right = this.getRight(role);
        Term currentTerm = this.getTerm();
        if (right != null && currentTerm != null) {
            Teacher teacher = this.getTeacher(workId);
            if (teacher != null) {
                List<Teacher> teacherList = this.getTeacherList(
                        teacher.getDepartment().getDepartmentId()
                );

                List<Teacher> newTeacherList = this.clearTeacherList(
                        this.teacherEvaluationDao.findAllByEvaluatorIdAndTermId(workId, currentTerm.getTermId()),
                        teacherList
                );
                return new CommonResponse<Teacher>().ok().table(newTeacherList);
            } else {
                return new CommonResponse<Teacher>().busy();
            }
        } else {
            return new CommonResponse<Teacher>().bad();
        }
/*
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
                    List<Teacher> teacherList = this.clear(this.teacherEvaluationDao.findAllByEvaluatorIdAndTermId(workId, ), teacherCommonResponse.getTable());
                    return new CommonResponse<Teacher>().ok().table(teacherList);
                } else {
                    return new CommonResponse<Teacher>().error(teacherCommonResponse.getMessage());
                }
            } else {
                return new CommonResponse<Teacher>().error(teacherResponse.getMessage());
            }
        }
 */
    }

    @Override
    public long countAllByTermId(Integer termId) {
        return teacherEvaluationDao.countAllByTermId(termId);
    }

    @Override
    public void deleteAllByTermId(Integer termId) {
        teacherEvaluationDao.deleteAllByTermId(termId);
    }

    private List<Teacher> clearTeacherList(List<TeacherEvaluation> teacherEvaluationList, List<Teacher> teacherList) {
        if (teacherEvaluationList == null || teacherEvaluationList.isEmpty()) {
            return teacherList;
        } else {
            List<Teacher> newTeacherList = new ArrayList<>(teacherList.size() - teacherEvaluationList.size() + 1);
            boolean flag = false;
            int j = 0, i = 0;
            for (; i < teacherList.size(); i++) {
                Teacher t = teacherList.get(i);
                for (; j < teacherEvaluationList.size(); j++) {
                    TeacherEvaluation teacherEvaluation = teacherEvaluationList.get(j);
                    if (teacherEvaluation.getWorkId().equals(t.getWorkId())) {
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

    private Right getRight(Integer role) {
        CommonResponse<Right> rightCommonResponse = rightClient.findRightById(role);
        if (rightCommonResponse.success()) {
            return rightCommonResponse.getData();
        }
        return null;
    }

    private Term getTerm() {
        CommonResponse<Term> termCommonResponse = academicYearClient.currentTerm();
        if (termCommonResponse.success()) {
            return termCommonResponse.getData();
        }
        return null;
    }

    private CommonResponse<ThirdKpi> getThirdKpi(IdsRequest request) {
        return this.thirdKpiClient.findAllBySecondKpiIds(request);
    }

    private Teacher getTeacher(Integer wordId) {
        CommonResponse<Teacher> teacherCommonResponse = rightClient.checkTeacher(wordId);
        if (teacherCommonResponse.success()) {
            return teacherCommonResponse.getData();
        }
        return null;
    }

    private List<Teacher> getTeacherList(Integer departmentId) {
        CommonResponse<Teacher> teacherCommonResponse = this.teacherClient.findTeacher(departmentId, TEACHER_ROLE, TEACHER_STATE);
        if (teacherCommonResponse.success()) {
            return teacherCommonResponse.getTable();
        }
        return null;
    }


}
