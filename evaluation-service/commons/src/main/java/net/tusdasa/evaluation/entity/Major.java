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
public class Major implements Serializable {

    private Integer majorId;

    private String majorName;

    private static final long serialVersionUID = 1003L;

}