package net.tusdasa.evaluation.service;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-25 9:06 PM
 */

@Slf4j
@Service
public class CalculateResultService {

    private AnalysisClient analysisClient;

    private AcademicYearClient academicYearClient;

    private StudentResultService studentResultService;

    private TeacherResultService teacherResultService;

    public CalculateResultService(AnalysisClient analysisClient, AcademicYearClient academicYearClient, StudentResultService studentResultService, TeacherResultService teacherResultService) {
        this.analysisClient = analysisClient;
        this.academicYearClient = academicYearClient;
        this.studentResultService = studentResultService;
        this.teacherResultService = teacherResultService;
    }

    /**
     * @param workId 工号
     * @return 当前学年成绩
     */
    public AcademicYearResult getAcademicYearResult(Integer workId) {
        AcademicYear academicYear = this.getCurrentAcademicYear();
        if (academicYear == null) {
            return null;
        }
        StudentSituation studentSituation = this.getStudentSituation(workId);
        TeacherSituation teacherSituation = this.getTeacherSituation(workId);

        if (studentSituation != null && teacherSituation != null) {
            // 先计算学生成绩
            AcademicYearResult academicYearResult = studentResultService.getStudentResult(studentSituation, academicYear);
            // 计算教师成绩
            return teacherResultService.getTeacherResult(academicYearResult, teacherSituation, academicYear);
        } else {
            return null;
        }

    }

    public List<AcademicYearResult> getAcademicYearResultByIds(IdsRequest idsRequest) {
        List<AcademicYearResult> academicYearResultList = new LinkedList<>();
        if (!idsRequest.getFirstIds().isEmpty()) {
            // 基础数据
            List<TeacherSituation> teacherSituationList = this.findTeacherSituationByIds(idsRequest);
            List<StudentSituation> studentSituationList = this.findStudentSituationByIds(idsRequest);
            AcademicYear academicYear = this.getCurrentAcademicYear();
            if (!teacherSituationList.isEmpty() && !studentSituationList.isEmpty() && academicYear != null) {
                // 有学生评价不一定有教师评价, 有教师评价不一定有学生评价
                for (StudentSituation studentSituation : studentSituationList) {
                    for (TeacherSituation teacherSituation : teacherSituationList) {
                        if (studentSituation.getId().equals(teacherSituation.getId())) {
                            AcademicYearResult academicYearResult = studentResultService.getStudentResult(studentSituation, academicYear);
                            academicYearResultList.add(
                                    // 增加到成绩列表
                                    teacherResultService.getTeacherResult(
                                            academicYearResult,
                                            teacherSituation,
                                            academicYear
                                    )
                            );
                        }
                    }
                }
            }
        }
        return academicYearResultList;
    }

    public List<TeacherSituation> findTeacherSituationByIds(IdsRequest idsRequest) {
        CommonResponse<TeacherSituation> situationCommonResponse = this.analysisClient.findTeacherSituationByIds(idsRequest);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getTable();
        }
        return Collections.emptyList();
    }

    public List<StudentSituation> findStudentSituationByIds(IdsRequest idsRequest) {
        CommonResponse<StudentSituation> situationCommonResponse = this.analysisClient.findStudentSituationByIds(idsRequest);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getTable();
        }
        return Collections.emptyList();
    }

    public AcademicYearResult getAcademicYearResultByAcademicYear(Integer workId, Integer academicYearId) {
        AcademicYear academicYear = this.getAcademicYearById(academicYearId);

        if (academicYear == null) {
            return null;
        }
        StudentSituation studentSituation = this.getStudentSituation(workId);
        TeacherSituation teacherSituation = this.getTeacherSituation(workId);

        if (studentSituation != null && teacherSituation != null) {
            // 先计算学生成绩
            AcademicYearResult academicYearResult = studentResultService.getStudentResult(studentSituation, academicYear);
            // 计算教师成绩
            return teacherResultService.getTeacherResult(academicYearResult, teacherSituation, academicYear);
        } else {
            return null;
        }

    }

    // 获得学生评价结果
    private StudentSituation getStudentSituation(Integer id) {
        CommonResponse<StudentSituation> situationCommonResponse = this.analysisClient.findStudentSituationById(id);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getData();
        }
        return null;
    }

    // 获得教师评价结果
    private TeacherSituation getTeacherSituation(Integer id) {
        CommonResponse<TeacherSituation> situationCommonResponse = this.analysisClient.findTeacherSituationById(id);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getData();
        }
        return null;
    }

    // 获得当前学年
    private AcademicYear getCurrentAcademicYear() {
        CommonResponse<AcademicYear> commonResponse = this.academicYearClient.current();
        if (commonResponse.success()) {
            return commonResponse.getData();
        }
        return null;
    }

    // 获得当前学年
    private AcademicYear getAcademicYearById(Integer academicYearId) {
        CommonResponse<AcademicYear> commonResponse = this.academicYearClient.findByAcademicYearId(academicYearId);
        if (commonResponse.success()) {
            return commonResponse.getData();
        }
        return null;
    }
}
