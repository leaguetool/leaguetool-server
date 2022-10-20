package com.s6.leaguetoolserver.component.emoji;

import lombok.Data;

import java.util.List;

@Data
public class Emoji {

    private String emoji;

    private String title;

    private String text;

    private List<Emoji> data;
}
