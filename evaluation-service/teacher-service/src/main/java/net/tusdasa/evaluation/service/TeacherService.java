package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.vo.TeacherRequest;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    void addTeacher(TeacherRequest request);

    void deleteTeacher(Integer workId);

    void updateTeacher(TeacherRequest request);

    Map<String, Object> findTeacherByPassword(Integer workId, String password);

    List<Teacher> findAll(Integer page, Integer size);

    Teacher findTeacherByWorldId(Integer workId);

    List<Teacher> findAllTeacherByRoleAndDepartment(Integer departmentId, Integer stateId, Integer roleId);

    List<Teacher> findAllTeacherByRoleAndDepartmentByPage(Integer departmentId, Integer stateId, Integer roleId, int page, int size);

    boolean restPassword(Integer workId, String newPassword, String oldPassword);
}
