package com.example.NHD_BOOK_SHOP.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.NHD_BOOK_SHOP.domain.Role;
import com.example.NHD_BOOK_SHOP.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Tạo mới role
    public Role handleCreateRole(Role role) {
        return this.roleRepository.save(role);
    }

    // Lấy một role dựa vào id
    public Role handleGetRoleById(UUID id) {
        Optional<Role> opRole = this.roleRepository.findById(id);
        if (opRole.isPresent()) {
            return opRole.get();
        }
        return null;
    }

    // Lấy tất cả role
    public List<Role> handleGetListRoles() {
        return this.roleRepository.findAll();
    }

    // Cập nhật role
    public Role handleUpdateRole(Role role) {
        Role currentRole = this.handleGetRoleById(role.getId());
        if (currentRole != null) {
            currentRole.setName(role.getName());
            currentRole.setDescription(role.getDescription());

            this.roleRepository.save(currentRole);
        }
        return currentRole;
    }

    // Xóa role
    public void handleDeleteRole(UUID id) {
        this.roleRepository.deleteById(id);
    }

    // Kiểm tra role có trùng tên
    public boolean handleCheckRoleExistByName(String name){
        return this.roleRepository.existsByName(name);
    }
}
