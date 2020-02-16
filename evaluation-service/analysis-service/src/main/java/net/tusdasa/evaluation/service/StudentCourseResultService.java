package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.StudentCourseResult;

import java.util.List;

public interface StudentCourseResultService {

    StudentCourseResult addStudentCourseResult(StudentCourseResult studentCourseResult);

    List<StudentCourseResult> findAllByCourseId(Integer courseId);

    StudentCourseResult findAllById(String id);

    List<StudentCourseResult> findAllByCourseIdAndTermId(Integer courseId, Integer termId);

    List<StudentCourseResult> findAllByStudentId(Long studentId);

    List<StudentCourseResult> findAllByStudentIdAndAndTermId(Long studentId, Integer termId);
}
