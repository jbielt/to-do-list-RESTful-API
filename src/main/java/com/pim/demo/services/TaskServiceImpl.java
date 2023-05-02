package com.pim.demo.services;

import com.pim.demo.exception.TaskNotFoundException;
import com.pim.demo.models.entity.Task;
import com.pim.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {


    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }



    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    @Override
    public Task findById(Long id) {
        Optional<Task> resultTask = taskRepository.findById(id);
        Task task = null;

        if(resultTask.isPresent()){
            task = resultTask.get();
        }else{
            throw new TaskNotFoundException("Did not find task with id: " + id);
        }
        return task;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findByCompletedTrue() {
        return taskRepository.findByCompletedTrue();
    }
    @Override
    public List<Task> findByCompletedFalse() {
        return taskRepository.findByCompletedFalse();
    }
}
