package com.ufc.main.repositories;

import com.ufc.main.entities.Item;
import com.ufc.main.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByPedido(Pedido pedido);
}
