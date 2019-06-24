package com.web.main.service;

import com.web.main.model.Prato;
import com.web.main.repository.PratoRepository;
import com.web.main.utils.Utils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PratoServiceImp implements PratoService {
    
    @Autowired
    private PratoRepository pratoRepository;
    
    @Override
    public List<Prato> listarPratos() {
        return pratoRepository.findAll();
    }
    
    @Override
    public void salvarPrato(Prato prato, MultipartFile imagem) {
        String local = "images/" + prato.getPnome() + ".png";
        Utils.salvarImagem(local, imagem);
        pratoRepository.save(prato);
    }
    
    @Override
    public Prato buscarPrato(Integer id) {
        return pratoRepository.getOne(id);
    }
    
    @Override
    public void deletarPrato(Integer id) {
        pratoRepository.deleteById(id);
    }
    
}
