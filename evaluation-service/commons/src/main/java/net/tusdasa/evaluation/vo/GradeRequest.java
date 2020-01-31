package net.tusdasa.evaluation.vo;

import lombok.*;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.utils.CheckUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder
public class GradeRequest implements CommonRequest<Grade> {

    private Integer gradeId;

    private String gradeName;


    public Grade build() {
        return Grade.builder().build().withGradeName(this.getGradeName());
    }

    @Override
    public boolean isUpdateRequest() {
        return CheckUtils.isIntegerNumber(gradeId) && CheckUtils.isString(this.getGradeName());
    }

    @Override
    public boolean isCreateRequest() {
        return CheckUtils.isString(this.getGradeName());
    }

}
