package net.tusdasa.evaluation.kpi;

import lombok.*;
import net.tusdasa.evaluation.entity.FirstKpi;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@With
public class FirstKpiResponse extends FirstKpi {
    List<SecondKpiResponse> table;
}
