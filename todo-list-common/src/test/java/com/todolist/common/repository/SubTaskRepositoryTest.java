package com.todolist.common.repository;

import com.todolist.common.domain.SubTask;
import com.todolist.common.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubTaskRepositoryTest {

    private TaskRepository taskRepository;

    private SubTaskRepository subTaskRepository;

    @Autowired
    public SubTaskRepositoryTest(TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }

    @BeforeEach
    void setUp() {
        Task savedTask = taskRepository.save(Task.builder().title("To-do #1").build());
        log.debug("insert Task Info : {}", savedTask);

        // savedTask
        List<SubTask> subTasks = new ArrayList<>();
        IntStream.range(1, 6).forEach(i -> subTasks.add(SubTask.builder().title("Sub-Task #" + i + " 입니다.").task(savedTask).build()));
        List<SubTask> savedSubTasks = subTaskRepository.saveAll(subTasks);
        savedSubTasks.forEach(s -> log.debug("saved SubTask Info : {}", s));
    }

    @DisplayName("Task에 대한 Sub Task 조회 테스트")
    @Test
    void selectAll() {
        Task task = taskRepository.findById(1L).orElse(new Task());
        List<SubTask> subTasks = subTaskRepository.findByTask(task);
        subTasks.forEach(s -> log.debug("saved SubTask Info : {}", s));

        assertThat(subTasks.size()).isEqualTo(5);
        assertThat(subTasks.get(0).getTask().getId()).isEqualTo(task.getId());
    }

    @DisplayName("Sub Task 등록 테스트")
    @Test
    void save() {
        Task task = taskRepository.findById(1L).orElse(new Task());
        log.debug("task info : {}", task);
        SubTask savedSubTask = subTaskRepository.save(SubTask.builder().title("Sub-Task 입니다.").task(task).build());
        log.debug("saved SubTask info : {}", savedSubTask);
        SubTask findSubTask = subTaskRepository.findById(savedSubTask.getId()).orElse(new SubTask());
        log.debug("find SubTask info : {}", findSubTask);
        Task findTask = taskRepository.findById(1L).orElse(new Task());
        log.debug("findTask info : {}", findTask);

        assertThat(savedSubTask.getId()).isEqualTo(findSubTask.getId());
        assertThat(findTask.getId()).isEqualTo(findSubTask.getTask().getId());
    }

    @DisplayName("Sub Task 수정 테스트")
    @Test
    void update() {
        Task task = taskRepository.findById(1L).orElse(new Task());
        log.debug("task info : {}", task);
        SubTask savedSubTask = subTaskRepository.save(SubTask.builder().title("Sub-Task 입니다.").task(task).build());
        log.debug("saved SubTask info : {}", savedSubTask);
        SubTask findSubTask = subTaskRepository.findById(savedSubTask.getId()).orElse(new SubTask());
        log.debug("findSubTask info : {}", findSubTask);
        findSubTask.modifyTitle("변경한 Sub-Task 입니다.").modifyUpdateDate();
        SubTask updateSubTask = subTaskRepository.save(findSubTask);
        log.debug("updateSubTask info : {}", updateSubTask);

        assertThat(updateSubTask.getTitle()).isEqualTo("변경한 Sub-Task 입니다.");
    }

}
