package net.tusdasa.evaluation.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tusdasa
 * @Date: 2020-03-22 9:19 PM
 */

public class FactorTeacher {

    // 学期
    private String termName;

    // 学期ID
    private Integer termId;

    // 成绩
    private Integer totalScore = 0;

    // 分数
    private List<Map<String, Integer>> scoreList;

    public FactorTeacher() {
    }

    public FactorTeacher(Term term) {
        this.termId = term.getTermId();
        this.termName = term.getTermName();
    }

    public FactorTeacher add(String evaluator, Integer score) {
        if (this.scoreList == null) {
            this.scoreList = new ArrayList<>();
        }
        HashMap<String, Integer> evaluation = new HashMap<>();
        evaluation.put(evaluator, score);
        this.totalScore = this.totalScore + score;
        this.scoreList.add(evaluation);
        return this;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
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
