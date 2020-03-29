package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.StudentClassMapper;
import net.tusdasa.evaluation.dao.StudentMapper;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.StudentService;
import net.tusdasa.evaluation.vo.StudentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    private StudentClassMapper studentClassMapper;


    public StudentServiceImpl(StudentMapper studentMapper, StudentClassMapper studentClassMapper) {
        this.studentMapper = studentMapper;
        this.studentClassMapper = studentClassMapper;
    }

    @Override
    @Transactional
    public void addStudent(StudentRequest request) {
        request.setStudentSecret(DigestUtils.md5DigestAsHex(request.getStudentSecret().getBytes()));
        this.studentMapper.insert(request.build());
        log.info("add student {}", request.build());
    }

    @Override
    @Transactional
    public void updateStudent(StudentRequest request) {
        Student student = this.studentMapper.selectByPrimaryKey(request.getStudentId());
        if (student != null) {
            if (request.getStudentSecret() != null) {
                student.setStudentSecret(request.build().getStudentSecret());
            }
            StudentClass studentClass = student.getStudentClass();
            studentClass.setClassId(request.getClassId());
            student.setStudentClass(studentClass);
            this.studentMapper.updateByPrimaryKeySelective(student);
            log.info("update student {}", student);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Student findStudentById(Long studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        this.studentMapper.deleteByPrimaryKey(studentId);
        log.info("delete student {}", studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> findStudentByIdAndPassword(Long studentId, String password) {
        Map<String, Object> result = new HashMap<>();
        Student student = this.studentMapper.selectByPrimaryKey(studentId);

        if (student != null) {
            if (password.equals(student.getStudentSecret())) {
                result.put("code", 1);
                result.put("msg", "登录成功");
                result.put("obj", student);
            } else {
                result.put("code", -1);
                result.put("msg", "学号或密码错误");
            }
        } else {
            result.put("code", 0);
            result.put("msg", "学号不存在");
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllByPage(Integer page, Integer size) {
        HashMap<String, Integer> parameter = new HashMap<>();
        parameter.put("page", page);
        parameter.put("size", size);
        return studentMapper.findAll(parameter);
    }

    @Override
    public boolean resetPassword(Long studentId, String newPassword, String oldPassword) {
        Student student = this.studentMapper.selectByPrimaryKey(studentId);
        if (student != null && student.getStudentSecret().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            student.setStudentSecret(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            this.studentMapper.resetPassword(student);
            return true;
        }
        return false;
    }

    @Override
    public long countByDepartmentId(Integer departmentId) {
        return this.studentMapper.countByDepartmentId(departmentId);
    }
}
