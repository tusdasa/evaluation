package net.tusdasa.evaluation.kpi;

import lombok.*;

import java.util.List;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirstKpiResponse {

    private Integer firstKpiId;

    private String firsKpiContent;

    private Integer academicYearId;

    List<SecondKpiResponse> table;

}
