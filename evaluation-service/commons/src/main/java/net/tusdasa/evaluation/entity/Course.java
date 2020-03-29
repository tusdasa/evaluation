package net.tusdasa.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * 课程类
 * @author tusdasa
 * @version 1.0
 * */

public class Course implements Serializable {

    /**
     * 课程Id
     */
    private Long courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 授课教师ID
     */
    private Integer teacherWorkId;

    /**
     * 开课学期ID
     */
    private Integer termId;

    /**
     * 开设该课程的边界列表
     */
    private List<StudentClass> studentClassList;

    private static final long serialVersionUID = 1001L;

}