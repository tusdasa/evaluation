package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.StudentEvaluationDao;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.service.StudentEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentEvaluationServiceImpl implements StudentEvaluationService {

    private StudentEvaluationDao studentEvaluationDao;

    public StudentEvaluationServiceImpl(StudentEvaluationDao studentEvaluationDao) {
        this.studentEvaluationDao = studentEvaluationDao;
    }

    @Override
    public StudentEvaluation addStudentCourseResult(StudentEvaluation studentEvaluation) {
        return studentEvaluationDao.insert(studentEvaluation);
    }

    @Override
    public void updateCourseResultTotal(StudentEvaluation studentEvaluation) {
        Optional<StudentEvaluation> studentCourseResultOptional = studentEvaluationDao.findById(studentEvaluation.getId());
        if (studentCourseResultOptional.isPresent()) {
            StudentEvaluation old = studentCourseResultOptional.get();
            old.setTotal(studentEvaluation.getTotal());
            studentEvaluationDao.save(old);
        }

    }

    @Override
    public List<StudentEvaluation> findAllByCourseId(Integer courseId) {
        return studentEvaluationDao.findAllByCourseId(courseId);
    }

    @Override
    public StudentEvaluation findAllById(String id) {
        return studentEvaluationDao.findAllById(id).get();
    }

    @Override
    public List<StudentEvaluation> findAllByCourseIdAndTermId(Integer courseId, Integer termId) {
        return studentEvaluationDao.findAllByCourseIdAndTermId(courseId, termId);
    }

    @Override
    public List<StudentEvaluation> findAllByStudentId(Long studentId) {
        return studentEvaluationDao.findAllByStudentId(studentId);
    }

    @Override
    public List<StudentEvaluation> findAllByStudentIdAndAndTermId(Long studentId, Integer termId) {
        return studentEvaluationDao.findAllByStudentIdAndAndTermId(studentId, termId);
    }
}
