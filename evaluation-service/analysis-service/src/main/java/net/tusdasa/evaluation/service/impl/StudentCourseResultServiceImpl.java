package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.StudentCourseResultDao;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentCourseResultServiceImpl implements StudentCourseResultService {

    private StudentCourseResultDao studentCourseResultDao;

    public StudentCourseResultServiceImpl(StudentCourseResultDao studentCourseResultDao) {
        this.studentCourseResultDao = studentCourseResultDao;
    }

    @Override
    public StudentCourseResult addStudentCourseResult(StudentCourseResult studentCourseResult) {
        return studentCourseResultDao.insert(studentCourseResult);
    }

    @Override
    public void updateCourseResultTotal(StudentCourseResult studentCourseResult) {
        Optional<StudentCourseResult> studentCourseResultOptional = studentCourseResultDao.findById(studentCourseResult.getId());
        if (studentCourseResultOptional.isPresent()) {
            StudentCourseResult old = studentCourseResultOptional.get();
            old.setTotal(studentCourseResult.getTotal());
            studentCourseResultDao.save(old);
        }

    }

    @Override
    public List<StudentCourseResult> findAllByCourseId(Integer courseId) {
        return studentCourseResultDao.findAllByCourseId(courseId);
    }

    @Override
    public StudentCourseResult findAllById(String id) {
        return studentCourseResultDao.findAllById(id).get();
    }

    @Override
    public List<StudentCourseResult> findAllByCourseIdAndTermId(Integer courseId, Integer termId) {
        return studentCourseResultDao.findAllByCourseIdAndTermId(courseId, termId);
    }

    @Override
    public List<StudentCourseResult> findAllByStudentId(Long studentId) {
        return studentCourseResultDao.findAllByStudentId(studentId);
    }

    @Override
    public List<StudentCourseResult> findAllByStudentIdAndAndTermId(Long studentId, Integer termId) {
        return studentCourseResultDao.findAllByStudentIdAndAndTermId(studentId, termId);
    }
}
