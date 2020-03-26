package net.tusdasa.evaluation.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 8:34 PM
 */

public class AcademicYearResult implements Serializable {

    // workId
    private Integer id;

    // 教师名称
    private String teacherName;

    // 学年名
    private String academicYearName;

    // 教师的部门
    private String departmentName;

    // 教师的职称
    private String professionalTitle;

    private TermResult firstTerm;

    private TermResult secondTerm;

    // 学年学生分数
    private Double studentScore;

    // 学年
    private Double teacherScore;

    // 总分
    private Double total = 0.0D;

    public AcademicYearResult() {
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

    public Double getTotal() {
        BigDecimal bigDecimal = new BigDecimal(this.total);
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAcademicYearName() {
        return academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public TermResult getFirstTerm() {
        return firstTerm;
    }

    public void setFirstTerm(TermResult firstTerm) {
        this.firstTerm = firstTerm;
    }

    public TermResult getSecondTerm() {
        return secondTerm;
    }

    public void setSecondTerm(TermResult secondTerm) {
        this.secondTerm = secondTerm;
    }

    public Double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
    }

    public Double getTeacherScore() {
        return teacherScore;
    }

    public void setTeacherScore(Double teacherScore) {
        this.teacherScore = teacherScore;
    }
}
