package com.example.NHD_BOOK_SHOP.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NHD_BOOK_SHOP.domain.User;
import com.example.NHD_BOOK_SHOP.service.UserService;
import com.example.NHD_BOOK_SHOP.util.exception.IdInvalidException;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Tạo người dùng mới
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws IdInvalidException {

        boolean isExistEmail = this.userService.handleCheckUserByEmail(user.getEmail());
        if (isExistEmail) {
            throw new IdInvalidException(
                    "User với email: " + user.getEmail() + " đã tồn tại. Vui lòng chọn email khác!");
        }

        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.handleCreateUser(user));
    }

    // Lấy người dùng bằng id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) throws IdInvalidException {

        User user = this.userService.handleGetUserById(id);
        if (user == null) {
            throw new IdInvalidException("User với ID: " + id + " không tồn tại!");
        }

        return ResponseEntity.ok(user);
    }

    // Lấy tất cả người dùng
    @GetMapping("/users")
    public ResponseEntity<List<User>> getListUsers() {
        return ResponseEntity.ok(this.userService.handleGetAllUsers());
    }

    // Cập nhật người dùng
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws IdInvalidException {

        User newUser = this.userService.handleUpdateUser(user);
        if (newUser == null) {
            throw new IdInvalidException("User với ID: " + user.getId() + " không tồn tại!");
        }

        return ResponseEntity.ok(newUser);
    }

    // Xóa người dùng
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) throws IdInvalidException {
        User user = this.userService.handleGetUserById(id);
        if (user == null) {
            throw new IdInvalidException("User với ID: " + id + " không tồn tại!");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

}
