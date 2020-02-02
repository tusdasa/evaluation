package net.tusdasa.evaluation.commons;

import lombok.*;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class SecondKpiResponse {

    private Integer secondKpiId;

    private Integer firstKpiId;

    private String secondKpiContent;

    private List<ThirdKpi> table;
}
