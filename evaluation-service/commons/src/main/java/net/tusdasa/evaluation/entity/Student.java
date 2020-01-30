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
public class Student implements Serializable, Comparable<StudentRequest> {

    private Long studentId;

    private String studentSecret;

    private String studentName;

    private StudentClass studentClass;

    private static final long serialVersionUID = 1001L;

    public static Student invalid() {
        return Student.builder().build().withStudentId(-1L);
    }


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