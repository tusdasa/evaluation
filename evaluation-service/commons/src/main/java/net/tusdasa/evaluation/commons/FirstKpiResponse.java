package net.tusdasa.evaluation.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.FirstKpi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@With
public class FirstKpiResponse implements Serializable {

    private Integer firstKpiId;

    private String firsKpiContent;

    private Integer academicYearId;

    private List<SecondKpiResponse> table;

    public FirstKpiResponse() {
        this.table = new ArrayList<>();
    }

    public FirstKpiResponse(int size) {
        this.table = new ArrayList<>(size);
    }

    public FirstKpiResponse addAcademic(AcademicYear academicYear) {
        this.setAcademicYearId(academicYear.getAcademicYearId());
        return this;
    }

    public FirstKpiResponse addFirstKpi(FirstKpi firstKpi) {
        this.setFirstKpiId(firstKpi.getFirstKpiId());
        this.setFirsKpiContent(firstKpi.getFirsKpiContent());
        return this;
    }

    public void addA(AcademicYear academicYear) {
        this.setAcademicYearId(academicYear.getAcademicYearId());
    }

    public void addF(FirstKpi firstKpi) {
        this.setFirstKpiId(firstKpi.getFirstKpiId());
        this.setFirsKpiContent(firstKpi.getFirsKpiContent());
    }

    public void addT(SecondKpiResponse secondKpiResponse) {
        if (this.table == null) this.table = new ArrayList<>();
        table.add(secondKpiResponse);
    }

}
