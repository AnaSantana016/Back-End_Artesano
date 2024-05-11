package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.Subscriptions;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionsRepository extends CrudRepository<Subscriptions, Long> {
    Iterable<Follows> getSubscriptionsBy(Integer suscriberId);
    Iterable<Follows> getFollowsByFollowed(Integer suscribedToID);
    Integer countBySuscribedTo(Integer suscriberId);
    Integer countBySuscriber(Integer suscribedToId);
}

