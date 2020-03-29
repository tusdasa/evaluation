package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.StudentEvaluationClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.client.TeacherEvaluationClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.entity.Term;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-29 1:32 PM
 */

@Service
public class EvaluationService {

    private StudentEvaluationClient studentEvaluationClient;

    private TeacherEvaluationClient teacherEvaluationClient;

    private TeacherClient teacherClient;

    private AcademicYearClient academicYearClient;

    public EvaluationService(StudentEvaluationClient studentEvaluationClient, TeacherEvaluationClient teacherEvaluationClient, TeacherClient teacherClient, AcademicYearClient academicYearClient) {
        this.studentEvaluationClient = studentEvaluationClient;
        this.teacherEvaluationClient = teacherEvaluationClient;
        this.teacherClient = teacherClient;
        this.academicYearClient = academicYearClient;
    }

    private Teacher getTeacher(Integer workId) {
        CommonResponse<Teacher> teacherCommonResponse = teacherClient.getTeacherById(workId);
        if (teacherCommonResponse.success()) {
            return teacherCommonResponse.getData();
        }
        return null;
    }

    private AcademicYear getCurrentAcademicYear() {
        CommonResponse<AcademicYear> academicYearCommonResponse = this.academicYearClient.current();
        if (academicYearCommonResponse.success()) {
            return academicYearCommonResponse.getData();
        }
        return null;
    }

    private Term getTerm() {
        CommonResponse<Term> commonResponse = this.academicYearClient.currentTerm();
        if (commonResponse.success()) {
            return commonResponse.getData();
        }
        return null;
    }

    private CommonResponse<Long> getStudentEvaluation(Term term) {
        return this.studentEvaluationClient.countAllByTermId(term.getTermId());
    }

    public CommonResponse<Long> getAllStudent() {
        Term term = this.getTerm();
        if (term != null) {
            return this.getStudentEvaluation(term);
        }
        return new CommonResponse<Long>().busy();
    }

    public CommonResponse<Long> getAllTeacher() {
        Term term = this.getTerm();
        if (term != null) {
            return this.teacherEvaluationClient.countAll(term.getTermId());
        }
        return new CommonResponse<Long>().busy();
    }

    public CommonResponse<String> deleteAllTeacher() {
        Term term = this.getTerm();
        if (term != null) {
            return studentEvaluationClient.deleteAllByTermId(term.getTermId());
        }
        return new CommonResponse<String>().busy();
    }

    public CommonResponse<String> deleteAllStudent() {
        Term term = this.getTerm();
        if (term != null) {
            return teacherEvaluationClient.deleteTeacherResult(term.getTermId());
        }
        return new CommonResponse<String>().busy();
    }


}
