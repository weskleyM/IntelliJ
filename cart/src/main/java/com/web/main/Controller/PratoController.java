package com.web.main.Controller;

import com.web.main.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("prato")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap mp) {
        mp.put("pratos", pratoService.findAll());
        return "prato/index";
    }
}
