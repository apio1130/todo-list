package com.todolist.common.domain.enums;

import lombok.Getter;

import java.util.Arrays;

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

    public static SubTaskStatus findStatus(String statusCode) {
        return Arrays.stream(SubTaskStatus.values()).filter(s -> s.getCode().equals(statusCode)).findAny().orElse(null);
    }

}
