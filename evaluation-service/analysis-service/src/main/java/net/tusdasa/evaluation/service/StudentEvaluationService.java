package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.client.StudentClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.StudentTeachingSituationDao;
import net.tusdasa.evaluation.entity.*;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 8:29 PM
 */

@Service
public class StudentEvaluationService {

    private StudentTeachingSituationDao studentTeachingSituationDao;

    private CourseClient courseClient;

    private StudentClient studentClient;

    private TeacherClient teacherClient;

    public StudentEvaluationService(StudentTeachingSituationDao studentTeachingSituationDao, CourseClient courseClient, StudentClient studentClient, TeacherClient teacherClient) {
        this.studentTeachingSituationDao = studentTeachingSituationDao;
        this.courseClient = courseClient;
        this.studentClient = studentClient;
        this.teacherClient = teacherClient;
    }

    public StudentTeachingSituation findByWorkId(Integer workId) {
        return this.studentTeachingSituationDao.findById(workId).get();
    }

    public Course findCourseByCourseId(Long courseId) {
        CommonResponse<Course> courseCommonResponse = courseClient.findByCourseId(courseId);
        if (courseCommonResponse.success()) {
            return courseCommonResponse.getData();
        }
        return null;
    }

    public Student findStudentById(Long studentId) {
        CommonResponse<Student> studentCommonResponse = studentClient.getStudentById(studentId);
        if (studentCommonResponse.success()) {
            return studentCommonResponse.getData();
        }
        return null;
    }

    public Teacher findTeacherById(Integer workId) {
        CommonResponse<Teacher> teacherCommonResponse = teacherClient.getTeacherById(workId);
        if (teacherCommonResponse.success()) {
            return teacherCommonResponse.getData();
        }
        return null;
    }

    public StudentTeachingSituation add(StudentTeachingSituation studentTeachingSituation, StudentEvaluation studentEvaluation) {

        Student student = this.findStudentById(studentEvaluation.getStudentId());
        Course course = this.findCourseByCourseId(studentEvaluation.getCourseId());

        if (studentTeachingSituation != null) {


        } else {
            Teacher teacher = this.findTeacherById(course.getTeacherWorkId());

            studentTeachingSituation = this.getStudentTeachingSituation(teacher);

        }


        return null;
    }

    public FactorClasses getFactorClasses(Student student) {
        return new FactorClasses(student);
    }

    public FactorCourse getFactorCourse(Course course) {
        return new FactorCourse(course);
    }

    public StudentTeachingSituation getStudentTeachingSituation(Teacher teacher) {
        return new StudentTeachingSituation(teacher);
    }

}
