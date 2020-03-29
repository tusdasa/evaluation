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

/**
 * 权限类 mongodb 数据表 right
 * @author tusdasa
 * @version 1.0
 * */

public class Right implements Serializable {

    /**
     * 主键ID 对应角色表
     * 为1 是学生
     */
    private Integer id;

    /**
     * 一级指标权限 存放 firstKpiId 主键值
     */
    private HashSet<Integer> firstKpiId;

    /**
     * 二级指标权限 存放 secondKpiId 主键值
     */
    private HashSet<Integer> secondKpiId;

    /**
     * 三级指标权限 存放 thirdKpiId 主键值
     */
    private HashSet<Integer> thirdKpiId;

    public boolean checkRight() {
        if (this.getFirstKpiId().isEmpty() || this.getSecondKpiId().isEmpty() || this.getThirdKpiId().isEmpty()) {
            return false;
        }
        return true;
    }

}
