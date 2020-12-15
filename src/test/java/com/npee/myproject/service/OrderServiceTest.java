package com.npee.myproject.service;

import com.npee.myproject.entity.domain.Address;
import com.npee.myproject.entity.domain.Member;
import com.npee.myproject.entity.domain.Order;
import com.npee.myproject.entity.domain.OrderStatus;
import com.npee.myproject.entity.domain.item.Book;
import com.npee.myproject.entity.domain.item.Item;
import com.npee.myproject.entity.domain.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());

    }
    
    @Test
    public void 주문취소() throws Exception {
        // given
        
        // when
        // then
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        // given

        // when
        // then
    }

}