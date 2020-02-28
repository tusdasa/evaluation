package net.tusdasa.evaluation.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@EqualsAndHashCode

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 4:13 PM
 */

@Document(indexName = "teacher")
public class TeacherSituation implements Serializable {

    // 教师Id
    private Integer id;

    // 评价人
    private String teacherName;

    // 学期
    private String termName;

    // 成绩
    private Integer totalScore = 0;

    // 分数
    private List<Map<String, Integer>> scoreList;


    public TeacherSituation() {
    }

    public TeacherSituation add(String evaluator, Integer score) {
        if (this.scoreList == null) {
            this.scoreList = new ArrayList<>();
        }
        HashMap<String, Integer> evaluation = new HashMap<>();
        evaluation.put(evaluator, score);
        this.totalScore = this.totalScore + score;
        this.scoreList.add(evaluation);
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

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public List<Map<String, Integer>> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Map<String, Integer>> scoreList) {
        this.scoreList = scoreList;
    }
}
