package com.todolist.common.repository;

import com.todolist.common.domain.Groups;
import com.todolist.common.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByGroups(Groups groups);
}
