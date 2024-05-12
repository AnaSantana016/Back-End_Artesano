package com.pdigs.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;
    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "lastViewed")
    private Long lastViewed;
    @Size(max = 250)
    @Column(name = "image")
    private String image;
    @Size(max = 255)
    @Column(name = "favorites")
    private String favorites;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creation_time) {
        this.creationTime = creation_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(Long lastViewed) {
        this.lastViewed = lastViewed;
    }

    public String getImage() {
        return image;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }
}
