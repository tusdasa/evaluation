package net.tusdasa.evaluation.vo;

import lombok.*;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.utils.CheckUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SecondKpiRequest implements CommonRequest<SecondKpi> {

    private Integer secondKpiId;

    private Integer firstKpiId;

    private String secondKpiContent;

    @Override
    public SecondKpi build() {
        return SecondKpi.builder().build().withFirstKpiId(this.getFirstKpiId()).withSecondKpiContent(this.getSecondKpiContent()).withFirstKpiId(this.getFirstKpiId());
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(this.getFirstKpiId()) && CheckUtils.isString(this.getSecondKpiContent())
                && CheckUtils.isIntegerNumber(this.getSecondKpiId());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isIntegerNumber(this.getFirstKpiId()) && CheckUtils.isString(this.getSecondKpiContent())
                ;
    }
}
