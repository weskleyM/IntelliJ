package com.ufc.main.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SaveImages {

    public static void saveImage(String local, MultipartFile imagem) {
        File file = new File(local);
        try {
            FileUtils.writeByteArrayToFile(file, imagem.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteImage(String local) {
        File file = new File(local);
        file.delete();
    }
}
