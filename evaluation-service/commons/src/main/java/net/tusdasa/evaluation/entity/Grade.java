package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.GradeRequest;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@With

/**
 * @Description 年级类
 * @author tusdasa
 * @version 1.0
 * */

public class Grade implements Serializable, Comparable<GradeRequest> {

    /**
     * 年级Id
     */
    private Integer gradeId;

    /**
     * 年级名
     */
    private String gradeName;

    private static final long serialVersionUID = 1004L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see GradeRequest
     */
    @Override
    public int compareTo(GradeRequest request) {
        if (request.getGradeId().intValue() == this.getGradeId()
                && request.getGradeName().equals(this.getGradeName())
        ) {
            return 0;
        }
        return -1;
    }
}