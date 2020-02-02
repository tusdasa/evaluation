package net.tusdasa.evaluation.commons;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class FirstKpiResponse {

    private Integer firstKpiId;

    private String firsKpiContent;

    private Integer academicYearId;

    private List<SecondKpiResponse> table;
}
