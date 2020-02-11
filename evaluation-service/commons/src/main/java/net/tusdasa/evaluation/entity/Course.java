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
public class Course implements Serializable {

    private Long courseId;

    private String courseName;

    private Integer teacherWorkId;

    private Integer termId;

    private List<StudentClass> studentClassList;

    private static final long serialVersionUID = 1001L;

}