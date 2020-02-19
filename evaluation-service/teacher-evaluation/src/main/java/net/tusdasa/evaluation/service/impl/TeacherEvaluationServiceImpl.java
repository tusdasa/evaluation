package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.TeacherEvaluationService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherEvaluationServiceImpl implements TeacherEvaluationService {

    private AcademicYearClient academicYearClient;

    private RightClient rightClient;

    private ThirdKpiClient thirdKpiClient;

    public TeacherEvaluationServiceImpl(AcademicYearClient academicYearClient, RightClient rightClient, ThirdKpiClient thirdKpiClient) {
        this.academicYearClient = academicYearClient;
        this.rightClient = rightClient;
        this.thirdKpiClient = thirdKpiClient;
    }

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

}
