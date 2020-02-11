package net.tusdasa.evaluation.service.Impl;

import net.tusdasa.evaluation.dao.StudentCourseResultDao;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StudentCourseResultServiceImpl implements StudentCourseResultService {

    private StudentCourseResultDao studentCourseResultDao;

    public StudentCourseResultServiceImpl(StudentCourseResultDao studentCourseResultDao) {
        this.studentCourseResultDao = studentCourseResultDao;
    }

    @Override
    public void addStudentCourseResult(StudentCourseResult studentCourseResult) {
        studentCourseResultDao.insert(studentCourseResult);
    }

    @Override
    public StudentCourseResult findBydStudentId(Long studentId) {
        return studentCourseResultDao.findByStudentId(studentId).get();
    }
}
