package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.TeacherMapper;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.TeacherService;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Transactional
    @Override
    public void addTeacher(TeacherRequest request) {
        teacherMapper.insert(request.build());
        log.info("add teacher {}", request);
    }

    @Transactional
    @Override
    public void deleteTeacher(Integer workId) {
        this.teacherMapper.deleteByPrimaryKey(workId);
        log.info("delete teacher {}", workId);
    }

    @Transactional
    @Override
    public void updateTeacher(TeacherRequest request) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(request.getWorkId());
        if (teacher != null) {
            Teacher newTeacher = request.build();
            if (request.getTeacherSecret() == null || "".equals(request.getTeacherSecret())) {
                newTeacher.setTeacherSecret(null);
            }
            this.teacherMapper.updateByPrimaryKeySelective(newTeacher);
            log.info("update teacher {}", request);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> findTeacherByPassword(Integer workId, String password) {
        Map<String, Object> map = new HashMap<>();
        Teacher teacher = teacherMapper.selectByPrimaryKey(workId);

        if (teacher != null) {
            if (password.equals(teacher.getTeacherSecret())) {
                map.put("code", 1);
                map.put("msg", "登录成功");
                map.put("obj", teacher);
            } else {
                map.put("code", -1);
                map.put("msg", "工号或密码错误");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "工号不存在");
        }
        return map;

    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAll(Integer page, Integer size) {
        HashMap<String, Integer> parameter = new HashMap<>();
        parameter.put("page", page);
        parameter.put("size", size);
        return teacherMapper.findAll(parameter);
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher findTeacherByWorldId(Integer workId) {
        return teacherMapper.selectByPrimaryKey(workId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAllTeacherByRoleAndDepartment(Integer departmentId, Integer stateId, Integer roleId) {
        HashMap<String, Integer> parameter = new HashMap<>();
        parameter.put("departmentId", departmentId);
        parameter.put("stateId", stateId);
        parameter.put("roleId", roleId);
        return this.teacherMapper.findAllTeacherByRoleAndDepartment(parameter);
    }

    @Override
    public boolean restPassword(Integer workId, String newPassword, String oldPassword) {
        Teacher teacher = this.teacherMapper.selectByPrimaryKey(workId);
        if (teacher != null && teacher.getTeacherSecret().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            teacher.setTeacherSecret(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            this.teacherMapper.restPassword(teacher);
            return true;
        }
        return false;
    }
}
