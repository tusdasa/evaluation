package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.SecondKpiRequest;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString
public class SecondKpi implements Serializable, Comparable<SecondKpiRequest> {

    private Integer secondKpiId;

    private Integer firstKpiId;

    private String secondKpiContent;

    private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(SecondKpiRequest request) {
        if (this.getFirstKpiId().intValue() == request.getFirstKpiId().intValue()
                && this.getSecondKpiId().intValue() == request.getSecondKpiId().intValue()
                && this.getSecondKpiContent().equals(request.getSecondKpiContent())
        ) {
            return 0;
        }
        return -1;
    }
}