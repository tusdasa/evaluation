package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.FirstKpiRequest;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString
public class FirstKpi implements Serializable, Comparable<FirstKpiRequest> {

    private Integer firstKpiId;

    private String firsKpiContent;

    private Integer academicYearId;

    private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(FirstKpiRequest request) {

        if (request.getAcademicYearId().intValue() == this.getAcademicYearId().intValue()
                && request.getFirsKpiContent().equals(this.getFirsKpiContent())
                && request.getFirstKpiId().intValue() == this.getFirstKpiId().intValue()
        ) {
            return 0;
        }
        return -1;

    }
}