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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/finalizar", method = RequestMethod.GET)
    public ModelAndView finalizar(HttpSession session) {

        ModelAndView mv = new ModelAndView("redirect:/usuario/pedidos");

        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = (UserDetails) auth;

        Usuario usuario = usuarioService.findByEmail(user.getUsername());

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);

        pedido.setTotal(Double.valueOf(0));
        pedidoService.savePedido(pedido);

        Iterable<Item> carrinho = (Iterable<Item>) session.getAttribute("carrinho");

        for (Item item : carrinho) {
            item.setPedido(pedido);
        }

        itemService.saveItens(carrinho);

        Double total = (Double) session.getAttribute("total");

        pedido.setTotal(Double.valueOf(total));

        pedidoService.savePedido(pedido);

        session.removeAttribute("carrinho");
        session.removeAttribute("total");

        return mv;
    }
}
