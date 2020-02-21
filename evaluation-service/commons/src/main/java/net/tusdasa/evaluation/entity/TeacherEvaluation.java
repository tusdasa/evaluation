package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.tusdasa.evaluation.utils.UUIDUtils;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode

/**
 * @Description 教学督导评价类
 * @author tusdasa
 * @version 1.0
 * */

public class TeacherEvaluation {

    /**
     * MongoDB id UUID
     *
     * @see UUIDUtils#UUID()
     */
    private String id;

    /**
     *
     */
    private Integer workId;

    /**
     * 当前学期Id
     */
    private Integer termId;

    //private Integer ;
}
