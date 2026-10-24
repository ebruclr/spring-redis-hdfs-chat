package com.example.project2.controller.crud;

import com.example.project2.dto.CreateOrderRequest;
import com.example.project2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderCrudController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.ok("Order created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody CreateOrderRequest request){
        orderService.updateOrder(id, request);
        return ResponseEntity.ok("Order updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
