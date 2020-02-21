package net.tusdasa.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * @Description 学生评价类
 * @author tusdasa
 * @version 1.0
 * */

public class Teacher implements Serializable, Comparable<TeacherRequest> {

    /**
     * 教师ID 教师工号
     */
    private Integer workId;

    /**
     * 教师密码
     */
    private String teacherSecret;

    /**
     * 教师角色Id 确定权限
     *
     * @see Role
     */
    private Integer roleId;

    /**
     * 教师所在部门
     *
     * @see Department
     */
    private Department department;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 教师职称
     *
     * @see Professional
     */
    private Professional professional;

    /**
     * 教师状态
     *
     * @see TeacherState
     */
    private TeacherState state;

    private static final long serialVersionUID = 1003L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see TeacherRequest
     */
    @Override
    public int compareTo(TeacherRequest request) {

        if (request.getWorkId().intValue() == this.getWorkId().intValue()
                && DigestUtils.md5DigestAsHex(request.getTeacherSecret().getBytes()).equals(request.getTeacherSecret())
                && request.getRoleId().intValue() == this.roleId.intValue()
                && request.getTeacherName().equals(this.getTeacherName())
                && request.getDepartmentId().intValue() == this.getDepartment().getDepartmentId().intValue()
                && request.getProfessionalId().intValue() == this.getProfessional().getProfessionalId().intValue()
                && request.getStateId().intValue() == this.getState().getStateId().intValue()
        ) {
            return 0;
        } else {
            return -1;
        }

    }
}