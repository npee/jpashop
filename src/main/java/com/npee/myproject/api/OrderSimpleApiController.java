package com.npee.myproject.api;

import com.npee.myproject.entity.domain.Order;
import com.npee.myproject.entity.domain.repository.OrderRepository;
import com.npee.myproject.entity.domain.repository.OrderSearch;
import com.npee.myproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Order
 * Order -> Member (ManyToOne)      v
 * Order -> Delivery (OneToOne)     v
 * Order -> OrderItem (OneToMany)
 * -> XTOOne 성능최적화
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        return all;
    }
}
