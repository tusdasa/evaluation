package net.tusdasa.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * 学年类
 * @author tusdasa
 * @version 1.0
 * */

public class AcademicYear implements Serializable, Comparable<AcademicYearRequest> {

    /**
     * 学年ID
     */
    private Integer academicYearId;

    /**
     * 学年名称
     */
    private String academicYearName;

    /**
     * 学年开始时间
     */
    private Term startTerm;

    /**
     * 学年结束时间
     */
    private Term endTerm;

    private static final long serialVersionUID = 1001L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see AcademicYearRequest
     */
    @Override
    public int compareTo(AcademicYearRequest request) {
        if (request.getAcademicYearId().intValue() == this.getAcademicYearId().intValue()
                && request.getAcademicYearName().equals(this.getAcademicYearName())
                && request.getStartTerm().intValue() == this.getStartTerm().getTermId().intValue()
                && request.getEndTerm().intValue() == this.getEndTerm().getTermId().intValue()
        ) {
            return 0;
        }
        return -1;
    }
}