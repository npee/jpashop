package com.npee.myproject.service;

import com.npee.myproject.entity.domain.Delivery;
import com.npee.myproject.entity.domain.Member;
import com.npee.myproject.entity.domain.Order;
import com.npee.myproject.entity.domain.OrderItem;
import com.npee.myproject.entity.domain.item.Item;
import com.npee.myproject.entity.domain.repository.ItemRepository;
import com.npee.myproject.entity.domain.repository.MemberRepositoryOld;
import com.npee.myproject.entity.domain.repository.OrderRepository;
import com.npee.myproject.entity.domain.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepositoryOld memberRepositoryOld;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepositoryOld.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장 - cascade 정책 때문에 order만 persist 하면 딸린 친구들도 다 persist 된다.
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();

        // order 업데이트? -> dirty checking
        // orderStatus와 stockQuantity의 변경에 대한 로직을 짜지 않아도 동작한다.
    }

    /**
     * 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }

}
