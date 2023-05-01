package com.pim.demo.controllers;


import com.pim.demo.models.entity.Task;
import com.pim.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {


    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/")
    ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks(){
        return ResponseEntity.ok(taskService.findByCompletedTrue());
    }

    /*
    @GetMapping("/incompleted")
    public ResponseEntity<List<Task>> getAllIncompletedTasks(){
        return ResponseEntity.ok(taskService.findAllIncompleteTask());
    }
    */

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setId(0L);
        return ResponseEntity.ok(taskService.save(task));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        return ResponseEntity.ok(taskService.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        try{
            taskService.deleteById(id);
            return (ResponseEntity<HttpStatus>) ResponseEntity.noContent();
        }catch (Exception e){
            return (ResponseEntity<HttpStatus>) ResponseEntity.internalServerError();
        }

    }




}
