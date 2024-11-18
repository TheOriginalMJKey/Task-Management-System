package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks(Specification<Task> spec);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    void saveAllTasks(List<Task> tasks);
}
