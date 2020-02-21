package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.tusdasa.evaluation.utils.UUIDUtils;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode

/**
 * 教学督导评价类
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
     * 被评价人Id
     */
    private Integer workId;

    /**
     * 当前学期Id
     */
    private Integer termId;

    /**
     * 评价人ID
     */
    private Integer teacherId;

    /**
     * 总分
     */
    private Integer total;

    /**
     * 三级指标得分
     */
    private List<SecondKpi> secondKpiList;

}
