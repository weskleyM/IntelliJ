package com.web.main.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {

    public static void salvarImagem(String local, MultipartFile imagem) {
        File file = new File(local);
        try {
            FileUtils.writeByteArrayToFile(file, imagem.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
