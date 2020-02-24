package net.tusdasa.evaluation.commons;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@ToString

/**
 * @Author: tusdasa
 * @Date: 2020-02-24 1:41 PM
 */

public class UserInfo implements Serializable {
    private String id;
    private String name;
    private Integer role;

}
