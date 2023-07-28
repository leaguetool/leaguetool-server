package com.s6.leaguetool.emoji;

import lombok.*;

import java.util.List;

/**
 * emoji表情
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Emoji {
    /**
     * emoji表情
     */
    private String emoji;
    /**
     * emoji表情标题
     */
    private String title;
    /**
     * emoji表情文字
     */
    private String text;
    /**
     * emoji表情图片子项
     */
    private List<Emoji> data;
}
