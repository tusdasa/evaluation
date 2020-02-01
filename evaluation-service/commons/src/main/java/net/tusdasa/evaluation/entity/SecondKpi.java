package net.tusdasa.evaluation.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@ToString
public class SecondKpi implements Serializable {

    private Integer secondKpiId;

    private Integer firstKpiId;

    private String secondKpiContent;

    private static final long serialVersionUID = 1L;

}