package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.entity.Order;
import com.dailycodebuffer.OrderService.external.client.ProductService;
import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //Order Entity ->save the data with status order created
        //productService-block products(reduce the quantity)
        //paymentService->Payments->sucess->COMPLETE orElse
        //CANCELLED
        log.info("placing order request: {}",orderRequest);

        //rest api call using feign client
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("creating order with status CREATED");

        Order order=Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order=orderRepository.save(order);

        log.info("order placed sucessfully with orderId: {}",order.getId());

        return order.getId();
    }
}
