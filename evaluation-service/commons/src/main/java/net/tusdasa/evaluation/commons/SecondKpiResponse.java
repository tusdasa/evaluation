package net.tusdasa.evaluation.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@With
public class SecondKpiResponse implements Serializable {

    private Integer secondKpiId;

    private String secondKpiContent;

    private Integer firstKpiId;

    private List<ThirdKpi> table;

    public SecondKpiResponse() {
        this.table = new ArrayList<>();
    }

    public SecondKpiResponse(int size) {
        this.table = new ArrayList<>(size);
    }

    public SecondKpiResponse addSecondKpi(SecondKpi secondKpi) {
        this.setSecondKpiId(secondKpi.getSecondKpiId());
        this.setSecondKpiContent(secondKpi.getSecondKpiContent());
        this.setFirstKpiId(secondKpi.getFirstKpiId());
        return this;
    }

    public SecondKpiResponse addTable(ThirdKpi thirdKpi) {
        if (this.getTable() == null) this.table = new ArrayList<>();
        table.add(thirdKpi);
        return this;
    }

    public void addS(SecondKpi secondKpi) {
        this.setSecondKpiId(secondKpi.getSecondKpiId());
        this.setSecondKpiContent(secondKpi.getSecondKpiContent());
        this.setFirstKpiId(secondKpi.getFirstKpiId());
    }

    public void addT(ThirdKpi thirdKpi) {
        if (this.table == null) this.table = new ArrayList<>();
        this.table.add(thirdKpi);
    }
}
