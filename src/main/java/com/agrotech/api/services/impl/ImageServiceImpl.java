package com.agrotech.api.services.impl;
import com.agrotech.api.Repository.ImageRepository;
import com.agrotech.api.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl {
    private final ImageRepository imageMongoRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageMongoRepository) {
        this.imageMongoRepository = imageMongoRepository;
    }

    public String saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());
        Image savedImage = imageMongoRepository.save(image);
        return savedImage.getId();
    }

    public Image getImage(String id) {
        Optional<Image> optionalImage = imageMongoRepository.findById(id);
        return optionalImage.orElse(null);
    }
}
