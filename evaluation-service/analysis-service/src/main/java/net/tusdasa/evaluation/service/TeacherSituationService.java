package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.TeacherSituationDao;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.mongodb.TeacherEvaluationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: tusdasa
 * @Date: 2020-02-28 5:07 PM
 */

@Service
public class TeacherSituationService {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherSituationService.class);

    private TeacherEvaluationDao teacherEvaluationDao;

    private TeacherClient teacherClient;

    private AcademicYearClient academicYearClient;

    private TeacherSituationDao teacherSituationDao;

    public TeacherSituationService(TeacherEvaluationDao teacherEvaluationDao, TeacherClient teacherClient, AcademicYearClient academicYearClient, TeacherSituationDao teacherSituationDao) {
        this.teacherEvaluationDao = teacherEvaluationDao;
        this.teacherClient = teacherClient;
        this.academicYearClient = academicYearClient;
        this.teacherSituationDao = teacherSituationDao;
    }

    private Term findCurrentTerm() {
        CommonResponse<Term> termCommonResponse = this.academicYearClient.currentTerm();
        if (termCommonResponse.success()) {
            return termCommonResponse.getData();
        }
        LOG.error("AcademicYear service unavailable");
        return null;
    }

    private Optional<TeacherSituation> findByTeacher(Integer workId) {
        return this.teacherSituationDao.findById(workId);
    }

    private Teacher findTeacherById(Integer workId) {
        CommonResponse<Teacher> teacherCommonResponse = teacherClient.getTeacherById(workId);
        if (teacherCommonResponse.success()) {
            return teacherCommonResponse.getData();
        }
        LOG.error("Teacher service unavailable");
        return null;
    }

    public void addTeacherSituation(TeacherEvaluation teacherEvaluation) {
        teacherEvaluationDao.save(teacherEvaluation);

        Optional<TeacherSituation> optional = this.findByTeacher(teacherEvaluation.getWorkId());

        Teacher evaluator = this.findTeacherById(teacherEvaluation.getEvaluatorId());

        Term term = this.findCurrentTerm();


        if (evaluator == null || term == null) {
            LOG.error("service unavailable");
            throw new RuntimeException("服务异常");
        }

        if (optional.isPresent()) {
            TeacherSituation situation = optional.get();
            List<FactorTeacher> factorTeacherList = situation.getFactorTeacherList();
            boolean flag = false;
            for (FactorTeacher factorTeacher : factorTeacherList) {
                if (teacherEvaluation.getTermId().equals(factorTeacher.getTermId())) {
                    factorTeacher.add(evaluator.getTeacherName(), teacherEvaluation.getTotal());
                    flag = true;
                    break;
                }
            }

            // 遍历完了, 没有找到对应的学期
            if (!flag) {
                FactorTeacher factorTeacher = new FactorTeacher(term);
                factorTeacher.add(evaluator.getTeacherName(), teacherEvaluation.getTotal());
                situation.add(factorTeacher);
            }

            // FactorTeacher factorTeacher = new FactorTeacher(term);
            // factorTeacher.add(evaluator.getTeacherName(), teacherEvaluation.getTotal());
            this.teacherSituationDao.save(situation);
        } else {
            Teacher teacher = this.findTeacherById(teacherEvaluation.getWorkId());
            if (teacher == null) {
                LOG.error("service unavailable");
                throw new RuntimeException("服务异常");
            }
            TeacherSituation teacherSituation = this.getTeacherSituation(teacher);
            FactorTeacher factorTeacher = new FactorTeacher(term);
            factorTeacher.add(evaluator.getTeacherName(), teacherEvaluation.getTotal());
            this.teacherSituationDao.save(teacherSituation.add(factorTeacher));
        }

    }

    private TeacherSituation getTeacherSituation(Teacher teacher) {
        TeacherSituation teacherSituation = new TeacherSituation();
        teacherSituation.setTeacherName(teacher.getTeacherName());
        teacherSituation.setId(teacher.getWorkId());
        teacherSituation.setDepartmentName(teacher.getDepartment().getDepartmentName());
        teacherSituation.setDepartmentId(teacher.getDepartment().getDepartmentId());
        return teacherSituation;
    }

}
