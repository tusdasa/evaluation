package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;

public interface TeacherEvaluationService {
    // 找出教学督导权限下的三级指标

    // 接收角色ID 和 工号
    CommonResponse<ThirdKpi> findAllThirdKpi(Integer role);

    // 接收评教结果

}
