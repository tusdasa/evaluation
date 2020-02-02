package net.tusdasa.evaluation.kpi;

import lombok.*;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.util.List;

@Data
@With
public class SecondKpiResponse extends SecondKpi {
    private List<ThirdKpi> table;

    public SecondKpiResponse() {

    }
}
