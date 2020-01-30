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
public class TeacherState implements Serializable {

    private Integer stateId;

    private String stateName;

    private static final long serialVersionUID = 1008L;

}