package com.gigant.blog.service;

import com.gigant.blog.model.UserPost;
import com.gigant.blog.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepository userPostRepository;

    @Autowired
    public UserPostServiceImpl(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    @Override
    public void addNewPost(UserPost userPost, MultipartFile[] multipartFile) {
        saveImages(multipartFile, userPost.getTheAccount().getId());

    }

    private void saveImages(MultipartFile[] multipartFile, long id) {
        String pathName = String.format("src/main/resources/static/userdata/user_%s/post");
        File file = new File(pathName);
        file.mkdir();

        for (MultipartFile mfile : multipartFile) {
            String fileName = String.format("%s-%s",mfile.getOriginalFilename(),UUID.randomUUID());
            Path path = Paths.get(file.getPath(), "/" + fileName);
            try {
                mfile.transferTo(path);
            } catch (IOException e) {
                throw new IllegalStateException("File "+mfile.getOriginalFilename()+"has some issues",e);
            }
        }
    }
}
