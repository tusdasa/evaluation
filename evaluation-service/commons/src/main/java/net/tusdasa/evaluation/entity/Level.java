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
public class Level implements Serializable {

    private Integer levelId;

    private String levelName;

    private Integer levelType;

    private Integer levelSore;

    private static final long serialVersionUID = 1000645L;

}