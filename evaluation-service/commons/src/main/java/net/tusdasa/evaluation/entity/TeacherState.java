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

/**
 * @Description 教师状态类 参加评教 不参加评教
 * @author tusdasa
 * @version 1.0
 * */

public class TeacherState implements Serializable {

    /**
     * 状态Id
     */
    private Integer stateId;

    /**
     * 状态名
     */
    private String stateName;

    private static final long serialVersionUID = 1008L;

}