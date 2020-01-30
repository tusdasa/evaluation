package net.tusdasa.evaluation.vo;

import lombok.*;
import net.tusdasa.evaluation.entity.Department;
import net.tusdasa.evaluation.entity.Professional;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.entity.TeacherState;
import net.tusdasa.evaluation.utils.CheckUtils;
import org.springframework.util.DigestUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class TeacherRequest implements CommonRequest<Teacher> {

    private Integer workId;

    private String teacherSecret;

    private Integer roleId;

    private Integer departmentId;

    private String teacherName;

    private Integer professionalId;

    private Integer stateId;

    public Teacher build(){
        Department department = Department.builder().build().withDepartmentId(this.getDepartmentId());
        Professional professional = Professional.builder().build().withProfessionalId(this.getProfessionalId());
        TeacherState teacherState = TeacherState.builder().build().withStateId(this.getStateId());

        return Teacher.builder().build()
                .withWorkId(this.getWorkId())
                .withTeacherSecret(DigestUtils.md5DigestAsHex(this.getTeacherSecret().getBytes()))
                .withRoleId(this.getRoleId())
                .withTeacherName(this.getTeacherName())
                .withDepartment(department)
                .withProfessional(professional)
                .withState(teacherState);
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(this.getWorkId())
                && CheckUtils.isName(this.getTeacherName())
                && CheckUtils.isPassword(this.getTeacherSecret())
                && CheckUtils.isIntegerNumber(this.getDepartmentId())
                && CheckUtils.isIntegerNumber(this.getProfessionalId())
                && CheckUtils.isIntegerNumber(this.getRoleId())
                && CheckUtils.isIntegerNumber(this.getStateId());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isName(this.getTeacherName())
                && CheckUtils.isPassword(this.getTeacherSecret())
                && CheckUtils.isIntegerNumber(this.getDepartmentId())
                && CheckUtils.isIntegerNumber(this.getProfessionalId())
                && CheckUtils.isIntegerNumber(this.getRoleId())
                && CheckUtils.isIntegerNumber(this.getStateId());
    }

}
