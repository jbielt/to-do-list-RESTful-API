package com.pim.demo.services;

import com.pim.demo.models.entity.Task;

import java.util.List;

public interface TaskService {


    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();





}
