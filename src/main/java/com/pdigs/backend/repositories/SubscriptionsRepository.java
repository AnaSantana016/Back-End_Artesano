package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Subscriptions;
import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionsRepository extends CrudRepository<Subscriptions, Long> {
    Iterable<Subscriptions> getSubscriptionsBySuscriber(User suscriber);
    Iterable<Subscriptions> getSubscriptionsBySubscribedTo(User suscribedTo);
    Long countBySuscribedTo(User suscriberTo);
    Long countBySuscriber(User suscriber);
    void deleteById(Long id);
    void deleteBySuscriberAndAndSubscribedTo(User suscriber, User suscribedTo);
}

