package net.tusdasa.evaluation.entity;

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
public class StudentClass implements Serializable, Comparable<StudentClassRequest> {

    private Integer classId;

    private String className;

    private Department department;

    private Grade grade;

    private Major major;

    private static final long serialVersionUID = 1002L;

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