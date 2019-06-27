package com.ufc.main.service;

import com.ufc.main.entities.Pedido;
import com.ufc.main.entities.Usuario;
import com.ufc.main.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void savePedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<Pedido> findByUsuario(Usuario usuario) {
        return pedidoRepository.findByUsuarioOrderByDataPedidoDesc(usuario);
    }

    public Pedido findById(Long id) {
        return pedidoRepository.getOne(id);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
}
