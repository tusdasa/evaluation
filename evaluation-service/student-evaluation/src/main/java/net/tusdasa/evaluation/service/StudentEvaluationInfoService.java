package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.StudentEvaluation;

import java.util.List;

public interface StudentEvaluationInfoService {

    //CommonResponse<ThirdKpi> findAll(Integer role);

    //CommonResponse<Course> currentCourse(Long studentId);

    //List<StudentEvaluation> findBydStudentId(Long studentId);

    List<StudentEvaluation> findAllByStudentIdAndTermId(Long studentId, Integer termId);

    StudentEvaluation addStudentCourseResult(StudentEvaluation studentEvaluation, Student student);

    List<Course> checkAllCourse(List<Course> courseList, List<StudentEvaluation> studentEvaluationList);

    long countAllByTermId(Integer termId);

    void deleteAllByTermId(Integer termId);

    long countAllByTermIdAndAndDepartmentId(Integer termId, Integer departmentId);


}
