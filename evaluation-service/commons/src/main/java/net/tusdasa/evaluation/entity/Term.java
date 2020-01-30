package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.TermRequest;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder
public class Term implements Serializable,Comparable<TermRequest> {

    private Integer termId;

    private Date startTime;

    private Date endTime;

    private String termName;

    private static final long serialVersionUID = 1009L;

    @Override
    public int compareTo(TermRequest request) {
        if (request.getTermId().intValue()==this.getTermId().intValue()
                && request.getTermName().equals(this.getTermName())){
            return 0;
        }else {
            return -1;
        }
    }
}