package com.pim.demo.controllers;


import com.pim.demo.exception.TaskNotFoundException;
import com.pim.demo.models.entity.Task;
import com.pim.demo.services.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaskController {


    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "taskDescription", required = false) String taskDescription){
        if(taskDescription != null && !taskDescription.isEmpty()){
            return ResponseEntity.ok(taskService.findTaskByTaskDescriptionContains(taskDescription));
        }else{
            return ResponseEntity.ok(taskService.findAll());
        }
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTask(@PathVariable(value = "id") Long idTask){
        return ResponseEntity.ok(taskService.findById(idTask));
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
    public ResponseEntity<Task> createTask(@Valid @NotNull @RequestBody Task task) {
        task.setId(0L);
        return ResponseEntity.ok(taskService.save(task));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id")Long idTask, @Valid @RequestBody Task task){
        Task dbTask = taskService.findById(idTask);

        dbTask.setCompleted(task.isCompleted());
        dbTask.setTaskDescription(task.getTaskDescription());

        return ResponseEntity.ok(taskService.save(dbTask));
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable(value = "id") Long idTask){
        Task task = taskService.findById(idTask);

        if(task == null){
            throw new TaskNotFoundException("Task id not found - " + idTask);
        }
        taskService.deleteById(idTask);

        return "Deleted task id - " + idTask;

        //Podria retornar ResponseEntity.ok().build(); con el método tipo RepsponseEntity<?>
    }




}
