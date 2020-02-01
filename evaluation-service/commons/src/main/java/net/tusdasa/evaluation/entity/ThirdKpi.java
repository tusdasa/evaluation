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
public class ThirdKpi implements Serializable, Comparable<ThirdKpiRequest> {
    private Integer thirdKpiId;

    private Integer secondKpiId;

    private String thirdKpiContent;

    private Integer calculationRuleId;

    private static final long serialVersionUID = 1L;

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