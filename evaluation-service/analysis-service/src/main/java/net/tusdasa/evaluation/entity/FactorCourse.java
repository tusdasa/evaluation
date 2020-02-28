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
 * @Date: 2020-02-27 3:24 PM
 */

public class FactorCourse implements Serializable {

    // 课程ID
    private Long id;

    // 课程名称
    private String courseName;

    // 开课的学期
    private String termName;

    // 该门课程所有班级的评价之平均成绩之和
    private Double totalScore = 0.0;

    // 参加课的班级
    List<FactorClasses> classesList;

    public FactorCourse() {
    }

    public FactorCourse add(FactorClasses classes) {
        if (this.classesList == null) {
            this.classesList = new ArrayList<>(20);
        }
        this.classesList.add(classes);
        this.totalScore = this.totalScore + classes.getAverageScore();
        return this;
    }

    public FactorCourse(Course course, Term term) {
        this.id = course.getCourseId();
        this.courseName = course.getCourseName();
        this.termName = term.getTermName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public List<FactorClasses> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<FactorClasses> classesList) {
        this.classesList = classesList;
    }

}
