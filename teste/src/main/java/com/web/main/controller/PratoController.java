package com.web.main.controller;

import com.web.main.model.Prato;
import com.web.main.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PratoController {

    @Autowired
    PratoService pratoService;

    @RequestMapping(value = {"/lista"}, method = {RequestMethod.GET})
    public ModelAndView listarPrato() {
        ModelAndView mav = new ModelAndView();
        List<Prato> pratos = pratoService.listarPratos();
        mav.addObject("pratos", pratos);
        mav.setViewName("prato/listar");
        return mav;
    }

    @RequestMapping(value = {"/novo"}, method = {RequestMethod.GET})
    public ModelAndView salvar() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("prato", new Prato());
        mav.setViewName("prato/salvar");
        return mav;
    }

    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.GET})
    public ModelAndView deletarPrato(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        pratoService.deletarPrato(id);
        mav.setViewName("redirect:/admin/lista");
        return mav;
    }

    @RequestMapping(value = {"/edite/{id}"}, method = {RequestMethod.GET})
    public ModelAndView editaPrato(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        Prato prato = pratoService.buscarPrato(id);
        mav.addObject("prato", prato);
        mav.setViewName("prato/editar");
        return mav;
    }

    @RequestMapping(value = {"/salva"}, method = {RequestMethod.POST})
    public ModelAndView salvarPrato(@Valid Prato prato, @RequestParam(value = "imagem") MultipartFile imagem, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            //mav.addObject("mensagem", "Erro ao cadastrar prato!");
            mav.setViewName("prato/salvar");
            return mav;
        } else {
            pratoService.salvarPrato(prato, imagem);
            //mav.addObject("mensagem", "Prato cadastrado com sucesso!");
            mav.addObject("prato", new Prato());
            mav.setViewName("redirect:/admin/lista");
            return mav;
        }
    }
}
