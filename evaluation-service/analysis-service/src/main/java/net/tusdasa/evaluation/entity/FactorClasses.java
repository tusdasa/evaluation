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
 * @Date: 2020-02-27 3:13 PM
 */

public class FactorClasses implements Serializable {

    // 班级ID
    private Integer id;

    // 班级名称
    private String classesName;

    // 班级学院
    private String departmentName;

    // 班级年级
    private String gradeName;

    // 班级专业
    private String majorName;

    // 班级总分
    private Integer totalScore = 0;

    // 平均分
    private Double averageScore = 0.0;

    // 总分列表
    private List<Integer> totalList;

    // 二级指标得分详情
    //List<FactorSecondKPI> secondKPIList;


    public FactorClasses addScore(Integer score) {
        if (this.totalList == null) {
            totalList = new ArrayList<>(50);
        }
        totalList.add(score);
        this.totalScore += this.totalScore + score;
        this.totalScore = this.totalScore / this.totalList.size();
        return this;
    }

    public FactorClasses(Student student) {
        this.id = student.getStudentClass().getClassId();
        this.classesName = student.getStudentClass().getClassName();
        this.departmentName = student.getStudentClass().getDepartment().getDepartmentName();
        this.majorName = student.getStudentClass().getMajor().getMajorName();
        this.gradeName = student.getStudentClass().getGrade().getGradeName();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Integer> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<Integer> totalList) {
        this.totalList = totalList;
    }
}
