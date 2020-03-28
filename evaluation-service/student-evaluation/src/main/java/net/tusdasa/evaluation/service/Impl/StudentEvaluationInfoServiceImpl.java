package net.tusdasa.evaluation.service.Impl;

import net.tusdasa.evaluation.dao.StudentEvaluationDao;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.service.StudentEvaluationInfoService;
import net.tusdasa.evaluation.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentEvaluationInfoServiceImpl implements StudentEvaluationInfoService {

    Logger logger = LoggerFactory.getLogger(StudentEvaluationInfoServiceImpl.class);

    private StudentEvaluationDao studentEvaluationDao;

    public StudentEvaluationInfoServiceImpl(StudentEvaluationDao studentEvaluationDao) {
        this.studentEvaluationDao = studentEvaluationDao;
    }

    /**
     * 获取当前学期
     * AcademicYear academicYear = this.currentAcademicYear();
     * 获取当前学期和当前角色可以操作的指标
     * CommonResponse<FirstKpi> firstKpiList = firstKpiClient.findByIds(academicYear.getAcademicYearId(), new IdsRequest().addFirstIds(this.STUDENT_FIRST_KPI));
     * 从CommonResponse<FirstKpi>里遍历当前的firstKpiId 封装为IdsRequest 传入第二指标微服务
     * IdsRequest firstKpiIds = this.getFirstKpiIdsRequest(firstKpiList.getTable());
     * 第二指标微服务找出第一指标中指定中的二级指标
     * CommonResponse<SecondKpi> secondKpiList = this.secondKpiClient.findAllByFirstKpi(firstKpiIds);
     * 从CommonResponse<SecondKpi>中遍历所有的secondKpiId 封装为IdsRequest 传入第三指标微服务
     * IdsRequest secondKpiIds = this.getSecondKpiIdsRequest(secondKpiList.getTable());
     * 第三指标微服务找出第二指标中指定中的三级指标
     * CommonResponse<ThirdKpi> thirdKpiList = thirdKpiClient.findThirdBySecondKpiIds(secondKpiIds);
     **/
/*
    public CommonResponse<ThirdKpi> findAll(Integer role) {
        CommonResponse<Right> rightCommonResponse = rightClient.findRightById(role);
        if (rightCommonResponse.success()) {
            Right right = rightCommonResponse.getData();
            if (right != null && right.checkRight()) {
                CommonResponse<ThirdKpi> thirdKpi = thirdKpiClient.findAllBySecondKpiIds(new IdsRequest().addFirstIds(right.getSecondKpiId()).addSecondIds(right.getThirdKpiId()));
                if (thirdKpi.success()) {
                    return thirdKpi;
                } else {
                    return new CommonResponse<ThirdKpi>().error(thirdKpi.getMessage());
                }
            } else {
                return new CommonResponse<ThirdKpi>().error("您没有任何权限");
            }
        } else {
            return new CommonResponse<ThirdKpi>().error(rightCommonResponse.getMessage());
        }

    }
 */
/*
    public CommonResponse<Course> currentCourse(Long studentId) {
        CommonResponse<Student> studentCommonResponse = this.getStudent(studentId);
        if (studentCommonResponse.success()) {
            CommonResponse<Term> termCommonResponse = this.getTerm();
            if (termCommonResponse.success()) {
                CommonResponse<Course> courseCommonResponse = courseClient.findCourseByClassIdAndTermId(studentCommonResponse.getData().getStudentClass().getClassId(), termCommonResponse.getData().getTermId());
                List<Course> courseList = this.checkAllCourse(courseCommonResponse.getTable(), this.getStudentCourseResult(studentId, termCommonResponse.getData().getTermId()));
                if (courseList != null) {
                    return new CommonResponse<Course>().ok().table(courseList);
                } else {
                    return new CommonResponse<Course>().error("您已经完成了所有的评价");
                }
            } else {
                logger.info("未找到学期");
                return new CommonResponse<Course>().error(termCommonResponse.getMessage());
            }
        } else {
            logger.info("未找到学生信息");
            return new CommonResponse<Course>().error(studentCommonResponse.getMessage());
        }
    }
*/
    /**
     * 获取学生信息
     */
    /*
    private CommonResponse<Student> getStudent(Long studentId) {
        return this.rightClient.checkStudent(String.valueOf(studentId));
    }
     */

    /**
     * 获取当前学期信息
     */
    /*
    private CommonResponse<Term> getTerm() {
        return this.academicYearClient.currentTerm();
    }
    */

    /**
     * 获取学生已评价课程
     */

    public List<Course> checkAllCourse(List<Course> courseList, List<StudentEvaluation> studentEvaluationList) {
        if (courseList != null && !courseList.isEmpty()) {
            if (studentEvaluationList != null && !studentEvaluationList.isEmpty()) {
                if (studentEvaluationList.size() < courseList.size()) {
                    List<Course> newCourseList = new ArrayList<>(courseList.size() - studentEvaluationList.size());
                    int i = 0;
                    int j = 0;
                    boolean flag = false;
                    for (; i < courseList.size(); i++) {
                        Course course = courseList.get(i);
                        logger.info(course.getCourseName());
                        for (; j < studentEvaluationList.size(); j++) {
                            StudentEvaluation studentEvaluation = studentEvaluationList.get(j);
                            if (studentEvaluation.getCourseId().longValue() == course.getCourseId().longValue()) {
                                // 已经被评价了不再进入课程列表
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            newCourseList.add(course);
                        }
                        // 置为false
                        flag = false;
                        // 置为0 开始后续过滤
                        j = 0;
                    }
                    return newCourseList;
                } else {
                    return Collections.emptyList();
                }
            } else {
                // 没有已经评价了的课程
                return courseList;
            }

        } else {
            // 没有课
            return Collections.emptyList();
        }

    }

    @Override
    public List<StudentEvaluation> findAllByStudentIdAndAndTermId(Long studentId, Integer termId) {
        return studentEvaluationDao.findAllByStudentIdAndAndTermId(studentId, termId);
    }

    @Override
    public StudentEvaluation addStudentCourseResult(StudentEvaluation studentEvaluation, Student student) {
        String uuid = UUIDUtils.UUID();
        studentEvaluation.setId(uuid);
        studentEvaluation.setStudentId(student.getStudentId());
        studentEvaluation.setClassId(student.getStudentClass().getClassId());
        studentEvaluation.setStudentName(student.getStudentName());
        return studentEvaluationDao.insert(studentEvaluation);
    }


}
