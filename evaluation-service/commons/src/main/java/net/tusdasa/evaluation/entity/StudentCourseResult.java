package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentCourseResult implements Serializable {

    private String id;

    private Long studentId;

    private Integer termId;

    private Integer courseId;

    private Integer total;

    private List<KpiScore> scores;

}
