package com.todolist.common.repository;

import com.todolist.common.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TaskRepositoryTest {

    private TaskRepository taskRepository;

    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @BeforeEach
    void setUp() {
        IntStream.range(1, 11).forEach(i -> taskRepository.save(Task.builder().title("To-do #" + i).build()));
    }

    @DisplayName("Task 조회 테스트")
    @Test
    public void selectAll() {
        List<Task> taskList = taskRepository.findAll();
        log.debug("taskList size : {}", taskList.size());
        taskList.forEach(t -> log.debug(t.toString()));

        assertThat(taskList.size()).isEqualTo(10);
    }

    @DisplayName("Task 등록 테스트")
    @Test
    public void save() {
        Task savedTask = taskRepository.save(Task.builder().title("등록 테스트").build());
        log.debug("savedTask > {}", savedTask);
        Task findTask = taskRepository.findById(savedTask.getId()).orElse(new Task());
        log.debug("findTask > {}", findTask);

        assertThat(savedTask.getTitle()).isEqualTo("등록 테스트");
    }

    @DisplayName("Task 수정 테스트")
    @Test
    public void update() {
        Task findTask = taskRepository.findById(1L).orElse(new Task());
        log.debug("findTask > {}", findTask);
        findTask.modifyTitle("수정 테스트").modifyUpdateDate();
        log.debug("findTask check > {}", findTask);
        taskRepository.save(findTask);
        Task updatedTask = taskRepository.findById(findTask.getId()).orElse(new Task());
        log.debug("updatedTask > {}", updatedTask);

        assertThat(updatedTask.getId()).isEqualTo(findTask.getId());
        assertThat(updatedTask.getTitle()).isEqualTo(findTask.getTitle());
    }

}
