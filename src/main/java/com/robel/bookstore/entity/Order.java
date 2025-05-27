package com.robel.bookstore.entity;

import com.robel.bookstore.enums.OrderStatus;
import com.robel.bookstore.enums.PaymentMethod;
import com.robel.bookstore.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    @Min(1)
    private Integer totalQuantity;

    @NotNull
    @Min(0)
    private Double totalAmount;

    @Min(0)
    @Builder.Default
    private Double discountAmount = 0.0;

    @Min(0)
    @Builder.Default
    private Double discountPercentage = 0.0;


    @NotNull
    @Min(0)
    private Double finalAmount; //payable amount after discount


    private LocalDateTime deliveryDate;

    @Size(max = 500)
    private String notes;


    @CreationTimestamp
    private LocalDateTime orderDate;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //relations
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
}
