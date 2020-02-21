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

/**
 * 一级指标类
 * @author tusdasa
 * @version 1.0
 * */

public class FirstKpi implements Serializable, Comparable<FirstKpiRequest> {

    /**
     * 一级指标Id
     */
    private Integer firstKpiId;

    /**
     * 一级指标内容
     */
    private String firsKpiContent;

    /**
     * 一级指标所在学年
     */
    private Integer academicYearId;

    private static final long serialVersionUID = 10051564L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see FirstKpiRequest
     */
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