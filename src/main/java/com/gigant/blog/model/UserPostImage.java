package com.gigant.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_images")
@Data
@NoArgsConstructor
public class UserPostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageURL;

    @ManyToOne(cascade = {CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.PERSIST,
                        CascadeType.REFRESH})
    @JoinColumn(name = "userPostId")
    private UserPost userPost;

    public UserPostImage(String imageURL) {
        this.imageURL = imageURL;
    }
}
