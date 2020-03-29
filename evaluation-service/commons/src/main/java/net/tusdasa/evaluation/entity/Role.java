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
 * 角色类
 * @author tusdasa
 * @version 1.0
 * */

public class Role implements Serializable {

    /**
     * 角色ID
     *
     * @see Right
     */
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    private static final long serialVersionUID = 1006L;

}