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
 * @Date: 2020-02-27 4:13 PM
 */

public class TeacherSituation implements Serializable {

    // 教师Id
    private Integer id;

    // 评价人
    private String teacherName;

    // 部门
    private String departmentName;

    // 成绩
    private Integer totalScore = 0;

    // 分数
    private List<FactorTeacher> factorTeacherList;

    public TeacherSituation() {
    }

    public TeacherSituation add(FactorTeacher factorTeacher) {
        if (this.factorTeacherList == null) {
            this.factorTeacherList = new ArrayList<>();
        }
        this.totalScore = this.totalScore + factorTeacher.getTotalScore();
        this.factorTeacherList.add(factorTeacher);
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

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public List<FactorTeacher> getFactorTeacherList() {
        return factorTeacherList;
    }

    public void setFactorTeacherList(List<FactorTeacher> factorTeacherList) {
        this.factorTeacherList = factorTeacherList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
