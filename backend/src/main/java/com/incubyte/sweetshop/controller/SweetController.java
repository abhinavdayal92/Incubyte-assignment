package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.dto.SearchRequest;
import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.entity.Sweet;
import com.incubyte.sweetshop.service.SweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController {

    private final SweetService sweetService;

    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        List<Sweet> sweets = sweetService.getAllSweets();
        return ResponseEntity.ok(sweets);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Sweet>> searchSweets(@ModelAttribute SearchRequest searchRequest) {
        List<Sweet> sweets = sweetService.searchSweets(searchRequest);
        return ResponseEntity.ok(sweets);
    }

    @PostMapping
    public ResponseEntity<Sweet> createSweet(@Valid @RequestBody SweetRequest request) {
        Sweet sweet = sweetService.createSweet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sweet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sweet> updateSweet(
            @PathVariable Long id,
            @Valid @RequestBody SweetRequest request
    ) {
        Sweet sweet = sweetService.updateSweet(id, request);
        return ResponseEntity.ok(sweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSweet(@PathVariable Long id) {
        sweetService.deleteSweet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Sweet> purchaseSweet(@PathVariable Long id) {
        Sweet sweet = sweetService.purchaseSweet(id);
        return ResponseEntity.ok(sweet);
    }

    @PostMapping("/{id}/restock")
    public ResponseEntity<Sweet> restockSweet(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request
    ) {
        Integer quantity = request.get("quantity");
        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be a positive number");
        }
        Sweet sweet = sweetService.restockSweet(id, quantity);
        return ResponseEntity.ok(sweet);
    }
}

