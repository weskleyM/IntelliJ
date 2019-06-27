package com.ufc.main.controller;

import com.ufc.main.entities.Item;
import com.ufc.main.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class EncarteController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "carrinho/encarte";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView atualizar(HttpSession session, @RequestParam(value = "qtd") int qtd,
                                  @RequestParam(value = "id") Long id) {

        ModelAndView mv = new ModelAndView("redirect:/carrinho/index");

        List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");

        int index = this.exists(id, carrinho);

        carrinho.get(index).setQtd(qtd);

        session.setAttribute("total", this.total(session));
        session.setAttribute("carrinho", carrinho);

        return mv;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable("id") Long id, HttpSession session) {

        ModelAndView mv = new ModelAndView("redirect:/carrinho/index");

        if (session.getAttribute("carrinho") == null) {

            List<Item> carrinho = new ArrayList<Item>();

            Item item = new Item();
            item.setPrato(pratoService.findById(id));
            item.setQtd(1);

            Double precoPrato = item.getPrato().getPreco().doubleValue();
            int qtd = item.getQtd();
            Double subtotal = precoPrato * qtd;

            item.setValor(Double.valueOf(subtotal));

            carrinho.add(item);

            session.setAttribute("carrinho", carrinho);
            session.setAttribute("total", this.total(session));

        } else {

            List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");

            int index = this.exists(id, carrinho);

            if (index == -1) {

                Item item = new Item();
                item.setPrato(pratoService.findById(id));
                item.setQtd(1);

                Double precoPrato = item.getPrato().getPreco().doubleValue();
                int qtd = item.getQtd();
                Double subtotal = precoPrato * qtd;

                item.setValor(Double.valueOf(subtotal));

                carrinho.add(item);

            } else {

                int quantidade = carrinho.get(index).getQtd() + 1;
                carrinho.get(index).setQtd(quantidade);

                Double precoPrato = carrinho.get(index).getPrato().getPreco().doubleValue();
                Double subtotal = precoPrato * quantidade;
                carrinho.get(index).setValor(Double.valueOf(subtotal));

            }

            session.setAttribute("carrinho", carrinho);
            session.setAttribute("total", this.total(session));

        }

        return mv;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id, HttpSession session) {

        Double total = 0.0;

        List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");
        int index = this.exists(id, carrinho);

        if (carrinho.get(index).getQtd() > 1) {
            int qtd = carrinho.get(index).getQtd();
            carrinho.get(index).setQtd(qtd - 1);

        } else {
            carrinho.remove(index);
        }

        if (carrinho.size() == 0) {
            session.setAttribute("carrinho", null);
            session.setAttribute("total", null);
        } else {
            session.setAttribute("carrinho", carrinho);
            session.setAttribute("total", this.total(session));
        }

        return "redirect:/carrinho/index";
    }

    private int exists(Long id, List<Item> carrinho) {

        for (int i = 0; i < carrinho.size(); i++) {

            if (carrinho.get(i).getPrato().getId() == id) {
                return i;
            }
        }

        return -1;
    }

    private double total(HttpSession session) {
        List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");
        double s = 0;

        for (Item item : carrinho) {
            s += item.getQtd() * item.getPrato().getPreco().doubleValue();
        }

        return s;
    }
}