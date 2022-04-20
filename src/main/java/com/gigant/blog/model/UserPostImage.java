package com.gigant.blog.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_images")
@Data
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
}
