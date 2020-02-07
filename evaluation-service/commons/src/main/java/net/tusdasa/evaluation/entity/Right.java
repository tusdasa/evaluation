package net.tusdasa.evaluation.entity;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
@ToString
public class Right implements Serializable {

    private Integer id;

    private HashSet<Integer> firstKpiId;

    private HashSet<Integer> secondKpiId;

    private HashSet<Integer> thirdKpiId;

}
