package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.PurchaseHistory;
import com.pdigs.backend.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.Float.parseFloat;

@RestController
@RequestMapping(path = "/history")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping
    public Iterable<PurchaseHistory> getAllHistory() {
        return historyRepository.findAll();
    }

    @GetMapping(params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<HistoryProduct> getHistory(@RequestParam("userId") Integer userId,
                                                  @RequestParam(value = "filterField", required = false) String filterField,
                                                  @RequestParam(value = "sortOrder", required = false) String sortOrder) {

        Sort sort = sortOrder == null || sortOrder.isEmpty() || sortOrder.equals("asc")
                ? Sort.by(Sort.Direction.ASC, filterField != null && !filterField.isEmpty() ? filterField : "date")
                : Sort.by(Sort.Direction.DESC, filterField != null && !filterField.isEmpty() ? filterField : "date");

        Iterable<PurchaseHistory> histories = historyRepository.findByBuyer_Id(userId, sort);
        List<HistoryProduct> historyProducts = new ArrayList<>();

        for (PurchaseHistory history : histories) {
            historyProducts.add(new HistoryProduct(history.getProduct(), history.getDate(), history.getQuantity()));
        }
        return historyProducts;
    }

    @PostMapping
    public ResponseEntity<String> addHistory(@RequestBody PurchaseHistory history) {
        historyRepository.save(history);
        return ResponseEntity.ok("Product added successfully to history");
    }
}