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
public class Role implements Serializable {

    private Integer roleId;

    private String roleName;

    private Integer authorityId;

    private static final long serialVersionUID = 1006L;

}