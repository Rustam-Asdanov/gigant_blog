package com.gigant.blog.rest;

import com.gigant.blog.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController
@RequestMapping("/api")
@SessionAttributes("user")
public class ImageGetController {

    @GetMapping("/{image-name}")
    @ResponseBody
    public byte[] getLocalImage(
            @PathVariable("image-name") String imageName
    ) {
        log.info("Image name is {}", imageName);
        if (imageName.isEmpty()) {
            imageName = "not-found.jpg";
        }

        File file = new File("src/main/resources/static/image/" + imageName);
        byte[] imageBytes;

        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Some issues during the reading file", e);
        }

        return imageBytes;
    }

    @GetMapping("/getAccountImage/{userId}/download/{imageName}")
    @ResponseBody
    public byte[] getAccountImage(
            @SessionAttribute("user") Account currentAccount,
            @PathVariable("imageName") String imageName
    ) {
        log.info("id {} and imageName {}", currentAccount.getId(), imageName);
        String path = String.format(
                "src/main/resources/static/userdata/user_%s/%s", currentAccount.getId(), imageName
        );

        if (imageName.equals("profile.jpg")) {
            path = "src/main/resources/static/image/profile.jpg";
        }

        File file = new File(path);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Photo not found", e);
        }

        return imageBytes;
    }

    @GetMapping("/getAccountImage/carousel/{imageName}")
    public byte[] getImageForCarousel(
            @PathVariable("imageName") String imageName,
            @SessionAttribute("user") Account account
    ) {
        log.info("received image name is {}", imageName);
        String path = String.format("src/main/resources/static/userdata/user_%s/post/%s",
                account.getId(), imageName);

        File file = new File(path);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Photo not found", e);
        }

        return imageBytes;
    }

    @GetMapping("/postImage/{accountId}/get/{imageName}")
    public byte[] getPostImage(
            @PathVariable("imageName") String imageName,
            @PathVariable("account_id") long accountId
    ) {
        log.info("received image name is {}", imageName);
        String path = String.format("src/main/resources/static/userdata/user_%s/post/%s",
                accountId, imageName);

        File file = new File(path);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Photo not found", e);
        }

        return imageBytes;
    }
}
