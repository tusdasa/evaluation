package net.tusdasa.evaluation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentEvaluation implements Serializable {

    // MongoDB id
    private String id;

    // 评教学生Id
    private Long studentId;

    // 当前学期Id
    private Integer termId;

    // 当前课程Id
    private Integer courseId;

    // 总分 由分析微服务计算
    private Integer total;

    // 三级指标得分
    private List<KpiScore> kpiScoreList;

}
