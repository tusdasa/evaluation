package net.tusdasa.evaluation.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With

/**
 * @Description 指标得分类
 * @author tusdasa
 * @version 1.0
 * */

public class KpiScore implements Serializable {

    /**
     * 指标ID
     */
    private Integer kid;

    /**
     * 指标得分
     */
    private Integer score;

}
