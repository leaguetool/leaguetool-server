package com.s6.leaguetool.chat.packages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region implements Serializable {
    private static final long serialVersionUID = 3691355732719784265L;
    //大区名字
    private String name;
    //大区id
    private Integer id;
}
