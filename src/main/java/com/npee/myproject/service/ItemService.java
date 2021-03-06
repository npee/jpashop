package com.npee.myproject.service;

import com.npee.myproject.entity.domain.item.Book;
import com.npee.myproject.entity.domain.item.Item;
import com.npee.myproject.entity.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price) {
        Item findItem = itemRepository.findOne(itemId); // 영속 상태. 트랜잭션 안에서!
        findItem.setId(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
