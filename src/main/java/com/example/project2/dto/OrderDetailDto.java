package com.example.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto implements Serializable {
    private Long orderId;
    private String deliveryStatus;
    private String city;
    private LocalDateTime orderDate;

    private Long customerId;
    private String customerName;
    private String customerPhone;
    public String customerAddress;

    private Long productId;
    private String productName;
    private String supplier;
    private Double productPrice;
    private String imagePath;

}
