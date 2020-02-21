package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString

/**
 * 第三指标类
 * @author tusdasa
 * @version 1.0
 * */

public class ThirdKpi implements Serializable, Comparable<ThirdKpiRequest> {

    /**
     * 第三指标Id
     */
    private Integer thirdKpiId;

    /**
     * 所属第二指标Id
     */
    private Integer secondKpiId;

    /**
     * 第三指标 内容
     */
    private String thirdKpiContent;

    /**
     * 指标计算规则
     */
    private Integer calculationRuleId;

    private static final long serialVersionUID = 1L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see ThirdKpiRequest
     */
    @Override
    public int compareTo(ThirdKpiRequest request) {
        if (request.getThirdKpiId().intValue() == this.getThirdKpiId()
                && request.getSecondKpiId().intValue() == this.getSecondKpiId().intValue()
                && request.getThirdKpiContent().equals(this.getThirdKpiContent())
                && request.getCalculationRuleId().intValue() == this.getCalculationRuleId().intValue()

        ) {
            return 0;
        }
        return -1;
    }
}