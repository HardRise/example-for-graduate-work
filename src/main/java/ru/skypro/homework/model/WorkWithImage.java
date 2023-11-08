package ru.skypro.homework.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class WorkWithImage {
    private WorkWithImage() {}

    /**
     * Save the new image.
     */
    public static String saveAndGetStringImage(String directory, String name, MultipartFile image) throws IOException {
        Path filePath = Path.of(directory, name + "." + getExtensions(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is);
             BufferedOutputStream bos = new BufferedOutputStream(os);
        ){
            bis.transferTo(bos);
        }

        return filePath.toString();
    }

    /**
     * Update the image.
     */
    public static String updateAndGetStringImage(String directory, String oldImage, MultipartFile image) throws IOException {
        String name = oldImage.substring(oldImage.lastIndexOf('/') + 1, oldImage.lastIndexOf('.'));

        Path pathOld = Path.of(oldImage);
        Files.deleteIfExists(pathOld);

        return saveAndGetStringImage(directory, name, image);
    }

    private static String getExtensions(String fileName) {
        if (fileName == null) {
            throw new NullPointerException();
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
