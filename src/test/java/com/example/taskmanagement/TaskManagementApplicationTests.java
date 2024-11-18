package com.example.taskmanagement;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskManagementApplicationTests {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void contextLoads() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);

        Task savedTask = taskRepository.save(task);
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isNotNull();
    }
}
