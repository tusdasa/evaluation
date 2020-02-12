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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculationRule implements Serializable {

    private Integer calculationRuleId;

    private Integer uniqueItem;

    private Integer maxScore;

    private Integer minScore;

    private Integer levelType;

    private List<Level> levels;

    private Evidence evidence;

    private static final long serialVersionUID = 10006468L;
}