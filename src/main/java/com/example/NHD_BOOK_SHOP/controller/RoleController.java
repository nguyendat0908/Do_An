package com.example.NHD_BOOK_SHOP.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NHD_BOOK_SHOP.domain.Role;
import com.example.NHD_BOOK_SHOP.service.RoleService;
import com.example.NHD_BOOK_SHOP.util.exception.IdInvalidException;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Tạo mới role
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) throws IdInvalidException {

        boolean isExistRoleName = this.roleService.handleCheckRoleExistByName(role.getName());
        if (isExistRoleName) {
            throw new IdInvalidException("Role với tên: " + role.getName() + " đã tồn tại. Vui lòng chọn tên khác!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.handleCreateRole(role));
    }

    // Lấy role dựa vào id
    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") UUID id) throws IdInvalidException {

        Role role = this.roleService.handleGetRoleById(id);
        if (role == null) {
            throw new IdInvalidException("Role với ID: " + id + " không tồn tại!");
        }

        return ResponseEntity.ok(role);
    }

    // Lấy list role
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getListRole() {
        return ResponseEntity.ok(this.roleService.handleGetListRoles());
    }

    // Cập nhật role
    @PutMapping("/roles")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) throws IdInvalidException {
        Role newRole = this.roleService.handleUpdateRole(role);
        if (newRole == null) {
            throw new IdInvalidException("Role với ID: " + role.getId() + " không tồn tại!");
        }
        return ResponseEntity.ok(newRole);
    }

    // Xóa role
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") UUID id) throws IdInvalidException {
        Role role = this.roleService.handleGetRoleById(id);
        if (role == null) {
            throw new IdInvalidException("Role với ID: " + id + " không tồn tại!");
        }
        this.roleService.handleDeleteRole(id);
        return ResponseEntity.ok(null);
    }

}
