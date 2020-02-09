package net.tusdasa.evaluation.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.*;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.FirstKpiResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentAssessmentServiceImpl implements StudentAssessmentService {

    private final Integer STUDENT_RIGHT = -1;

    private AcademicYearClient academicYearClient;

    private FirstKpiClient firstKpiClient;

    private SecondKpiClient secondKpiClient;

    private ThirdKpiClient thirdKpiClient;

    private RightClient rightClient;

    private CourseClient courseClient;

    public StudentAssessmentServiceImpl(AcademicYearClient academicYearClient,
                                        FirstKpiClient firstKpiClient,
                                        SecondKpiClient secondKpiClient,
                                        ThirdKpiClient thirdKpiClient,
                                        RightClient rightClient,
                                        CourseClient courseClient) {
        this.academicYearClient = academicYearClient;
        this.firstKpiClient = firstKpiClient;
        this.secondKpiClient = secondKpiClient;
        this.thirdKpiClient = thirdKpiClient;
        this.rightClient = rightClient;
        this.courseClient = courseClient;
    }

    /**
     * 获取当前学期
     * AcademicYear academicYear = this.currentAcademicYear();
     * 获取当前学期和当前角色可以操作的指标
     * CommonResponse<FirstKpi> firstKpiList = firstKpiClient.findByIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(this.STUDENT_FIRST_KPI));
     * 从CommonResponse<FirstKpi>里遍历当前的firstKpiId 封装为IdsRequest 传入第二指标微服务
     * IdsRequest firstKpiIds = this.getFirstKpiIdsRequest(firstKpiList.getTable());
     * 第二指标微服务找出第一指标中指定中的二级指标
     * CommonResponse<SecondKpi> secondKpiList = this.secondKpiClient.findAllByFirstKpi(firstKpiIds);
     * 从CommonResponse<SecondKpi>中遍历所有的secondKpiId 封装为IdsRequest 传入第三指标微服务
     * IdsRequest secondKpiIds = this.getSecondKpiIdsRequest(secondKpiList.getTable());
     * 第三指标微服务找出第二指标中指定中的三级指标
     * CommonResponse<ThirdKpi> thirdKpiList = thirdKpiClient.findThirdBySecondKpiIds(secondKpiIds);
     **/
    private List<FirstKpiResponse> findAllThirdKpi() {
        /*
        // 获取当前学期
        AcademicYear academicYear = this.currentAcademicYear();
        if (academicYear != null) {
            List<FirstKpi> firstKpiList = this.findAllFirstKpiByAcademicYear(academicYear, this.STUDENT_FIRST_KPI);
            if (firstKpiList != null && !firstKpiList.isEmpty()) {
                List<SecondKpi> secondKpiList = this.findAllSecondKpiByFirstIds(this.getFirstKpiIdsRequest(firstKpiList));
                if (secondKpiList != null && !secondKpiList.isEmpty()) {
                    List<ThirdKpi> thirdKpiList = this.findAllThirdKpiBySecondKpiIds(this.getSecondKpiIdsRequest(secondKpiList));
                    if (thirdKpiList != null && !thirdKpiList.isEmpty()) {
                        return this.createFirstKpiResponse(academicYear, firstKpiList, secondKpiList, thirdKpiList);
                    } else {
                        log.error("第三指标获取失败");
                        //第三指标失败
                        return java.util.Collections.emptyList();
                    }
                } else {
                    log.error("第二指标获取失败");
                    // 获取第二指标失败
                    return java.util.Collections.emptyList();
                }
            } else {
                log.error("第一指标获取失败");
                //获取第一指标失败
                return java.util.Collections.emptyList();
            }

        } else {
            log.error("学年获取失败");
            // 获取学年失败
            return java.util.Collections.emptyList();
        }
         */

        return null;
    }

    @Override
    public CommonResponse<ThirdKpi> findAll() {
        /*
        //获取当前学期
        CommonResponse<AcademicYear> academicYear = this.academicYearClient.current();
        if (academicYear.success()) {
            //获取当前学期和当前角色可以操作的指标
            CommonResponse<FirstKpi> firstKpi = firstKpiClient.findAllByAcademicYearAndIds(academicYear.getData().getAcademicYearId(), new IdsRequest().addFirstIds(this.STUDENT_FIRST_KPI));
            if (firstKpi.success()) {
                //从CommonResponse<FirstKpi>里遍历当前的firstKpiId 封装为IdsRequest 传入第二指标微服务
                //第二指标微服务找出第一指标中指定中的二级指标
                CommonResponse<SecondKpi> secondKpi = this.secondKpiClient.findAllByFirstKpiIds(this.getFirstKpiIdsRequest(firstKpi.getTable()));
                if (secondKpi.success()) {
                    //从CommonResponse<SecondKpi>中遍历所有的secondKpiId 封装为IdsRequest 传入第三指标微服务
                    //第三指标微服务找出第二指标中指定中的三级指标
                    CommonResponse<ThirdKpi> thirdKpi = thirdKpiClient.findAllBySecondKpiIds(this.getSecondKpiIdsRequest(secondKpi.getTable()));
                    if (thirdKpi.success()) {
                        return thirdKpi;
                    } else {
                        return new CommonResponse<ThirdKpi>().error(thirdKpi.getMessage());
                    }
                } else {
                    return new CommonResponse<ThirdKpi>().error(secondKpi.getMessage());
                }

            } else {
                return new CommonResponse<ThirdKpi>().error(firstKpi.getMessage());
            }

        } else {
            return new CommonResponse<ThirdKpi>().error(academicYear.getMessage());
        }
         */
        CommonResponse<Right> rightCommonResponse = rightClient.findRightById(-1);
        if (rightCommonResponse.success()) {
            Right right = rightCommonResponse.getData();
            if (!right.getFirstKpiId().isEmpty()) {
                CommonResponse<ThirdKpi> thirdKpi = thirdKpiClient.findAllBySecondKpiIds(new IdsRequest().addFirstIds(right.getSecondKpiId()).addSecondIds(right.getThirdKpiId()));
                if (thirdKpi.success()) {
                    return thirdKpi;
                } else {
                    return new CommonResponse<ThirdKpi>().error(thirdKpi.getMessage());
                }
            } else {
                return new CommonResponse<ThirdKpi>().error("您没有任何权限");
            }
            //System.out.println(right.toString());
            //System.out.println(new IdsRequest().addFirstIds(right.getSecondKpiId()).addSecondIds(right.getThirdKpiId()).toString());
        } else {
            return new CommonResponse<ThirdKpi>().error(rightCommonResponse.getMessage());
        }

    }

    @Override
    public CommonResponse<Course> currentCourse(Integer classId) {
        CommonResponse<Term> commonResponse = academicYearClient.currentTerm();
        if (commonResponse.success()) {
            return courseClient.findCourseByClassIdAndTermId(classId, commonResponse.getData().getTermId());
        } else {
            return new CommonResponse<Course>().error(commonResponse.getMessage());
        }

    }

    /*
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
            CommonResponse<FirstKpi> firstKpiCommonResponse = this.firstKpiClient.findAllByAcademicYearAndIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(firstKpiId));
            if (firstKpiCommonResponse != null && firstKpiCommonResponse.success()) {
                return firstKpiCommonResponse.getTable();
            }
            return null;
        }

        private List<SecondKpi> findAllSecondKpiByFirstIds(IdsRequest idsRequest) {
            CommonResponse<SecondKpi> secondKpiCommonResponse = this.secondKpiClient.findAllByFirstKpiIds(idsRequest);
            if (secondKpiCommonResponse != null && secondKpiCommonResponse.success()) {
                return secondKpiCommonResponse.getTable();
            }
            return null;
        }

        private List<ThirdKpi> findAllThirdKpiBySecondKpiIds(IdsRequest idsRequest) {
            CommonResponse<ThirdKpi> thirdKpiCommonResponse = this.thirdKpiClient.findAllBySecondKpiIds(idsRequest);
            if (thirdKpiCommonResponse != null && thirdKpiCommonResponse.success()) {
                return thirdKpiCommonResponse.getTable();
            }
            return null;
        }
        private List<FirstKpiResponse> createFirstKpiResponse(AcademicYear academicYear, List<FirstKpi> firstKpiList, List<SecondKpi> secondKpiList, List<ThirdKpi> thirdKpiList) {
            List<FirstKpiResponse> firstKpiResponseList = new ArrayList<>(firstKpiList.size());
            for (FirstKpi firstKpi : firstKpiList) {
                FirstKpiResponse firstKpiResponse = new FirstKpiResponse().addFirstKpi(firstKpi).addAcademic(academicYear);
                List<SecondKpiResponse> secondKpiResponseList = new ArrayList<>(secondKpiList.size());
                for (SecondKpi secondKpi : secondKpiList) {
                    if (secondKpi.getFirstKpiId().intValue() == firstKpiResponse.getFirstKpiId().intValue()) {
                        SecondKpiResponse secondKpiResponse = new SecondKpiResponse().addSecondKpi(secondKpi);
                        for (ThirdKpi thirdKpi : thirdKpiList) {
                            if (thirdKpi.getSecondKpiId().intValue() == secondKpi.getSecondKpiId().intValue()) {
                                secondKpiResponse.addT(thirdKpi);
                            }
                        }
                        secondKpiResponseList.add(secondKpiResponse);
                    } else {
                        continue;
                    }
                }
                firstKpiResponse.setTable(secondKpiResponseList);
                firstKpiResponseList.add(firstKpiResponse);
            }

            return firstKpiResponseList;
        }
    */
}
