package net.tusdasa.evaluation.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 1:55 PM
 */

@Document(indexName = "student")
public class StudentTeachingSituation implements Serializable {

    // workId
    private Integer id;

    // 教师名称
    private String teacherName;

    // 教师的部门
    private String departmentName;

    // 教师的职称
    private String professionalTitle;

    // 所有班级评价成绩之和
    private Double totalScore = 0.0;

    // 该教师开设的课程不同班级的给的评价信息
    private List<FactorCourse> factorCourseList;

    public StudentTeachingSituation(Teacher teacher) {
        this.id = teacher.getWorkId();
        this.teacherName = teacher.getTeacherName();
        this.departmentName = teacher.getDepartment().getDepartmentName();
        this.professionalTitle = teacher.getProfessional().getProfessionalTitle();
    }

    public StudentTeachingSituation add(FactorCourse course) {
        if (this.factorCourseList == null) {
            this.factorCourseList = new ArrayList<>(10);
        }
        this.factorCourseList.add(course);
        this.totalScore = this.totalScore + course.getTotalScore();
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public List<FactorCourse> getFactorCourseList() {
        return factorCourseList;
    }

    public void setFactorCourseList(List<FactorCourse> factorCourseList) {
        this.factorCourseList = factorCourseList;
    }

}
