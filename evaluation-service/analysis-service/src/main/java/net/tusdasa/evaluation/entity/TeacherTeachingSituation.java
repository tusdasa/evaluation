package net.tusdasa.evaluation.entity;

import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 4:13 PM
 */

public class TeacherTeachingSituation {

    private Integer id;

    // 评价人
    private String evaluator;

    // 被评价人
    private String teacherName;

    // 学期
    private String termName;

    // 指标成绩
    List<FactorSecondKPI> secondKPIList;

}
