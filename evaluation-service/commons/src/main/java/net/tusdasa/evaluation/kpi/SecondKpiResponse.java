package net.tusdasa.evaluation.kpi;

import lombok.*;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecondKpiResponse {

    private Integer secondKpiId;

    private Integer firstKpiId;

    private String secondKpiContent;

    private List<ThirdKpi> table;

}
