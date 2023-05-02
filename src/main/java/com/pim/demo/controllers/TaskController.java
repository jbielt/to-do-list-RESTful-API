package com.pim.demo.controllers;


import com.pim.demo.models.entity.Task;
import com.pim.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {


    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/tasks/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks(){
        return ResponseEntity.ok(taskService.findByCompletedTrue());
    }

    @GetMapping("/tasks/incompleted")
    public ResponseEntity<List<Task>> getAllIncompletedTasks(){
        return ResponseEntity.ok(taskService.findByCompletedFalse());
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setId(0L);
        return ResponseEntity.ok(taskService.save(task));
    }

    @PostMapping("tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long idTask, @RequestBody Task task){
        task.setId(idTask);
        return ResponseEntity.ok(taskService.save(task));
    }

    @DeleteMapping("tasks/{id}")
    public String deleteTask(@PathVariable(value = "id") Long idTask){
        Task task = taskService.findById(idTask);

        if(task == null){
            throw new RuntimeException("Task id not found - " + idTask);
        }

        taskService.deleteById(idTask);

        return "Deleted task id - " + idTask;
    }




}
