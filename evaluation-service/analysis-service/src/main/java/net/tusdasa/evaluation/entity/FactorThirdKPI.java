package net.tusdasa.evaluation.entity;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With

/**
 * 三级指标
 * @Author: tusdasa
 * @Date: 2020-02-27 2:29 PM
 */

public class FactorThirdKPI {
    // 三级指标ID
    private Integer id;
    // 指标名
    private String kpiName;
    // 指标得分
    private Integer score;
}
