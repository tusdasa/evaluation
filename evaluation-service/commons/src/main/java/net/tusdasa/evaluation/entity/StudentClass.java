package net.tusdasa.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.tusdasa.evaluation.vo.StudentClassRequest;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * @Description 学生班级类
 * @author tusdasa
 * @version 1.0
 * */

public class StudentClass implements Serializable, Comparable<StudentClassRequest> {

    /**
     * 班级Id
     */
    private Integer classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级所属学院
     */
    private Department department;

    /**
     * 学生所在年级
     */
    private Grade grade;

    /**
     * 学生年级
     */
    private Major major;

    private static final long serialVersionUID = 1002L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see StudentClassRequest
     */
    @Override
    public int compareTo(StudentClassRequest request) {
        if (request.getMajorId().intValue() == this.getClassId()
                && request.getClassName().equals(this.getClassName())
                && request.getDepartmentId().intValue() == this.getDepartment().getDepartmentId().intValue()
                && request.getGradeId().intValue() == this.getMajor().getMajorId().intValue()
                && request.getMajorId().intValue() == this.getMajor().getMajorId().intValue()
        ) {
            return 0;
        }
        return -1;
    }
}