package net.tusdasa.evaluation.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@EqualsAndHashCode

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 4:13 PM
 */

public class TeacherTeachingSituation implements Serializable {

    private Integer id;

    // 评价人
    private String evaluator;

    // 被评价人
    private String teacherName;

    // 学期
    private String termName;

    // 指标成绩
    List<FactorSecondKPI> secondKPIList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public List<FactorSecondKPI> getSecondKPIList() {
        return secondKPIList;
    }

    public void setSecondKPIList(List<FactorSecondKPI> secondKPIList) {
        this.secondKPIList = secondKPIList;
    }
}
