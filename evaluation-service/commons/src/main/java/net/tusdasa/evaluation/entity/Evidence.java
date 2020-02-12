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
public class Evidence implements Serializable {

    private Integer evidenceId;

    private String evidenceName;

    private Integer evidenceType;

    private Integer evidenceScore;

    private static final long serialVersionUID = 100050L;

}