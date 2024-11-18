package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.util.CsvUtil;
import com.example.taskmanagement.util.TaskSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Api(value = "Task Management System", description = "Operations pertaining to tasks in Task Management System")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ApiOperation(value = "Create a new task", response = Task.class)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a task by ID", response = Task.class)
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all tasks", response = List.class)
    public List<Task> getAllTasks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) Boolean completed) {
        Specification<Task> spec = Specification.where(null);
        if (title != null) {
            spec = spec.and(TaskSpecification.hasTitle(title));
        }
        if (completed != null) {
            spec = spec.and(TaskSpecification.isCompleted(completed));
        }
        return taskService.getAllTasks(spec);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a task by ID", response = Task.class)
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a task by ID")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/export")
    @ApiOperation(value = "Export tasks to CSV", response = String.class)
    public ResponseEntity<String> exportTasksToCsv() throws IOException {
        List<Task> tasks = taskService.getAllTasks();
        String csv = CsvUtil.exportTasksToCsv(tasks);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=tasks.csv")
                .body(csv);
    }

    @PostMapping("/import")
    @ApiOperation(value = "Import tasks from CSV")
    public void importTasksFromCsv(@RequestParam("file") MultipartFile file) throws IOException {
        String csv = new String(file.getBytes());
        List<Task> tasks = CsvUtil.importTasksFromCsv(csv);
        taskService.saveAllTasks(tasks);
    }
}
