package com.hackerton.propofol.service;

import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${image.upload.dir}")
    private String imageDirPath;

    @SneakyThrows
    @Override
    public byte[] getImage(String imageName) {
        InputStream inputStream = new FileInputStream(new File(imageDirPath, imageName));

        return IOUtils.toByteArray(inputStream);
    }
}
