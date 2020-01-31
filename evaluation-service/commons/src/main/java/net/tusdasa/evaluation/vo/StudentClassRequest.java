package net.tusdasa.evaluation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.tusdasa.evaluation.entity.Department;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.entity.Major;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.utils.CheckUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentClassRequest implements CommonRequest<StudentClass> {

    private Integer classId;

    private String className;

    private Integer departmentId;

    private Integer majorId;

    private Integer gradeId;

    public StudentClass build() {
        Major major = Major.builder().build().withMajorId(this.getMajorId());
        Department department = Department.builder().build().withDepartmentId(this.getDepartmentId());
        Grade grade = Grade.builder().build().withGradeId(this.getGradeId());
        return StudentClass.builder().build().withMajor(major).withDepartment(department).withGrade(grade).withClassId(this.getClassId()).withClassName(this.getClassName());
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(this.getClassId())
                && CheckUtils.isIntegerNumber(this.getDepartmentId())
                && CheckUtils.isIntegerNumber(this.getMajorId())
                && CheckUtils.isIntegerNumber(this.getGradeId())
                && CheckUtils.isString(this.getClassName());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isIntegerNumber(this.getDepartmentId())
                && CheckUtils.isIntegerNumber(this.getMajorId())
                && CheckUtils.isIntegerNumber(this.getGradeId())
                && CheckUtils.isString(this.getClassName());
    }


}
