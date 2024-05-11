package com.pdigs.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private User followed;

    public Integer getId() {
        return id;
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}