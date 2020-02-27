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
 * @Date: 2020-02-27 3:13 PM
 */

public class FactorClasses {

    // 班级ID
    private Integer id;

    // 班级名称
    private String classesName;

    // 班级学院
    private String departmentName;

    // 班级年级
    private String gradeName;

    // 班级专业
    private String majorName;

    // 班级总分
    private Integer totalScore;

    // 平均分
    private Integer averageScore = totalScore / this.totalList.size();

    // 总分列表
    private List<Integer> totalList;

    // 二级指标得分详情
    //List<FactorSecondKPI> secondKPIList;


    public FactorClasses addScore(Integer score) {
        if (this.totalList == null) {
            totalList = new ArrayList<>();
        }
        totalList.add(score);
        return this;
    }

    public FactorClasses(Student student) {
        this.id = student.getStudentClass().getClassId();
        this.classesName = student.getStudentClass().getClassName();
        this.departmentName = student.getStudentClass().getDepartment().getDepartmentName();
        this.majorName = student.getStudentClass().getMajor().getMajorName();
    }


}
