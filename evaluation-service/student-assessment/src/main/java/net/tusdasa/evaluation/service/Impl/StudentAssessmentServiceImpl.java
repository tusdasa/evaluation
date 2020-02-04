package net.tusdasa.evaluation.service.Impl;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.FirstKpiClient;
import net.tusdasa.evaluation.client.SecondKpiClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.FirstKpiResponse;
import net.tusdasa.evaluation.commons.SecondKpiResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentAssessmentServiceImpl implements StudentAssessmentService {

    private final Integer STUDENT_FIRST_KPI = 5;

    private AcademicYearClient academicYearClient;

    private FirstKpiClient firstKpiClient;

    private SecondKpiClient secondKpiClient;

    private ThirdKpiClient thirdKpiClient;

    public StudentAssessmentServiceImpl(AcademicYearClient academicYearClient, FirstKpiClient firstKpiClient, SecondKpiClient secondKpiClient, ThirdKpiClient thirdKpiClient) {
        this.academicYearClient = academicYearClient;
        this.firstKpiClient = firstKpiClient;
        this.secondKpiClient = secondKpiClient;
        this.thirdKpiClient = thirdKpiClient;

    }

    @Override
    public List<FirstKpiResponse> findAllThirdKpi() {
        // 获取当前学期
        AcademicYear academicYear = this.currentAcademicYear();
        if (academicYear != null) {

            List<FirstKpi> firstKpiList = this.findAllFirstKpiByAcademicYear(academicYear, this.STUDENT_FIRST_KPI);
            if (firstKpiList != null && !firstKpiList.isEmpty()) {
                //List<FirstKpiResponse> firstKpiResponseList = this.createFirstKpiResponse(academicYear,firstKpiList);
                List<SecondKpi> secondKpiList = this.findAllSecondKpiByFirstIds(this.getFirstKpiIdsRequest(firstKpiList));
                if (secondKpiList != null && !secondKpiList.isEmpty()) {
                    List<ThirdKpi> thirdKpiList = this.findAllThirdKpiBySecondKpiIds(this.getSecondKpiIdsRequest(secondKpiList));
                    if (thirdKpiList != null && !thirdKpiList.isEmpty()) {
                        //this.createFirstKpiResponse(academicYear,firstKpiList,this.createSecondKpiResponse(secondKpiList, thirdKpiList));
                        return this.createFirstKpiResponse(academicYear, firstKpiList, this.createSecondKpiResponse(secondKpiList, thirdKpiList));
                    } else {
                        return null;
                    }
                } else {
                    // 获取第二指标失败
                    return null;
                }
            } else {
                //获取第一指标失败
                return null;
            }

        } else {
            // 获取学年失败
            return null;
        }
        /**
         // 获取当前学期
         AcademicYear academicYear = this.currentAcademicYear();
         // 获取当前学期和当前角色可以操作的指标
         CommonResponse<FirstKpi> firstKpiList = firstKpiClient.findByIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(this.STUDENT_FIRST_KPI));
         // 从CommonResponse<FirstKpi>里遍历当前的firstKpiId 封装为IdsRequest 传入第二指标微服务
         IdsRequest firstKpiIds = this.getFirstKpiIdsRequest(firstKpiList.getTable());
         //第二指标微服务找出第一指标中指定中的二级指标
         CommonResponse<SecondKpi> secondKpiList = this.secondKpiClient.findAllByFirstKpi(firstKpiIds);
         //从CommonResponse<SecondKpi>中遍历所有的secondKpiId 封装为IdsRequest 传入第三指标微服务
         IdsRequest secondKpiIds = this.getSecondKpiIdsRequest(secondKpiList.getTable());
         // 第三指标微服务找出第二指标中指定中的三级指标
         CommonResponse<ThirdKpi> thirdKpiList = thirdKpiClient.findThirdBySecondKpiIds(secondKpiIds);
         **/
    }

    // 获取当前学年
    private AcademicYear currentAcademicYear() {
        CommonResponse<AcademicYear> academicYearResponse = this.academicYearClient.current();
        if (academicYearResponse != null && academicYearResponse.success()) {
            return academicYearResponse.getData();
        } else {
            return null;
        }
    }

    private List<FirstKpi> findAllFirstKpiByAcademicYear(AcademicYear academicYear, Integer firstKpiId) {
        CommonResponse<FirstKpi> firstKpiCommonResponse = this.firstKpiClient.findByIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(firstKpiId));
        if (firstKpiCommonResponse != null && firstKpiCommonResponse.success()) {
            return firstKpiCommonResponse.getTable();
        }
        return null;
    }

    private List<SecondKpi> findAllSecondKpiByFirstIds(IdsRequest idsRequest) {
        CommonResponse<SecondKpi> secondKpiCommonResponse = this.secondKpiClient.findAllByFirstKpi(idsRequest);
        if (secondKpiCommonResponse != null && secondKpiCommonResponse.success()) {
            return secondKpiCommonResponse.getTable();
        }
        return null;
    }

    private List<ThirdKpi> findAllThirdKpiBySecondKpiIds(IdsRequest idsRequest) {
        CommonResponse<ThirdKpi> thirdKpiCommonResponse = this.thirdKpiClient.findThirdBySecondKpiIds(idsRequest);
        if (thirdKpiCommonResponse != null && thirdKpiCommonResponse.success()) {
            return thirdKpiCommonResponse.getTable();
        }
        return null;
    }

    // 封装所有二级指标的ID到IdsRequest
    private IdsRequest getSecondKpiIdsRequest(List<SecondKpi> secondKpiList) {
        IdsRequest idsRequest = new IdsRequest();
        if (secondKpiList.isEmpty()) {
            return null;
        } else {

            for (SecondKpi secondKpi : secondKpiList) {
                idsRequest.addFirstId(secondKpi.getSecondKpiId());
            }
            return idsRequest;
        }
    }

    // 封装所有一级指标的ID到IdsRequest
    private IdsRequest getFirstKpiIdsRequest(List<FirstKpi> firstKpiList) {
        IdsRequest idsRequest = new IdsRequest();
        if (firstKpiList.isEmpty()) {
            return null;
        } else {
            for (FirstKpi firstKpi : firstKpiList) {
                idsRequest.addFirstId(firstKpi.getFirstKpiId());
            }
            return idsRequest;
        }
    }

    private List<FirstKpiResponse> createFirstKpiResponse(AcademicYear academicYear, List<FirstKpi> firstKpiList, List<SecondKpiResponse> secondKpiResponseList) {
        List<FirstKpiResponse> firstKpiResponses = new ArrayList<>(firstKpiList.size());
        for (int i = 0; i < firstKpiList.size(); i++) {
            FirstKpiResponse firstKpiResponse = new FirstKpiResponse().addAcademic(academicYear).addFirstKpi(firstKpiList.get(i));
            for (int j = 0; j < secondKpiResponseList.size(); j++) {
                if (firstKpiResponse.getFirstKpiId().intValue() == secondKpiResponseList.get(j).getFirstKpiId().intValue()) {
                    firstKpiResponse.addSecondKpiResponse(secondKpiResponseList.get(j));
                    secondKpiResponseList.remove(secondKpiResponseList.get(j));
                    j--;
                }
            }
            firstKpiResponses.add(firstKpiResponse);
        }
        return firstKpiResponses;
    }

    private List<SecondKpiResponse> createSecondKpiResponse(List<SecondKpi> secondKpiList, List<ThirdKpi> thirdKpiList) {
        List<SecondKpiResponse> secondKpiResponseList = new ArrayList<>(secondKpiList.size());
        for (int i = 0; i < secondKpiList.size(); i++) {
            SecondKpi secondKpi = secondKpiList.get(i);
            for (int j = 0; j < thirdKpiList.size(); j++) {
                ThirdKpi thirdKpi = thirdKpiList.get(j);
                if (secondKpi.getSecondKpiId().intValue() == thirdKpi.getSecondKpiId().intValue()) {
                    secondKpiResponseList.add(new SecondKpiResponse().addSecondKpi(secondKpi).addTable(thirdKpi));
                    thirdKpiList.remove(thirdKpi);
                    j--;
                }
            }
        }
        return secondKpiResponseList;
    }
}
