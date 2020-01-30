package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.GradeRequest;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With
public class Grade implements Serializable, Comparable<GradeRequest> {

    private Integer gradeId;

    private String gradeName;

    private static final long serialVersionUID = 1004L;

    @Override
    public int compareTo(GradeRequest request) {
        if (request.getGradeId().intValue() == this.getGradeId()
                && request.getGradeName().equals(this.getGradeName())
        ) {
            return 0;
        }
        return -1;
    }
}