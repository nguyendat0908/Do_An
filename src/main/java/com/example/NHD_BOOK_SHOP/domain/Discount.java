package com.example.NHD_BOOK_SHOP.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.NHD_BOOK_SHOP.util.constant.DiscountTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Mã giảm giá
    private String discount_code;

    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum type;

    // Giá trị đơn hàng tối thiểu
    private double min_order_value;

    // Ngày bắt đầu áp dụng
    private LocalDateTime start_date;

    // Ngày kết thúc
    private LocalDateTime end_date;

    // Số lượng cho phép sử dụng tối đa
    private int usage_limit;

    // Số lượng mã đã được sử dụng
    private int used_count;

    private Instant createdAt;
    private Instant updatedAt;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    private List<Order> orders;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = Instant.now();
    }
}
