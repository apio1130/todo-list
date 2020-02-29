package com.todolist.common.repository;

import com.todolist.common.domain.SubTask;
import com.todolist.common.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    List<SubTask> findByTask(Task task);
}
