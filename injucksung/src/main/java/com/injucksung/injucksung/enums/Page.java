package com.injucksung.injucksung.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum Page {
    BOOK(10), QUESTION_CATEGORY(20), QUESTION(10), USER(10);
    private final int size;
}