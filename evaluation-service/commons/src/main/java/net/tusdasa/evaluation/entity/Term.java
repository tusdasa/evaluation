package net.tusdasa.evaluation.entity;

import lombok.*;
import net.tusdasa.evaluation.vo.TermRequest;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
@EqualsAndHashCode
@Builder

/**
 * 学期类
 * @author tusdasa
 * @version 1.0
 * */

public class Term implements Serializable, Comparable<TermRequest> {
    /**
     * 学期Id
     */
    private Integer termId;

    /**
     * 学期开始时间
     */
    private Date startTime;

    /**
     * 学期结束时间
     */
    private Date endTime;

    /**
     * 学期名
     */
    private String termName;

    private static final long serialVersionUID = 1009L;

    /**
     * @param request 更新 或者 创建请求
     * @return 返回 0 表示完全相同  返回 -1 表示至少有一项不同
     * @see TermRequest
     */
    @Override
    public int compareTo(TermRequest request) {
        if (request.getTermId().intValue() == this.getTermId().intValue()
                && request.getTermName().equals(this.getTermName())) {
            return 0;
        } else {
            return -1;
        }
    }
}