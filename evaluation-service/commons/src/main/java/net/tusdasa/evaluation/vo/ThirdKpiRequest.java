package net.tusdasa.evaluation.vo;

import lombok.*;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.utils.CheckUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder
public class ThirdKpiRequest implements CommonRequest<ThirdKpi> {

    private Integer thirdKpiId;

    private Integer secondKpiId;

    private String thirdKpiContent;

    private Integer calculationRuleId;

    @Override
    public ThirdKpi build() {
        return ThirdKpi.builder().build().withCalculationRuleId(this.getCalculationRuleId())
                .withThirdKpiContent(this.getThirdKpiContent())
                .withSecondKpiId(this.getSecondKpiId())
                .withThirdKpiId(this.getThirdKpiId());
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(this.getThirdKpiId()) &&
                CheckUtils.isIntegerNumber(this.getSecondKpiId())
                && CheckUtils.isString(this.getThirdKpiContent())
                && CheckUtils.isIntegerNumber(this.getCalculationRuleId());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isIntegerNumber(this.getSecondKpiId())
                && CheckUtils.isString(this.getThirdKpiContent());
    }
}
