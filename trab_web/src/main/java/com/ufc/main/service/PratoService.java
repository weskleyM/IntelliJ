package com.ufc.main.service;

import com.ufc.main.config.LocalImage;
import com.ufc.main.entities.Prato;
import com.ufc.main.repositories.PratoRepository;
import com.ufc.main.util.SaveImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    public void add(Prato prato, MultipartFile imagem) {
        prato.setStatus(1);
        String local = "images/" + prato.getNome() + ".png";
        SaveImages.saveImage(local, imagem);
        pratoRepository.save(prato);
    }

    public List<Prato> listarPratos() {
        return pratoRepository.findAll();
    }

    public Prato findById(Long id) {
        return pratoRepository.getOne(id);
    }

    public void remove(Long id) {
        Prato prato = pratoRepository.getOne(id);
        String local = "images/" + prato.getNome() + ".png";
        SaveImages.deleteImage(local);
        prato.setStatus(0);
        pratoRepository.deleteById(id);
    }

    public List<Prato> findStatusActive() {
        return pratoRepository.findByStatus(1);
    }
}
