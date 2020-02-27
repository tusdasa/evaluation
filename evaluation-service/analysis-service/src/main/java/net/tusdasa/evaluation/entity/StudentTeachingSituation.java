package net.tusdasa.evaluation.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

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
 * @Date: 2020-02-27 1:55 PM
 */

@Document(indexName = "student")
public class StudentTeachingSituation {

    // workId
    private Integer id;

    // 教师名称
    private String teacherName;

    // 教师的部门
    private String departmentName;

    // 教师的职称
    private String professionalTitle;

    // 所有班级总成绩
    private Integer totalScore;

    // 该教师开设的课程不同班级的给的评价信息
    private List<FactorCourse> factorCourseList;

    public StudentTeachingSituation(Teacher teacher) {
        this.id = teacher.getWorkId();
        this.teacherName = teacher.getTeacherName();
        this.departmentName = teacher.getDepartment().getDepartmentName();
        this.professionalTitle = teacher.getProfessional().getProfessionalTitle();
    }

    public StudentTeachingSituation add(FactorCourse course) {
        if (this.factorCourseList == null) {
            this.factorCourseList = new ArrayList<>(10);
        }
        this.factorCourseList.add(course);
        return this;
    }

}
