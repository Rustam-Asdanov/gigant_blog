package com.gigant.blog.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController("/api")
public class ImageGetController {

    @GetMapping("/{image-name}")
    public byte[] getLocalImage(
            @PathVariable("image-name") String imageName
    ){
        log.info("Image name is {}", imageName);
        if (imageName.isEmpty()){
            imageName = "not-found.jpg";
        }

        File file = new File("src/main/resources/static/image/"+imageName);
        byte[] imageBytes;

        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Some issues during the reading file",e);
        }

        return imageBytes;
    }

    @GetMapping("/getAccountImage/{userId}/download/{imageName}")
    public byte[] getAccountImage(
            @PathVariable("userId") long id,
            @PathVariable("imageName") String imageName
    ){
        String path = String.format(
                "src/main/resources/static/userdata/user_%s/%s",id,imageName
        );

        File file = new File(path);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Photo not found",e);
        }

        return imageBytes;
    }
}
