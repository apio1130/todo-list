package com.todolist.web.service;

import com.todolist.common.domain.Task;
import com.todolist.common.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task registTask(String taskName) {
        return taskRepository.save(Task.builder().title(taskName).build());
    }

}
