package com.example.taskmanagement;

import com.example.taskmanagement.model.Notification;
import com.example.taskmanagement.repository.NotificationRepository;
import com.example.taskmanagement.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationServiceTests {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void createNotification() {
        Notification notification = new Notification();
        notification.setMessage("Test Notification");
        notification.setCreatedAt(LocalDateTime.now());

        Notification savedNotification = notificationService.createNotification(notification);
        assertThat(savedNotification).isNotNull();
        assertThat(savedNotification.getId()).isNotNull();
    }

    @Test
    void getAllNotifications() {
        Notification notification1 = new Notification();
        notification1.setMessage("Notification 1");
        notification1.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification1);

        Notification notification2 = new Notification();
        notification2.setMessage("Notification 2");
        notification2.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification2);

        List<Notification> notifications = notificationService.getAllNotifications();
        assertThat(notifications).hasSize(2);
    }
}
