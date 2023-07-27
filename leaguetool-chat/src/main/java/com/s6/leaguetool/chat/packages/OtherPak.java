package com.s6.leaguetool.chat.packages;

import com.s6.leaguetool.chat.packages.enums.OtherPakType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtherPak {
    private OtherPakType otherPakType;

    private String data;
}
