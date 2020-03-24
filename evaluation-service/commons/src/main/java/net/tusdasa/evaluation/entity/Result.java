package net.tusdasa.evaluation.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 8:34 PM
 */

public class Result implements Serializable {

    // workId
    private Integer id;

    // 教师名称
    private String teacherName;

    private String academicYearName;

    // 教师的部门
    private String departmentName;

    // 教师的职称
    private String professionalTitle;

    private Double studentResult = 0.0D;

    private Double teacherResult = 0.0D;

    private Double studentMode = 0.0D;

    private Double studentStandardDeviation = 0.0D;

    private Double total = 0.0D;

    public Result() {
    }

    public Result(double[] studentScore, double teacherScore) {
        this.studentMode = studentScore[0];
        this.studentStandardDeviation = studentScore[1];
        this.studentResult = studentScore[2];
        this.teacherResult = teacherScore;
        this.total = this.studentResult * 10 + this.teacherResult;
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

    public Double getStudentResult() {
        BigDecimal bigDecimal = new BigDecimal(this.studentResult);
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setStudentResult(Double studentResult) {
        this.studentResult = studentResult;
    }

    public Double getTeacherResult() {
        BigDecimal bigDecimal = new BigDecimal(this.teacherResult);
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setTeacherResult(Double teacherResult) {
        this.teacherResult = teacherResult;
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

    public Double getStudentMode() {
        return studentMode;
    }

    public void setStudentMode(Double studentMode) {
        this.studentMode = studentMode;
    }

    public Double getStudentStandardDeviation() {
        BigDecimal bigDecimal = new BigDecimal(this.studentStandardDeviation);
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setStudentStandardDeviation(Double studentStandardDeviation) {
        this.studentStandardDeviation = studentStandardDeviation;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", academicYearName='" + academicYearName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", studentResult=" + studentResult +
                ", teacherResult=" + teacherResult +
                ", studentMode=" + studentMode +
                ", studentStandardDeviation=" + studentStandardDeviation +
                ", total=" + total +
                '}';
    }

}
