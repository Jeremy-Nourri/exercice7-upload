package org.example.exercice7upload.service;

import org.example.exercice7upload.model.Image;
import org.example.exercice7upload.repository.Repository;

public class Service {

    private Repository repository = new Repository();

    public void saveImage(String fileName, String fileType, byte[] data) {
        repository.save(new Image(fileName, fileType, data));

    }

    public Image findImageById(int id) {
        return repository.findById(id);
    }

    public void deleteImage(Image image) {
        repository.delete(image);
    }

    public void findAllImages() {
        repository.findAll();
    }


}
