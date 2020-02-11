package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.StudentCourseResult;

public interface StudentCourseResultService {
    void addStudentCourseResult(StudentCourseResult studentCourseResult);

    StudentCourseResult findBydStudentId(Long studentId);
}
