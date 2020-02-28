package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.TeacherSituationDao;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.entity.TeacherEvaluation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.mongodb.TeacherEvaluationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

        if (evaluator == null) {
            LOG.error("service unavailable");
            return;
        }

        if (optional.isPresent()) {
            TeacherSituation situation = optional.get();
            situation.add(evaluator.getTeacherName(), teacherEvaluation.getTotal());
            System.out.println(situation);
            this.teacherSituationDao.save(situation);
        } else {

            Term term = this.findCurrentTerm();
            Teacher teacher = this.findTeacherById(teacherEvaluation.getWorkId());
            if (term == null || teacher == null) {
                LOG.error("service unavailable");
                return;
            }
            TeacherSituation teacherSituation = new TeacherSituation();
            teacherSituation.setTeacherName(teacher.getTeacherName());
            teacherSituation.setTermName(term.getTermName());
            teacherSituation.setId(teacher.getWorkId());
            teacherSituation.add(evaluator.getTeacherName(), teacherEvaluation.getTotal());
            System.out.println(teacherSituation);
            this.teacherSituationDao.save(teacherSituation);
        }

    }

}
