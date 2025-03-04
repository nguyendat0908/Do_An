package com.example.NHD_BOOK_SHOP.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NHD_BOOK_SHOP.domain.User;
import com.example.NHD_BOOK_SHOP.service.UserService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tạo người dùng mới
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.handleCreateUser(user));
    }

    // Lấy người dùng bằng id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(this.userService.handleGetUserById(id));
    }

    // Lấy tất cả người dùng
    @GetMapping("/users")
    public ResponseEntity<List<User>> getListUsers() {
        return ResponseEntity.ok(this.userService.handleGetAllUsers());
    }

    // Cập nhật người dùng
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.handleUpdateUser(user));
    }

    // Xóa người dùng
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

}
