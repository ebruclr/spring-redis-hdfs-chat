package com.example.project2.service;

import com.example.project2.dto.CreateOrderRequest;
import com.example.project2.dto.OrderDetailDto;
import com.example.project2.model.Customer;
import com.example.project2.model.Order;
import com.example.project2.model.Product;
import com.example.project2.repository.CustomerRepository;
import com.example.project2.repository.OrderRepository;

// For Redis
import com.example.project2.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


// Class Definitions
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Cacheable(value = "order_details")
    public List<OrderDetailDto> getAllOrderDetails(){
        return orderRepository.fetchOrderDetailList();
    }

    @CacheEvict(value = "order_details", allEntries = true)
    public Order createOrder(CreateOrderRequest request){

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id"));
        com.example.project2.model.Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setCity(request.getCity());
        order.setDeliveryStatus(request.getDeliveryStatus());
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Transactional
    @CacheEvict(value = "order_details", allEntries = true)
    public Order updateOrder(Long orderId, CreateOrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Order ID: " + orderId));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id"));
        com.example.project2.model.Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id"));

        order.setCustomer(customer);
        order.setProduct(product);
        order.setCity(request.getCity());
        order.setDeliveryStatus(request.getDeliveryStatus());
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Transactional
    @CacheEvict(value = "order_details", allEntries = true)
    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }
}
