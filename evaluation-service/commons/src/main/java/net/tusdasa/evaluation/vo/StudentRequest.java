package net.tusdasa.evaluation.vo;

import lombok.*;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.utils.CheckUtils;
import org.springframework.util.DigestUtils;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class StudentRequest implements CommonRequest<Student> {

    private Long studentId;

    private String studentSecret;

    private String studentName;

    private Integer classId;

    private Integer departmentId;

    private Integer gradeId;

    private Integer majorId;

    public boolean isCreateRequest() {
        return CheckUtils.isPassword(this.getStudentSecret())
                && CheckUtils.isName(this.getStudentName())
                && CheckUtils.isIntegerNumber(this.getClassId())
                && CheckUtils.isIntegerNumber(this.getDepartmentId())
                && CheckUtils.isIntegerNumber(this.getGradeId())
                && CheckUtils.isIntegerNumber(this.getMajorId());
    }

    public boolean isUpdateRequest() {
        return CheckUtils.isLongNumber(this.getStudentId())
                && CheckUtils.isName(this.getStudentName())
                && CheckUtils.isIntegerNumber(this.getClassId())
                && CheckUtils.isIntegerNumber(this.getDepartmentId())
                && CheckUtils.isIntegerNumber(this.getGradeId())
                && CheckUtils.isIntegerNumber(this.getMajorId());
    }

    public Student build() {
        Department department = Department.builder().build().withDepartmentId(this.getDepartmentId());
        Grade grade = Grade.builder().build().withGradeId(this.getGradeId());
        Major major = Major.builder().build().withMajorId(this.getMajorId());
        StudentClass studentClass = StudentClass.builder().build().withClassId(this.getClassId()).withMajor(major).withGrade(grade).withDepartment(department);
        return Student.builder().build()
                .withStudentId(this.getStudentId())
                .withStudentName(this.getStudentName())
                .withStudentSecret(DigestUtils.md5DigestAsHex(this.getStudentSecret().getBytes()))
                .withStudentClass(studentClass);
    }

}
