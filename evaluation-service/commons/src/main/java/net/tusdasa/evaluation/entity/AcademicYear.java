package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.AcademicYearRequest;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder
public class AcademicYear implements Serializable, Comparable<AcademicYearRequest> {

    private Integer academicYearId;

    private String academicYearName;

    private Term startTerm;

    private Term endTerm;

    private static final long serialVersionUID = 1001L;

    @Override
    public int compareTo(AcademicYearRequest request) {
        if (request.getAcademicYearId().intValue() == this.getAcademicYearId().intValue()
                && request.getAcademicYearName().equals(this.getAcademicYearName())
                && request.getStartTerm().intValue() == this.getStartTerm().getTermId().intValue()
                && request.getEndTerm().intValue() == this.getEndTerm().getTermId().intValue()
        ){
            return 0;
        }else {
            return -1;
        }


    }
}