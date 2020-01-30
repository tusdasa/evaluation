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
public class Professional implements Serializable {

    private Integer professionalId;

    private String professionalTitle;

    private static final long serialVersionUID = 1005L;

}