package com.example.taskmanagement;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");

        User savedUser = userService.createUser(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        userRepository.save(user);

        User foundUser = userService.getUserById(user.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setRole("USER");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setRole("USER");
        userRepository.save(user2);

        assertThat(userService.getAllUsers()).hasSize(2);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        userRepository.save(user);

        user.setUsername("updateduser");

        User updatedUser = userService.updateUser(user.getId(), user);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo("updateduser");
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        userRepository.save(user);

        userService.deleteUser(user.getId());
        assertThat(userRepository.findById(user.getId())).isEmpty();
    }

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        userRepository.save(user);

        User foundUser = userService.findByUsername("testuser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }
}
