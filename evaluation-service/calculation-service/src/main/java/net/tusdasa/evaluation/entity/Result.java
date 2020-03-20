package net.tusdasa.evaluation.entity;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 8:34 PM
 */

public class Result {

    // workId
    private Integer id;

    // 教师名称
    private String teacherName;

    // 教师的部门
    private String departmentName;

    // 教师的职称
    private String professionalTitle;

    private Double studentResult = 0.0D;

    private Double teacherResult = 0.0D;

    public Result() {
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
        return studentResult;
    }

    public void setStudentResult(Double studentResult) {
        this.studentResult = studentResult;
    }

    public Double getTeacherResult() {
        return teacherResult;
    }

    public void setTeacherResult(Double teacherResult) {
        this.teacherResult = teacherResult;
    }
}
