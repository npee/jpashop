package com.npee.myproject.api;

import com.npee.myproject.entity.domain.Order;
import com.npee.myproject.entity.domain.repository.OrderRepository;
import com.npee.myproject.entity.domain.repository.OrderSearch;
import com.npee.myproject.entity.domain.repository.order.simplequery.OrderSimpleQueryDto;
import com.npee.myproject.entity.domain.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleQueryDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderSimpleQueryDto> result = orders.stream().map(o -> new OrderSimpleQueryDto(o)).collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<OrderSimpleQueryDto> result = orders.stream().map(o -> new OrderSimpleQueryDto(o)).collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }
}
