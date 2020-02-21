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
 * 指标等级类
 * @author tusdasa
 * @version 1.0
 * */

public class Level implements Serializable {

    /**
     * 等级ID
     */
    private Integer levelId;

    /**
     * 等级名称
     */
    private String levelName;

    /**
     * 等级类型ID 见数据表 t_type <em>level_type</em> 字段
     */
    private Integer levelType;

    /**
     * 该等级的得分
     */
    private Integer levelSore;

    private static final long serialVersionUID = 1000645L;

}