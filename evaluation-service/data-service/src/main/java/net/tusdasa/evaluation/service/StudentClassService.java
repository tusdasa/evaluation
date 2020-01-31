package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.vo.StudentClassRequest;

import java.util.List;

/**
 * 添加新学生时需要添加
 */
public interface StudentClassService {
    void addStudentClass(StudentClassRequest request);

    void deleteStudentClass(Integer studentClassId);

    void updateStudentClass(StudentClassRequest request);

    List<StudentClass> findAll();

    List<StudentClass> findAll(Integer departmentId, Integer gradeId, Integer majorId);

    StudentClass findStudentClassById(Integer studentClassId);
}
