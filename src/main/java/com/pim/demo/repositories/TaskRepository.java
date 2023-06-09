package com.pim.demo.repositories;

import com.pim.demo.models.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    public List<Task> findTaskByCompletedTrue();
    public List<Task> findTaskByCompletedFalse();
    public List<Task> findTaskByTaskDescriptionContains(String description);

}
