package com.s6.leaguetool.chat.packages;

import lombok.*;

import java.io.Serializable;

/**
 * 大区
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Region implements Serializable {
    private static final long serialVersionUID = 3691355732719784265L;
    /**
     * 大区名字
     */
    private String name;
    /**
     * 大区id
     */
    private Integer id;
}
