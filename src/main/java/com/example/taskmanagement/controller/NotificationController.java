package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Api(value = "Notification Management System", description = "Operations pertaining to notifications in Task Management System")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    @ApiOperation(value = "Create a new notification", response = Notification.class)
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping
    @ApiOperation(value = "Get all notifications", response = List.class)
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}
