package com.npee.myproject.entity.domain.repository;

import com.npee.myproject.entity.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
