package net.tusdasa.evaluation.service.Impl;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.FirstKpiClient;
import net.tusdasa.evaluation.client.SecondKpiClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.SecondKpiResponse;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<SecondKpiResponse> findAllThirdKpi() {
        AcademicYear academicYear = this.currentAcademicYear();
        System.out.println(academicYear.getAcademicYearName());
        CommonResponse<FirstKpi> firstKpiList = firstKpiClient.findByIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(5));
        System.out.println(firstKpiList.getTable().toString());
        CommonResponse<SecondKpi> secondKpiCommonResponse = secondKpiClient.findAllByFirstKpi(new IdsRequest().addFirstIds(1).addFirstIds(2).addFirstIds(3).addFirstIds(4).addFirstIds(5));
        System.out.println(secondKpiCommonResponse.getTable().toString());
        IdsRequest idsRequest = new IdsRequest();
        idsRequest.addFirstId(28);
        idsRequest.addFirstId(29);
        idsRequest.addFirstId(30);
        idsRequest.addFirstId(31);
        idsRequest.addFirstId(32);
        idsRequest.addFirstId(33);
        idsRequest.addFirstId(34);
        idsRequest.addSecondId(1);
        idsRequest.addSecondId(2);
        idsRequest.addSecondId(3);
        idsRequest.addSecondId(4);

        CommonResponse<ThirdKpi> thirdKpiCommonResponse = thirdKpiClient.findByIdsAndIds(idsRequest);
        System.out.println(thirdKpiCommonResponse.getTable().toString());
        return null;
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

    private List<FirstKpi> findAllFirstKpiCurrentAcademicYear(AcademicYear academicYear, Integer firstKpiId) {
        CommonResponse<FirstKpi> firstKpiCommonResponse = this.firstKpiClient.findByIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(firstKpiId));
        if (firstKpiCommonResponse != null && firstKpiCommonResponse.success()) {
            if (!firstKpiCommonResponse.emptyTable()) {
                return firstKpiCommonResponse.getTable();
            }
            return null;
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
        CommonResponse<ThirdKpi> thirdKpiCommonResponse = this.thirdKpiClient.findByIdsAndIds(idsRequest);
        if (thirdKpiCommonResponse != null && thirdKpiCommonResponse.success()) {
            return thirdKpiCommonResponse.getTable();
        }
        return null;
    }

    private IdsRequest getSecondKpiIdsRequest(List<SecondKpi> ids) {
        IdsRequest idsRequest = new IdsRequest();
        if (ids.isEmpty()) {
            return null;
        } else {
            Iterator<SecondKpi> iterator = ids.iterator();
            while (iterator.hasNext()) {
                idsRequest.addFirstId(iterator.next().getFirstKpiId());
                idsRequest.addSecondId(iterator.next().getSecondKpiId());
            }
            return idsRequest;
        }
    }

    private IdsRequest getFirstKpiIdsRequest(List<FirstKpi> ids) {
        IdsRequest idsRequest = new IdsRequest();
        if (ids.isEmpty()) {
            return null;
        } else {
            Iterator<FirstKpi> iterator = ids.iterator();
            while (iterator.hasNext()) {
                idsRequest.addFirstId(iterator.next().getFirstKpiId());
            }
            return idsRequest;
        }
    }
}
