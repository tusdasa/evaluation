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
 * 学生评价类
 * @author tusdasa
 * @version 1.0
 * */

public class StudentEvaluation implements Serializable {

    /**
     * MongoDB id UUID
     *
     * @see UUIDUtils#UUID()
     */
    private String id;

    /**
     * 评教学生Id
     */
    private Long studentId;

    /**
     * 当前学期Id
     */
    private Integer termId;

    /**
     * 当前课程Id
     */
    private Integer courseId;

    /**
     * 总分 由分析微服务计算
     */
    private Integer total;

    /**
     * 三级指标得分
     *
     * @see KpiScore
     */
    private List<KpiScore> kpiScoreList;

}
