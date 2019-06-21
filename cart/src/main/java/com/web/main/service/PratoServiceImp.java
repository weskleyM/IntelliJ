package com.web.main.service;

import com.web.main.dao.PratoDAO;
import com.web.main.entity.Prato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.util.List;

@Service
@Transactional
public class PratoServiceImp implements PratoService {

    @Autowired
    private PratoDAO pratoDAO;

    @Override
    public Iterable<Prato> findAll() {
        return pratoDAO.findAll();
    }

    @Override
    public Prato find(Integer id) {
        return pratoDAO.getOne(id);
    }
}
