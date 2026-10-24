package com.example.project2.repository;

import com.example.project2.dto.OrderDetailDto;
import com.example.project2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.example.project2.dto.OrderDetailDto(" +
            "o.id, o.deliveryStatus, o.city, o.orderDate, " +
            "c.id, c.name, c.phone, c.address," +
            "p.id, p.name, p.supplier, p.price, p.imagePath) " +
            "FROM Order o " +
            "JOIN o.customer c " +
            "JOIN o.product p ")
    List<OrderDetailDto> fetchOrderDetailList();
}
