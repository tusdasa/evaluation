package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.StudentRequest;
import org.springframework.util.DigestUtils;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With

/**
 * 学生类
 * @author tusdasa
 * @version 1.0
 * */

public class Student implements Serializable, Comparable<StudentRequest> {

    /**
     * 学生学号
     */
    private Long studentId;

    /**
     * 学生密码
     */
    private String studentSecret;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生班级
     *
     * @see StudentClass
     */
    private StudentClass studentClass;

    private static final long serialVersionUID = 1001L;

    /**
     * 返回一个无效的学生类
     *
     * @return Student
     */
    public static Student invalid() {
        return Student.builder().build().withStudentId(-1L);
    }

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see StudentRequest
     */
    @Override
    public int compareTo(StudentRequest request) {
        if (request.getStudentId().longValue() == this.getStudentId()
                && this.getStudentSecret().equals(DigestUtils.md5DigestAsHex(this.getStudentSecret().getBytes()))
                && request.getStudentName().equals(this.getStudentName())
                && request.getClassId().intValue() == this.getStudentClass().getClassId().intValue()

        ) {
            return 0;
        }
        return -1;
    }
}