package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.StudentClassMapper;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.StudentClassService;
import net.tusdasa.evaluation.utils.CheckUtils;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<StudentClass> findAll(Integer departmentId, Integer gradeId, Integer majorId) {

        Map<String, Integer> parameter = new HashMap<>();

        if (CheckUtils.isIntegerNumber(departmentId)) {
            parameter.put("departmentId", departmentId);
        }
        if (CheckUtils.isIntegerNumber(gradeId)) {
            parameter.put("gradeId", gradeId);
        }
        if (CheckUtils.isIntegerNumber(majorId)) {
            parameter.put("majorId", majorId);
        }

        return this.studentClassMapper.findAllByAll(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentClass findStudentClassById(Integer studentClassId) {
        return this.studentClassMapper.selectByPrimaryKey(studentClassId);
    }
}
