package com.ufc.main.repositories;

import com.ufc.main.entities.Pedido;
import com.ufc.main.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByUsuarioOrderByDataPedidoDesc(Usuario usuario);
}
