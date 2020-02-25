package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.entity.TeacherEvaluation;
import net.tusdasa.evaluation.entity.ThirdKpi;

/**
 * 教学督导评价服务接口
 *
 * @author tusdasa
 * @version 1.0
 */

public interface TeacherEvaluationService {

    /**
     * @param role 角色ID
     *             找出对应权限下，三级指标
     */
    CommonResponse<ThirdKpi> findAllThirdKpi(Integer role);

    /**
     * 接收评教结果
     *
     * @param teacherEvaluation 教学督导评价结果
     * @param workId            评价者（教学督导）工号
     */
    void addTeacherEvaluation(TeacherEvaluation teacherEvaluation, Integer workId, Integer role);

    CommonResponse<Teacher> findAllTeacher(Integer workId, Integer role);

}
