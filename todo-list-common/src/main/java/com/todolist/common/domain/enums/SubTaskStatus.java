package com.todolist.common.domain.enums;

import lombok.Getter;

@Getter
public enum SubTaskStatus {
    READY("대기", "01"),
    DOING("진행중", "02"),
    END("완료", "03");

    private String desc;
    private String code;

    SubTaskStatus(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
