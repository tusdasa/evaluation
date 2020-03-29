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
 * 专业类
 * @author tusdasa
 * @version 1.0
 * */

public class Major implements Serializable {

    /**
     * 专业ID
     */
    private Integer majorId;

    /**
     * 专业名
     */
    private String majorName;

    private static final long serialVersionUID = 1003L;

}