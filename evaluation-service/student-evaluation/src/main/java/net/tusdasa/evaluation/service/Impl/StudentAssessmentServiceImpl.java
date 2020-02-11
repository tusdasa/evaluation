package net.tusdasa.evaluation.service.Impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentAssessmentServiceImpl implements StudentAssessmentService {

    private final Integer STUDENT_RIGHT = -1;

    private AcademicYearClient academicYearClient;

    //private FirstKpiClient firstKpiClient;

    //private SecondKpiClient secondKpiClient;

    private ThirdKpiClient thirdKpiClient;

    private RightClient rightClient;

    private CourseClient courseClient;

    private StudentCourseResultService studentAssessmentService;


    public StudentAssessmentServiceImpl(AcademicYearClient academicYearClient,
                                        //FirstKpiClient firstKpiClient,
                                        //SecondKpiClient secondKpiClient,
                                        ThirdKpiClient thirdKpiClient,
                                        RightClient rightClient,
                                        CourseClient courseClient,
                                        StudentCourseResultService studentAssessmentService) {
        this.academicYearClient = academicYearClient;
        //this.firstKpiClient = firstKpiClient;
        //this.secondKpiClient = secondKpiClient;
        this.thirdKpiClient = thirdKpiClient;
        this.rightClient = rightClient;
        this.courseClient = courseClient;
        this.studentAssessmentService = studentAssessmentService;
        this.academicYearClient = academicYearClient;
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

    @Override
    public CommonResponse<ThirdKpi> findAll() {
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
        } else {
            return new CommonResponse<ThirdKpi>().error(rightCommonResponse.getMessage());
        }

    }

    @Override
    public CommonResponse<Course> currentCourse(String token) {
        CommonResponse<Student> studentCommonResponse = this.rightClient.checkStudent(token);
        if (studentCommonResponse.success()) {
            CommonResponse<Term> commonResponse = academicYearClient.currentTerm();
            if (commonResponse.success()) {
                CommonResponse<Course> courseCommonResponse = courseClient.findCourseByClassIdAndTermId(studentCommonResponse.getData().getStudentClass().getClassId(), commonResponse.getData().getTermId());
                return courseCommonResponse;
            } else {
                return new CommonResponse<Course>().error(commonResponse.getMessage());
            }
        } else {
            return new CommonResponse<Course>().error(studentCommonResponse.getMessage());
        }
    }

}
