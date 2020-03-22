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

    // 学期ID
    private Integer termId;

    // 开课的学期
    private String termName;

    // 该门课程班级总成绩/班级数量
    private Double totalScore = 0.0;

    // 参加课的班级
    List<FactorClasses> classesList = new ArrayList<>(20);

    public FactorCourse() {
    }

    public FactorCourse add(FactorClasses classes) {
        this.classesList.add(classes);
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

        double temp = 0.0;
        for (FactorClasses factorClasses : classesList) {
            temp = temp + factorClasses.getAverageScore();
        }
        this.totalScore = temp / this.classesList.size();
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

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }
}
