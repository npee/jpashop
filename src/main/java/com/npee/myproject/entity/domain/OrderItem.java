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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Order order;

    private int orderPrice;
    private int count;
}
