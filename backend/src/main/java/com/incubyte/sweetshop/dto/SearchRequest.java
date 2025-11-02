package com.incubyte.sweetshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchRequest {
    private String name;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}

