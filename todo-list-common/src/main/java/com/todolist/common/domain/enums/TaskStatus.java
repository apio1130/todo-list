package com.todolist.common.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskStatus {
    READY("대기", "01"),
    DOING("진행중", "02"),
    END("완료", "03");

    private String desc;
    private String code;

    TaskStatus(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public static TaskStatus findStatus(String statusCode) {
        return Arrays.stream(TaskStatus.values()).filter(s -> s.getCode().equals(statusCode)).findAny().orElse(null);
    }
}
