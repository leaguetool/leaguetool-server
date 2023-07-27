package com.s6.leaguetool.emoji;

import lombok.Data;

import java.util.List;

@Data
public class Emoji {

    private String emoji;

    private String title;

    private String text;

    private List<Emoji> data;
}
