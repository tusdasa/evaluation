package net.tusdasa.evaluation.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With

/**
 * @Author: tusdasa
 * @Date: 2020-02-27 3:24 PM
 */

public class FactorCourse {

    // 课程ID
    private Long id;

    // 课程名称
    private String courseName;

    // 开课的学期
    private String termName;

    // 该门课程所有班级的评价成绩总分
    private Integer totalScore;

    // 参加课的班级
    List<FactorClasses> classesList;

    public FactorCourse add(FactorClasses classes) {
        if (this.classesList == null) {
            this.classesList = new ArrayList<>(20);
        }
        this.classesList.add(classes);
        return this;
    }

    public FactorCourse(Course course) {
        this.id = course.getCourseId();
        this.courseName = course.getCourseName();
    }

}
