package net.tusdasa.evaluation.vo;

import lombok.*;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.utils.CheckUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder
public class TermRequest implements CommonRequest<Term> {

    private Integer termId;

    private Date startTime;

    private Date endTime;

    private String termName;

    public Term build() {
        return Term.builder().build().withTermName(this.getTermName()).withEndTime(this.getEndTime()).withStartTime(this.getStartTime());
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(this.getTermId());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isString(this.getTermName());
    }
}
