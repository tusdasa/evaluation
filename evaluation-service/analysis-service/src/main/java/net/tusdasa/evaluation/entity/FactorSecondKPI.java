package net.tusdasa.evaluation.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With

/**
 * 二级指标
 * @Author: tusdasa
 * @Date: 2020-02-27 2:22 PM
 */

public class FactorSecondKPI {
    // 二级指标ID
    private Integer id;
    // 二级指标得分
    private Integer totalScore;
    // 二级指标名
    private String kpiName;
    // 所属三级指标
    private List<FactorThirdKPI> thirdKPIList;
}
