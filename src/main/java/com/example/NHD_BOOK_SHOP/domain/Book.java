package com.example.NHD_BOOK_SHOP.domain;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.example.NHD_BOOK_SHOP.util.constant.TypeNameEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Số lượng sách sẵn có
    private int quantity;

    // Số lượng sách đã bán
    private int sold;

    // Giá sách mới
    private double new_price;

    // Giá sách cũ
    private double old_price;

    @Enumerated(EnumType.STRING)
    private TypeNameEnum book_type;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publication;

    private String image;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = Instant.now();
    }
}
