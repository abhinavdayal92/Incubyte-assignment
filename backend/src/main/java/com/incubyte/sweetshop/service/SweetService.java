package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.dto.SearchRequest;
import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.entity.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetService {

    private final SweetRepository sweetRepository;

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet getSweetById(Long id) {
        return sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + id));
    }

    public List<Sweet> searchSweets(SearchRequest searchRequest) {
        return sweetRepository.searchSweets(
                searchRequest.getName(),
                searchRequest.getCategory(),
                searchRequest.getMinPrice(),
                searchRequest.getMaxPrice()
        );
    }

    @Transactional
    public Sweet createSweet(SweetRequest request) {
        Sweet sweet = new Sweet();
        sweet.setName(request.getName());
        sweet.setCategory(request.getCategory());
        sweet.setPrice(request.getPrice());
        sweet.setQuantity(request.getQuantity());
        return sweetRepository.save(sweet);
    }

    @Transactional
    public Sweet updateSweet(Long id, SweetRequest request) {
        Sweet sweet = getSweetById(id);
        sweet.setName(request.getName());
        sweet.setCategory(request.getCategory());
        sweet.setPrice(request.getPrice());
        sweet.setQuantity(request.getQuantity());
        return sweetRepository.save(sweet);
    }

    @Transactional
    public void deleteSweet(Long id) {
        Sweet sweet = getSweetById(id);
        sweetRepository.delete(sweet);
    }

    @Transactional
    public Sweet purchaseSweet(Long id) {
        Sweet sweet = getSweetById(id);
        if (sweet.getQuantity() <= 0) {
            throw new RuntimeException("Sweet is out of stock");
        }
        sweet.setQuantity(sweet.getQuantity() - 1);
        return sweetRepository.save(sweet);
    }

    @Transactional
    public Sweet restockSweet(Long id, Integer quantity) {
        Sweet sweet = getSweetById(id);
        sweet.setQuantity(sweet.getQuantity() + quantity);
        return sweetRepository.save(sweet);
    }
}

