package com.gigant.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_posts")
@Data
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "userPost")
    private List<UserPostImage> images = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.PERSIST,
                        CascadeType.REFRESH})
    @JoinColumn(name = "accountId")
    private Account theAccount;
}
