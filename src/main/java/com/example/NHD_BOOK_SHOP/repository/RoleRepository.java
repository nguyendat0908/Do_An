package com.example.NHD_BOOK_SHOP.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NHD_BOOK_SHOP.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    
    boolean existsByName(String name);
}
