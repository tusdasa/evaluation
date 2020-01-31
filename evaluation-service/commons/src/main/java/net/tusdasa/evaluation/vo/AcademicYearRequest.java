package net.tusdasa.evaluation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.utils.CheckUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class AcademicYearRequest implements CommonRequest<AcademicYear> {

    private Integer academicYearId;

    private String academicYearName;

    private Integer startTerm;

    private Integer endTerm;

    public AcademicYear build() {
        Term start = Term.builder().build().withTermId(this.getStartTerm());
        Term end = Term.builder().build().withTermId(this.getEndTerm());
        return AcademicYear.builder().build()
                .withAcademicYearName(this.getAcademicYearName())
                .withAcademicYearId(this.getAcademicYearId())
                .withEndTerm(end)
                .withStartTerm(start);
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(this.getAcademicYearId())
                && CheckUtils.isString(this.getAcademicYearName())
                && CheckUtils.isIntegerNumber(this.getStartTerm())
                && CheckUtils.isIntegerNumber(this.getEndTerm());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isString(this.getAcademicYearName())
                && CheckUtils.isIntegerNumber(this.getStartTerm())
                && CheckUtils.isIntegerNumber(this.getEndTerm());
    }

}
