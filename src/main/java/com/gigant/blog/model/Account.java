package com.gigant.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Entity
@Table(name = "user_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String email;
    private String profileImageLink;

    @OneToMany(mappedBy = "theAccount")
    private List<UserPost> userPosts = new ArrayList<>();


}
