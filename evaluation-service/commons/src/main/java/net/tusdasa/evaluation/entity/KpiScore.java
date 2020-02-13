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
public class KpiScore implements Serializable {

    private Integer kid;

    private Integer score;

}
