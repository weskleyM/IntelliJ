package com.ufc.main.service;

import com.ufc.main.entities.Item;
import com.ufc.main.entities.Pedido;
import com.ufc.main.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PratoService pratoService;

    public void saveItens(Iterable<Item> itens) {
        itemRepository.saveAll(itens);
    }

    public List<Item> findByPedido(Pedido pedido) {
        return itemRepository.findByPedido(pedido);
    }
}
