package com.s6.leaguetool.chat.packages;

import com.s6.leaguetool.chat.packages.enums.OtherPakType;
import lombok.*;

/**
 * 其他包
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtherPak {
    /**
     * 其他包类型
     */
    private OtherPakType otherPakType;
    /**
     * 数据
     */
    private String data;
}
