package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.entity.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SweetServiceTest {

    @Mock
    private SweetRepository sweetRepository;

    @InjectMocks
    private SweetService sweetService;

    private Sweet testSweet;
    private SweetRequest sweetRequest;

    @BeforeEach
    void setUp() {
        testSweet = new Sweet();
        testSweet.setId(1L);
        testSweet.setName("Chocolate Bar");
        testSweet.setCategory("Chocolate");
        testSweet.setPrice(new BigDecimal("5.99"));
        testSweet.setQuantity(10);

        sweetRequest = new SweetRequest();
        sweetRequest.setName("Chocolate Bar");
        sweetRequest.setCategory("Chocolate");
        sweetRequest.setPrice(new BigDecimal("5.99"));
        sweetRequest.setQuantity(10);
    }

    @Test
    void testGetAllSweets() {
        // Given
        List<Sweet> sweets = Arrays.asList(testSweet);
        when(sweetRepository.findAll()).thenReturn(sweets);

        // When
        List<Sweet> result = sweetService.getAllSweets();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Chocolate Bar", result.get(0).getName());
        verify(sweetRepository, times(1)).findAll();
    }

    @Test
    void testGetSweetById_Success() {
        // Given
        when(sweetRepository.findById(1L)).thenReturn(Optional.of(testSweet));

        // When
        Sweet result = sweetService.getSweetById(1L);

        // Then
        assertNotNull(result);
        assertEquals("Chocolate Bar", result.getName());
        verify(sweetRepository, times(1)).findById(1L);
    }

    @Test
    void testGetSweetById_NotFound() {
        // Given
        when(sweetRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> sweetService.getSweetById(1L));
    }

    @Test
    void testCreateSweet() {
        // Given
        when(sweetRepository.save(any(Sweet.class))).thenReturn(testSweet);

        // When
        Sweet result = sweetService.createSweet(sweetRequest);

        // Then
        assertNotNull(result);
        assertEquals("Chocolate Bar", result.getName());
        verify(sweetRepository, times(1)).save(any(Sweet.class));
    }

    @Test
    void testUpdateSweet_Success() {
        // Given
        Sweet updatedSweet = new Sweet();
        updatedSweet.setId(1L);
        updatedSweet.setName("Updated Chocolate Bar");
        updatedSweet.setCategory("Chocolate");
        updatedSweet.setPrice(new BigDecimal("6.99"));
        updatedSweet.setQuantity(15);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(testSweet));
        when(sweetRepository.save(any(Sweet.class))).thenReturn(updatedSweet);

        SweetRequest updateRequest = new SweetRequest();
        updateRequest.setName("Updated Chocolate Bar");
        updateRequest.setCategory("Chocolate");
        updateRequest.setPrice(new BigDecimal("6.99"));
        updateRequest.setQuantity(15);

        // When
        Sweet result = sweetService.updateSweet(1L, updateRequest);

        // Then
        assertNotNull(result);
        assertEquals("Updated Chocolate Bar", result.getName());
        assertEquals(new BigDecimal("6.99"), result.getPrice());
        verify(sweetRepository, times(1)).findById(1L);
        verify(sweetRepository, times(1)).save(any(Sweet.class));
    }

    @Test
    void testDeleteSweet_Success() {
        // Given
        when(sweetRepository.findById(1L)).thenReturn(Optional.of(testSweet));
        doNothing().when(sweetRepository).delete(any(Sweet.class));

        // When
        sweetService.deleteSweet(1L);

        // Then
        verify(sweetRepository, times(1)).findById(1L);
        verify(sweetRepository, times(1)).delete(testSweet);
    }

    @Test
    void testPurchaseSweet_Success() {
        // Given
        Sweet sweetAfterPurchase = new Sweet();
        sweetAfterPurchase.setId(1L);
        sweetAfterPurchase.setName("Chocolate Bar");
        sweetAfterPurchase.setCategory("Chocolate");
        sweetAfterPurchase.setPrice(new BigDecimal("5.99"));
        sweetAfterPurchase.setQuantity(9);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(testSweet));
        when(sweetRepository.save(any(Sweet.class))).thenReturn(sweetAfterPurchase);

        // When
        Sweet result = sweetService.purchaseSweet(1L);

        // Then
        assertNotNull(result);
        assertEquals(9, result.getQuantity());
        verify(sweetRepository, times(1)).findById(1L);
        verify(sweetRepository, times(1)).save(any(Sweet.class));
    }

    @Test
    void testPurchaseSweet_OutOfStock() {
        // Given
        testSweet.setQuantity(0);
        when(sweetRepository.findById(1L)).thenReturn(Optional.of(testSweet));

        // When & Then
        assertThrows(RuntimeException.class, () -> sweetService.purchaseSweet(1L));
        verify(sweetRepository, times(1)).findById(1L);
        verify(sweetRepository, never()).save(any(Sweet.class));
    }

    @Test
    void testRestockSweet_Success() {
        // Given
        Sweet sweetAfterRestock = new Sweet();
        sweetAfterRestock.setId(1L);
        sweetAfterRestock.setName("Chocolate Bar");
        sweetAfterRestock.setCategory("Chocolate");
        sweetAfterRestock.setPrice(new BigDecimal("5.99"));
        sweetAfterRestock.setQuantity(25);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(testSweet));
        when(sweetRepository.save(any(Sweet.class))).thenReturn(sweetAfterRestock);

        // When
        Sweet result = sweetService.restockSweet(1L, 15);

        // Then
        assertNotNull(result);
        assertEquals(25, result.getQuantity());
        verify(sweetRepository, times(1)).findById(1L);
        verify(sweetRepository, times(1)).save(any(Sweet.class));
    }
}

