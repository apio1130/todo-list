package com.todolist.web.service;

import com.todolist.common.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskServiceTest {

    private TaskService taskService;

    @Autowired
    public TaskServiceTest(TaskService taskService) {
        this.taskService = taskService;
    }

    @BeforeEach
    void setUp() {

    }

    @DisplayName("저장 테스트")
    @Test
    void registTask() {
        Task task = taskService.registTask("할일1");
        log.debug("regist task info : {}", task);
        Assertions.assertThat(task.getTitle()).isEqualTo("할일1");
    }
}