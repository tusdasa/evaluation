package net.tusdasa.evaluation.service.Impl;

import net.tusdasa.evaluation.dao.StudentCourseResultDao;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StudentCourseResultServiceImpl implements StudentCourseResultService {

    private StudentCourseResultDao studentCourseResultDao;

    public StudentCourseResultServiceImpl(StudentCourseResultDao studentCourseResultDao) {
        this.studentCourseResultDao = studentCourseResultDao;
    }

    @Override
    public void addStudentCourseResult(StudentCourseResult studentCourseResult, Long studentId) {
        studentCourseResult.setId(UUIDUtils.UUID());
        studentCourseResult.setStudentId(studentId);
        studentCourseResultDao.insert(studentCourseResult);
    }

    @Override
    public List<StudentCourseResult> findBydStudentId(Long studentId) {
        return studentCourseResultDao.findAllByStudentId(studentId);
    }
}
