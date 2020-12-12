package com.npee.myproject.entity.domain;

import com.npee.myproject.entity.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Order order;

    private int orderPrice;
    private int count;

    // === 생성 메서드 ===
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    // === 비즈니스 로직 ===
    /**
     * OrderItem 주문 취소
     */
    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * 최종 가격 반환
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
