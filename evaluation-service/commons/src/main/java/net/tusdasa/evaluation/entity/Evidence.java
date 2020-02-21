package net.tusdasa.evaluation.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder

/**
 * 举证类
 * @author tusdasa
 * @version 1.0
 * */

public class Evidence implements Serializable {

    /**
     * 举证Id
     */
    private Integer evidenceId;

    /**
     * 举证类型的名称
     */
    private String evidenceName;

    /**
     * 举证对应类型
     *
     * @see CalculationRule
     */
    private Integer evidenceType;

    /**
     * 本次举证得分
     */
    private Integer evidenceScore;

    private static final long serialVersionUID = 100050L;

}