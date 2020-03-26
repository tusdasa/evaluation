package net.tusdasa.evaluation.entity;

/**
 * @Author: tusdasa
 * @Date: 2020-03-25 8:40 PM
 */

public class TermResult {

    private Integer termId;

    private String termName;

    // 学生给出的成绩
    private double studentResult = 0.0D;

    // 学生众数最大值
    private double studentModeMax = 0.0D;

    // 学生众数最小值
    private double studentModeMin = 0.0D;

    // 学生成绩标准差
    private double studentStandardDeviation = 0.0D;

    // 参与评教的学生人数
    private int studentCount = 0;

    // 参与评教的班级数
    private int classCount = 0;

    // 教学督导给出的成绩
    private double teacherResult = 0.0D;

    // 总分
    private double total = 0.0D;

    public TermResult() {
    }

    public TermResult(Term term) {
        this.termId = term.getTermId();
        this.termName = term.getTermName();
    }

    /**
     * result[0] 学生成绩
     * result[1] 标准差
     * result[2] 最小众数
     * result[3] 最大众数
     * result[4] 总人数
     * result[5] 班级数
     */
    public void setStudentScore(double[] studentScore) {
        this.studentResult = studentScore[0];
        this.studentStandardDeviation = studentScore[1];
        this.studentModeMin = studentScore[2];
        this.studentModeMax = studentScore[3];
        this.classCount = (int) studentScore[4];
        this.studentCount = (int) studentScore[5];
    }

    public void setTeacherScore(double teacherScore) {
        this.teacherResult = teacherScore;
        this.total = this.studentResult * 10 + this.teacherResult;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public double getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(double studentResult) {
        this.studentResult = studentResult;
    }

    public double getStudentModeMax() {
        return studentModeMax;
    }

    public void setStudentModeMax(double studentModeMax) {
        this.studentModeMax = studentModeMax;
    }

    public double getStudentModeMin() {
        return studentModeMin;
    }

    public void setStudentModeMin(double studentModeMin) {
        this.studentModeMin = studentModeMin;
    }

    public double getStudentStandardDeviation() {
        return studentStandardDeviation;
    }

    public void setStudentStandardDeviation(double studentStandardDeviation) {
        this.studentStandardDeviation = studentStandardDeviation;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    public double getTeacherResult() {
        return teacherResult;
    }

    public void setTeacherResult(double teacherResult) {
        this.teacherResult = teacherResult;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
