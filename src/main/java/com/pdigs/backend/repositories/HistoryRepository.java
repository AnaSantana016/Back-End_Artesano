package com.pdigs.backend.repositories;

import com.pdigs.backend.models.PurchaseHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;

public interface HistoryRepository extends CrudRepository<PurchaseHistory, Long> {

    Iterable<PurchaseHistory> findByBuyer_Id(Integer userId, Sort sort);

    Iterable<PurchaseHistory> findByBuyerId(Integer userId, Sort sort);
}
