package com.example.NHD_BOOK_SHOP.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.NHD_BOOK_SHOP.domain.User;
import com.example.NHD_BOOK_SHOP.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Tạo người dùng mới
    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    // Lấy người dùng bằng id
    public User handleGetUserById(UUID id) {
        Optional<User> opUser = this.userRepository.findById(id);
        if (opUser != null) {
            return opUser.get();
        }
        return null;
    }

    // Lấy tất cả người dùng
    public List<User> handleGetAllUsers() {
        return this.userRepository.findAll();
    }

    // Cập nhật người dùng
    public User handleUpdateUser(User user) {
        User currentUser = this.handleGetUserById(user.getId());
        if (currentUser != null) {
            currentUser.setName(user.getName());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());

            this.userRepository.save(currentUser);
        }

        return currentUser;
    }

    // Xóa người dùng
    public void handleDeleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }
}
