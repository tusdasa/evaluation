package net.tusdasa.evaluation.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString
public class ThirdKpi implements Serializable {
    private Integer thirdKpiId;

    private Integer secondKpiId;

    private String thirdKpiContent;

    private Integer calculationRuleId;

    private static final long serialVersionUID = 1L;

}