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
public class Department implements Serializable {

    private Integer departmentId;

    private String departmentName;

    private static final long serialVersionUID = 1006L;
}