package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TeacherEvaluation {

    // MongoDB id
    private String id;

    // 被评价者Id
    private Integer workId;

    // 当前学期Id
    private Integer termId;

    //private Integer ;
}
