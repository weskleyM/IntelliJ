package com.ufc.main.controller;

import com.ufc.main.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EncarteController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping(value = "encarte", method = RequestMethod.GET)
    public String index() {
        return "carrinho/encarte";
    }
}