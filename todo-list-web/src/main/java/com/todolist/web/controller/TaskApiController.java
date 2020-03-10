package com.todolist.web.controller;

import com.todolist.common.domain.Task;
import com.todolist.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/tasks")
@RestController
public class TaskApiController {

    private TaskService taskService;

    @Autowired
    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity getTaskList() {
        List<Task> tasks = taskService.retrieveList();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", tasks);
        resultMap.put("count", tasks.size());

        return ResponseEntity.ok( resultMap);
    }

}
