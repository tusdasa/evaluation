package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.StudentClassMapper;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.StudentClassService;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class StudentClassServiceImpl implements StudentClassService {

    private StudentClassMapper studentClassMapper;

    public StudentClassServiceImpl(StudentClassMapper studentClassMapper) {
        this.studentClassMapper = studentClassMapper;
    }
    @Transactional
    @Override
    public void addStudentClass(StudentClassRequest request) {
        this.studentClassMapper.insert(request.build());
        log.info("add student class {}", request);
    }

    @Transactional
    @Override
    public void deleteStudentClass(Integer studentClassId) {
        this.studentClassMapper.deleteByPrimaryKey(studentClassId);
        log.info("delete student class {}", studentClassId);
    }

    @Transactional
    @Override
    public void updateStudentClass(StudentClassRequest request) {
        StudentClass studentClass = this.studentClassMapper.selectByPrimaryKey(request.getClassId());
        if (studentClass != null && studentClass.compareTo(request) != 0) {
            this.studentClassMapper.updateByPrimaryKeySelective(request.build());
            log.info("update student class {}", request);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentClass> findAll() {
        return this.studentClassMapper.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public StudentClass findStudentClassById(Integer studentClassId) {
        return this.studentClassMapper.selectByPrimaryKey(studentClassId);
    }
}
