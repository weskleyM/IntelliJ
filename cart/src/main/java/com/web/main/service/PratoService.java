package com.web.main.service;

import com.web.main.entity.Prato;
import java.util.List;

public interface PratoService {

    public Iterable<Prato> findAll();
    public Prato find(Integer id);
}
