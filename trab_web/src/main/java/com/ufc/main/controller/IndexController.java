package com.ufc.main.controller;

import com.ufc.main.entities.Usuario;
import com.ufc.main.service.PratoService;
import com.ufc.main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("pratos", pratoService.findStatusActive());
        return mv;
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
    public ModelAndView novoUsuario(Usuario usuario) {
        ModelAndView mv = new ModelAndView("registro");
        return mv;
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@Validated Usuario usuario, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return novoUsuario(usuario);
        }

        usuarioService.save(usuario);

        ModelAndView mv = new ModelAndView("registro");
        return mv;
    }

}
