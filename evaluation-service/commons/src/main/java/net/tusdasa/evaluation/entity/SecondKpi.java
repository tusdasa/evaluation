package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.SecondKpiRequest;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString

/**
 * 第二指标类
 * @author tusdasa
 * @version 1.0
 * */

public class SecondKpi implements Serializable, Comparable<SecondKpiRequest> {

    /**
     * 第二指标ID
     */
    private Integer secondKpiId;

    /**
     * 所属第一指标ID
     *
     * @see FirstKpi
     */
    private Integer firstKpiId;

    /**
     * 第二指标内容
     */
    private String secondKpiContent;

    private static final long serialVersionUID = 141216511L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see SecondKpiRequest
     */
    @Override
    public int compareTo(SecondKpiRequest request) {
        if (this.getFirstKpiId().intValue() == request.getFirstKpiId().intValue()
                && this.getSecondKpiId().intValue() == request.getSecondKpiId().intValue()
                && this.getSecondKpiContent().equals(request.getSecondKpiContent())
        ) {
            return 0;
        }
        return -1;
    }
}