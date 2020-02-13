package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.StudentCourseResult;

import java.util.List;

public interface StudentCourseResultService {

    void addStudentCourseResult(StudentCourseResult studentCourseResult, Long studentId);

    List<StudentCourseResult> findBydStudentId(Long studentId);
}
