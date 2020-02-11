package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentCourseResult implements Serializable {

    private Integer id;

    private Integer workId;

    private Long studentId;

    private Integer termId;

    private Integer courseId;

    private List<Map<Integer, Integer>> result;

}
