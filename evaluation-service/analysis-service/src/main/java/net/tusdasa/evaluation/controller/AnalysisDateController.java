package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.StudentSituationDao;
import net.tusdasa.evaluation.dao.TeacherSituationDao;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 12:59 PM
 */

@RestController
public class AnalysisDateController {

    private StudentSituationDao studentSituationDao;

    private TeacherSituationDao teacherSituationDao;

    public AnalysisDateController(StudentSituationDao studentSituationDao, TeacherSituationDao teacherSituationDao) {
        this.studentSituationDao = studentSituationDao;
        this.teacherSituationDao = teacherSituationDao;
    }

    @GetMapping("student/{id}")
    public CommonResponse<StudentSituation> findStudentSituationById(@PathVariable("id") Integer id) {
        StudentSituation studentSituation = studentSituationDao.findById(id).orElse(null);
        if (studentSituation != null) {
            return new CommonResponse<StudentSituation>().ok().data(studentSituation);
        }
        return new CommonResponse<StudentSituation>().error("未找到");
    }

    @GetMapping("teacher/{id}")
    public CommonResponse<TeacherSituation> findTeacherSituation(@PathVariable("id") Integer id) {
        TeacherSituation teacherSituation = teacherSituationDao.findById(id).orElse(null);

        if (teacherSituation != null) {
            return new CommonResponse<TeacherSituation>().ok().data(teacherSituation);
        }
        return new CommonResponse<TeacherSituation>().error("未找到");

    }
}
