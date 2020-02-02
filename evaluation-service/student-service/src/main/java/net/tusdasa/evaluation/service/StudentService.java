package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.vo.StudentRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface StudentService {
    void addStudent(StudentRequest request);

    void updateStudent(StudentRequest request);

    Student findStudentById(Long student_id);

    void deleteStudent(Long studentId);

    Map<String, Object> findStudentByIdAndPassword(Long studentId, String password);

    List<Student> findAllByPage(Integer page, Integer size);
}
