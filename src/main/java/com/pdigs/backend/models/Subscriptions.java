package com.pdigs.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "suscriber_id")
    private User suscriber;

    @ManyToOne
    @JoinColumn(name = "suscribed_to_id")
    private User suscribedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribed_to_id")
    private User subscribedTo;

    public User getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(User subscribedTo) {
        this.subscribedTo = subscribedTo;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public Integer getId() {
        return id;
    }

    public User getSuscriber() {
        return suscriber;
    }

    public User getSuscribedTo() {
        return suscribedTo;
    }

    public void setSuscribedTo(User followed) {
        this.suscribedTo = followed;
    }

    public void setSuscriber(User follower) {
        this.suscriber = follower;
    }
}



