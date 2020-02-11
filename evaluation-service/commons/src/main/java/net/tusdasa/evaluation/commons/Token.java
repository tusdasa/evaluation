package net.tusdasa.evaluation.commons;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Token implements Serializable {
    private String sub;
    private Integer role;
    private Long exp;
}
