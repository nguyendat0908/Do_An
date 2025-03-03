package com.example.NHD_BOOK_SHOP.domain;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.example.NHD_BOOK_SHOP.util.constant.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String receiver_name;
    private String receiver_address;
    private String receiver_phone;
    private double shipping_price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date order_date;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

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
