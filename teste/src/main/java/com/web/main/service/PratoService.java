package com.web.main.service;

import com.web.main.model.Prato;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PratoService {

    public List<Prato> listarPratos();

    public void salvarPrato(Prato prato, MultipartFile imagem);

    public Prato buscarPrato(Integer id);

    public void deletarPrato(Integer id);
}
