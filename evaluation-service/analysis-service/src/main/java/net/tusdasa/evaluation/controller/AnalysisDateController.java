package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.StudentSituationDao;
import net.tusdasa.evaluation.dao.TeacherSituationDao;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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
    public CommonResponse<TeacherSituation> findTeacherSituationById(@PathVariable("id") Integer id) {
        TeacherSituation teacherSituation = teacherSituationDao.findById(id).orElse(null);

        if (teacherSituation != null) {
            return new CommonResponse<TeacherSituation>().ok().data(teacherSituation);
        }
        return new CommonResponse<TeacherSituation>().error("未找到");
    }

    @PostMapping("student/ids")
    public CommonResponse<StudentSituation> findStudentSituationByIds(@RequestBody IdsRequest idsRequest) {
        Iterable<StudentSituation> iterable = studentSituationDao.findAllById(idsRequest.getFirstIds());
        List<StudentSituation> studentSituationList = new LinkedList<>();
        iterable.forEach(studentSituationList::add);
        return new CommonResponse<StudentSituation>().ok().table(studentSituationList);
    }

    @PostMapping("teacher/ids")
    public CommonResponse<TeacherSituation> findTeacherSituationByIds(@RequestBody IdsRequest idsRequest) {

        Iterable<TeacherSituation> iterable = teacherSituationDao.findAllById(idsRequest.getFirstIds());
        List<TeacherSituation> teacherSituationList = new LinkedList<>();
        iterable.forEach(teacherSituationList::add);
        return new CommonResponse<TeacherSituation>().ok().table(teacherSituationList);
    }
/*
    @GetMapping("student/department/{id}")
    public CommonResponse<StudentSituation> findStudentSituationByDepartment(@PathVariable("id") Integer id) {
        List<StudentSituation> studentSituation = studentSituationDao.findAllByDepartmentId(id);
        return new CommonResponse<StudentSituation>().ok().table(studentSituation);
    }
*/

/*
    @PutMapping("up")
    public String update() {
        Optional<StudentSituation> studentSituations = studentSituationDao.findById(1000000007);
        if (studentSituations.isPresent()){
            StudentSituation studentSituation = studentSituations.get();
            List<FactorCourse> factorCourseList = studentSituation.getFactorCourseList();
            for (FactorCourse factorCourse:factorCourseList) {
                List<FactorClasses> factorClassesList = factorCourse.getClassesList();
                for (FactorClasses factorClasses :factorClassesList) {
                    if (factorClasses.getClassesName().equals("计科1901")){
                        factorClasses.setGradeName("2019级");
                    }
                    if (factorClasses.getClassesName().equals("计科1801")){
                        factorClasses.setGradeName("2018级");
                    }
                }
                if (factorCourse.getId() == 10L){
                    factorCourse.setTermName("2019-2020学年第二学期");
                    factorCourse.setTermId(2);
                }
            }
            studentSituationDao.save(studentSituation);
        }
        return "1";
    }
 */
}
