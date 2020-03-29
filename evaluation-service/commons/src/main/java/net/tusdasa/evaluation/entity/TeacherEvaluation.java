package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.tusdasa.evaluation.utils.UUIDUtils;

import java.io.Serializable;
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

public class TeacherEvaluation implements Serializable {

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
    private Integer evaluatorId;

    /**
     * 总分
     */
    private Integer total;

    /**
     * 三级指标得分
     */
    private List<KpiScore> secondKpiList;

}
