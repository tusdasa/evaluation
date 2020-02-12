package net.tusdasa.evaluation.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder
public class CalculationRuleRequest {

    private Integer calculationRuleId;

    private Integer uniqueItem;

    private Integer maxScore;

    private Integer minScore;

    private Integer levelType;

    private Integer evidenceType;

}
