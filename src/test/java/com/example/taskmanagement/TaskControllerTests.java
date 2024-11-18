package com.example.taskmanagement;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void createTask() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(task)))
                .andExpect(status().isCreated());
    }

    @Test
    void getTaskById() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);
        taskRepository.save(task);

        mockMvc.perform(get("/api/tasks/{id}", task.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllTasks() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTask() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);
        taskRepository.save(task);

        task.setTitle("Updated Task");

        mockMvc.perform(put("/api/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(task)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTask() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCompleted(false);
        taskRepository.save(task);

        mockMvc.perform(delete("/api/tasks/{id}", task.getId()))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
