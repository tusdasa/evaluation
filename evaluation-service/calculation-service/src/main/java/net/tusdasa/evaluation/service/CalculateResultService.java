package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-25 9:06 PM
 */

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
        CommonResponse<TeacherSituation> situationCommonResponse = this.analysisClient.findTeacherSituation(id);
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
