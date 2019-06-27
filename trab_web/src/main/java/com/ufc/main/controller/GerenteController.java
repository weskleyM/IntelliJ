package com.ufc.main.controller;

import com.ufc.main.entities.Prato;
import com.ufc.main.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView adicionarPrato(Prato prato) {
        return new ModelAndView("gerente/cadastrarPrato");
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ModelAndView salvarPrato(@Validated Prato prato, BindingResult result, RedirectAttributes attributes, @RequestParam(value = "imagem") MultipartFile imagem) {

        ModelAndView mv = new ModelAndView("redirect:/gerente/listar");

        if (result.hasErrors()) {
            return adicionarPrato(prato);
        }

        pratoService.add(prato, imagem);
        attributes.addFlashAttribute("mensagem", "Prato adicionado com sucesso!");

        return mv;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ModelAndView listarPratos() {

        ModelAndView mv = new ModelAndView("gerente/listarPrato");

        mv.addObject("pratos", pratoService.findStatusActive());

        return mv;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletarPrato(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/gerente/listar");
        pratoService.remove(id);
        return mv;
    }

    @RequestMapping(value = "/edite/{id}", method = RequestMethod.GET)
    public ModelAndView editaPrato(@PathVariable(name = "id") Long id) {
        ModelAndView mv = new ModelAndView("gerente/editar");
        Prato prato = pratoService.findById(id);
        mv.addObject("prato", prato);
        return mv;
    }
}
