package net.tusdasa.evaluation.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 1:55 PM
 */

public class StudentSituation implements Serializable {

    // workId
    private Integer id;

    // 教师名称
    private String teacherName;

    // 教师的部门
    private String departmentName;

    // 教师的部门id
    private Integer departmentId;

    // 教师的职称
    private String professionalTitle;

    // 所有班级评价成绩之和
    private Double totalScore = 0.0;

    // 该教师开设的课程不同班级的给的评价信息
    private List<FactorCourse> factorCourseList = new ArrayList<>(10);

    public StudentSituation() {
    }

    public StudentSituation(Teacher teacher) {
        this.id = teacher.getWorkId();
        this.teacherName = teacher.getTeacherName();
        this.departmentName = teacher.getDepartment().getDepartmentName();
        this.professionalTitle = teacher.getProfessional().getProfessionalTitle();
        this.departmentId = teacher.getDepartment().getDepartmentId();
    }

    public StudentSituation add(FactorCourse course) {
        this.factorCourseList.add(course);
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
        double temp = 0.0;
        for (FactorCourse factorCourse : factorCourseList) {
            temp = temp + factorCourse.getTotalScore();
        }
        this.totalScore = temp / getFactorCourseList().size();
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
