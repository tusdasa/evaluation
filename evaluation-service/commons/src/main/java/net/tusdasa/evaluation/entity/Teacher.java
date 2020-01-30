package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.util.DigestUtils;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
@EqualsAndHashCode
@ToString
public class Teacher implements Serializable, Comparable<TeacherRequest> {

    private Integer workId;

    private String teacherSecret;

    private Integer roleId;

    private Department department;

    private String teacherName;

    private Professional professional;

    private TeacherState state;

    private static final long serialVersionUID = 1003L;

    @Override
    public int compareTo(TeacherRequest request) {

        if (request.getWorkId().intValue() == this.getWorkId().intValue()
            && DigestUtils.md5DigestAsHex(request.getTeacherSecret().getBytes()).equals(request.getTeacherSecret())
                && request.getRoleId().intValue() == this.roleId.intValue()
                && request.getTeacherName().equals(this.getTeacherName())
                && request.getDepartmentId().intValue() == this.getDepartment().getDepartmentId().intValue()
                && request.getProfessionalId().intValue() == this.getProfessional().getProfessionalId().intValue()
                && request.getStateId().intValue() == this.getState().getStateId().intValue()
        ){
            return 0;
        }else {
            return -1;
        }

    }
}