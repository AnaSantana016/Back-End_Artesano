package com.pdigs.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_who_liked")
    private User userWhoLiked;

    @ManyToOne
    @JoinColumn(name = "product_liked")
    private Product productLiked;

    public Integer getId() {
        return id;
    }

    public User getUserWhoLiked() {
        return userWhoLiked;
    }

    public Product getProductLiked() {
        return productLiked;
    }

    public void setProductLiked(Product followed) {
        this.productLiked = followed;
    }

    public void setUserWhoLiked(User follower) {
        this.userWhoLiked = follower;
    }
}