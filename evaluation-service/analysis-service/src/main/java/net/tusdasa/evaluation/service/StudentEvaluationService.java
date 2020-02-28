package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.client.CourseClient;
import net.tusdasa.evaluation.client.StudentClient;
import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.dao.StudentTeachingSituationDao;
import net.tusdasa.evaluation.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @Author: tusdasa
 * @Date: 2020-02-27 8:29 PM
 */

@Service
public class StudentEvaluationService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentEvaluationService.class);

    private StudentTeachingSituationDao studentTeachingSituationDao;

    private CourseClient courseClient;

    private StudentClient studentClient;

    private TeacherClient teacherClient;

    private AcademicYearClient academicYearClient;

    public StudentEvaluationService(StudentTeachingSituationDao studentTeachingSituationDao, CourseClient courseClient, StudentClient studentClient, TeacherClient teacherClient, AcademicYearClient academicYearClient) {
        this.studentTeachingSituationDao = studentTeachingSituationDao;
        this.courseClient = courseClient;
        this.studentClient = studentClient;
        this.teacherClient = teacherClient;
        this.academicYearClient = academicYearClient;
    }

    private Term findCurrentTerm() {
        CommonResponse<Term> termCommonResponse = this.academicYearClient.currentTerm();
        if (termCommonResponse.success()) {
            return termCommonResponse.getData();
        }
        return null;
    }

    private StudentTeachingSituation findByWorkId(Integer workId) {
        return this.studentTeachingSituationDao.findById(workId).get();
    }

    private Course findCourseByCourseId(Long courseId) {
        CommonResponse<Course> courseCommonResponse = courseClient.findByCourseId(courseId);
        if (courseCommonResponse.success()) {
            return courseCommonResponse.getData();
        }
        LOG.error("Course service unavailable");
        return null;
    }

    private Student findStudentById(Long studentId) {
        CommonResponse<Student> studentCommonResponse = studentClient.getStudentById(studentId);
        if (studentCommonResponse.success()) {
            return studentCommonResponse.getData();
        }
        LOG.error("Student service unavailable");
        return null;
    }

    private Teacher findTeacherById(Integer workId) {
        CommonResponse<Teacher> teacherCommonResponse = teacherClient.getTeacherById(workId);
        if (teacherCommonResponse.success()) {
            return teacherCommonResponse.getData();
        }
        LOG.error("Teacher service unavailable");
        return null;
    }

    public void add(StudentEvaluation studentEvaluation) {

        Student student = this.findStudentById(studentEvaluation.getStudentId());
        Course course = this.findCourseByCourseId(studentEvaluation.getCourseId());
        Term term = this.findCurrentTerm();

        StudentTeachingSituation studentTeachingSituation = null;

        if (course != null && student != null && term != null) {
            Optional<StudentTeachingSituation> optional = this.studentTeachingSituationDao.findById(course.getTeacherWorkId());
            if (optional.isPresent()) {
                studentTeachingSituation = optional.get();
            }
        } else {
            LOG.error("calculate error service unavailable {}", studentEvaluation);
        }

        FactorClasses factorClasses = this.getFactorClasses(student, studentEvaluation);
        if (studentTeachingSituation != null) {
            FactorCourse factorCourse = this.getFactorCourse(course, term);
            StudentTeachingSituation ss = this.addNew(studentTeachingSituation, factorClasses, factorCourse, studentEvaluation.getTotal());
            System.out.println(ss.toString());
            this.studentTeachingSituationDao.save(ss);
        } else {
            Teacher teacher = this.findTeacherById(course.getTeacherWorkId());
            if (teacher != null) {
                FactorCourse factorCourse = this.getFactorCourse(course, term).add(factorClasses);
                StudentTeachingSituation situation = this.getStudentTeachingSituation(teacher).add(factorCourse);
                System.out.println(situation.toString());
                this.studentTeachingSituationDao.save(situation);
            } else {
                LOG.error("calculate error service unavailable {}", studentEvaluation);
            }
        }
    }

    private FactorClasses getFactorClasses(Student student, StudentEvaluation studentEvaluation) {
        return new FactorClasses(student).addScore(studentEvaluation.getTotal());
    }

    private FactorCourse getFactorCourse(Course course, Term term) {
        return new FactorCourse(course, term);
    }

    private StudentTeachingSituation getStudentTeachingSituation(Teacher teacher) {
        return new StudentTeachingSituation(teacher);
    }

    /***
     * 当前评价课程的任课教师就是此对象
     * StudentTeachingSituation situation 数据库已经有的结果
     * FactorClasses classes 新的
     * FactorCourse course
     * */
    private StudentTeachingSituation addNew(StudentTeachingSituation situation,
                                            FactorClasses classes,  //根据
                                            FactorCourse course,
                                            Integer score) {
        // 获得当前的课程列表
        List<FactorCourse> factorCourseList = situation.getFactorCourseList();

        // 遍历课程列表
        int i = 0, j = 0;
        boolean flag1 = false;
        boolean flag2 = false;
        for (; i < factorCourseList.size(); i++) {
            // 取出当前课程
            FactorCourse tempCourse = factorCourseList.get(i);
            // 判断是否是已经存在的课程
            if (tempCourse.getId().equals(course.getId())) {
                // 是否是已经存在课程
                flag1 = true;
                List<FactorClasses> classesList = tempCourse.getClassesList();

                // 遍历班级列表
                for (; j < classesList.size(); j++) {
                    // 取出当前一个班级
                    FactorClasses tempClasses = classesList.get(j);

                    if (tempClasses.getId().equals(classes.getId())) {
                        flag2 = true;
                        // 已经存在的班级
                        tempClasses.addScore(score);
                        // 无需继续查找
                        break;
                    }
                }

                // 新班级
                if (!flag2) {
                    classesList.add(classes.addScore(score));
                    flag2 = false;
                }

            }
        }
        // 为假表明是列表中不存在的一门课程
        if (!flag1) {
            factorCourseList.add(course.add(classes));
            flag1 = false;
        }

        return situation;
    }

}
