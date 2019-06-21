package com.web.main.Controller;

import com.web.main.entity.Item;
import com.web.main.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("encarte")
public class EncarteController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "encarte/index";
    }

    @RequestMapping(value = "comprar/{id}", method = RequestMethod.GET)
    public String compra(@PathVariable("id") Integer id, ModelMap mp, HttpSession session) {
        if (session.getAttribute("encarte") == null) {
            List<Item> encarte = new ArrayList<Item>();
            encarte.add(new Item(pratoService.find(id), 1));
            session.setAttribute("encarte", encarte);
        } else {

        }
        return "redirect:../../encarte";
    }
}
