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
 * @Description 职称类
 * @author tusdasa
 * @version 1.0
 * */

public class Professional implements Serializable {

    /**
     * 职称ID
     */
    private Integer professionalId;

    /**
     * 职称名
     */
    private String professionalTitle;

    private static final long serialVersionUID = 1005L;

}