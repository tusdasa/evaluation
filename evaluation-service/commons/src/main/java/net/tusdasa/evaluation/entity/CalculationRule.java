package net.tusdasa.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * 计算规则
 * @author tusdasa
 * @version 1.0
 * */

public class CalculationRule implements Serializable {

    /**
     * Id
     */
    private Integer calculationRuleId;

    /**
     * 分数只能一次或者是按次积累,
     * <ol>
     *     <li>为1时是唯一项</li>
     *     <li>为0是可以按次得分</li>
     * </ol>
     */
    private Integer uniqueItem = 0;

    /**
     * 该项的最大分数，-1表示无限制
     */
    private Integer maxScore = -1;

    /**
     * 最低得分数，默认最低分为0分, 可以为负分(扣分)
     */
    private Integer minScore = 0;

    /**
     * 等级类型
     */
    private Integer levelType;

    /**
     * 当 <em>level_type</em> 字段不为空时，此类型才有值
     *
     * @see Level
     */
    private List<Level> levels;

    /**
     * 当 <em>evidence_type</em> 字段不为空时，此类型才有值
     *
     * @see Evidence
     */
    private Evidence evidence;

    private static final long serialVersionUID = 10006468L;
}