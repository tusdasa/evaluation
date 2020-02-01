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
        return 0;
    }
}