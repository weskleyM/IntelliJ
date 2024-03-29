package com.ufc.main.controller;

import com.ufc.main.entities.Item;
import com.ufc.main.entities.Pedido;
import com.ufc.main.entities.Usuario;
import com.ufc.main.service.ItemService;
import com.ufc.main.service.PedidoService;
import com.ufc.main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/pedidos", method = RequestMethod.GET)
    public ModelAndView listarPedidosDoCliente() {

        ModelAndView mv = new ModelAndView("usuarios/listarPedidos");

        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = (UserDetails) auth;

        Usuario usuario = usuarioService.findByEmail(user.getUsername());

        List<Pedido> pedidosCliente = pedidoService.findByUsuario(usuario);

        for (Pedido p : pedidosCliente) {

            List<Item> itens = new ArrayList<>();

            itens = itemService.findByPedido(p);
            p.setItens(itens);

        }

        if (pedidosCliente.size() == 0) {
            mv.addObject("pedidosCliente", null);
        } else {
            mv.addObject("pedidosCliente", pedidosCliente);
        }

        return mv;
    }
}
