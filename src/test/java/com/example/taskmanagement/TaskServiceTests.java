package com.example.taskmanagement;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskServiceTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void createTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);

        Task savedTask = taskService.createTask(task);
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isNotNull();
    }

    @Test
    void getTaskById() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);
        taskRepository.save(task);

        Task foundTask = taskService.getTaskById(task.getId());
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getTitle()).isEqualTo("Test Task");
    }

    @Test
    void getAllTasks() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("This is task 1");
        task1.setCompleted(false);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("This is task 2");
        task2.setCompleted(false);
        taskRepository.save(task2);

        assertThat(taskService.getAllTasks()).hasSize(2);
    }

    @Test
    void updateTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);
        taskRepository.save(task);

        task.setTitle("Updated Task");

        Task updatedTask = taskService.updateTask(task.getId(), task);
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Task");
    }

    @Test
    void deleteTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);
        taskRepository.save(task);

        taskService.deleteTask(task.getId());
        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
}
