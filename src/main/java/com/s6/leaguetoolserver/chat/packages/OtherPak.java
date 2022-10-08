package com.s6.leaguetoolserver.chat.packages;

import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
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
