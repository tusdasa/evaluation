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
 * 部门类
 * @author tusdasa
 * @version 1.0
 * */

public class Department implements Serializable {

    /**
     * 部门Id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    private static final long serialVersionUID = 1006L;
}