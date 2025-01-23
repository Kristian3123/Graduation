package com.inf.Graduation.serviceimpl.service;

import com.inf.Graduation.data.dto.Review.CreateReviewDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Review.UpdateReviewDto;
import com.inf.Graduation.data.entity.Review;

import com.inf.Graduation.data.repository.ReviewRepository;
import com.inf.Graduation.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReview() {
        Review review = new Review();
        review.setId(1L);
        review.setText("Great thesis");

        when(reviewRepository.findAll()).thenReturn(Collections.singletonList(review));

        List<ReviewDto> reviews = reviewService.getReview();
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals("Great thesis", reviews.get(0).getText());

        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetReviewById() {
        Review review = new Review();
        review.setId(1L);
        review.setText("Great thesis");

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewDto reviewDto = reviewService.getReviewById(1L);
        assertNotNull(reviewDto);
        assertEquals(1L, reviewDto.getId());
        assertEquals("Great thesis", reviewDto.getText());

        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateReview() {
        CreateReviewDto createDto = new CreateReviewDto();
        createDto.setText("Excellent work");

        Review review = new Review();
        review.setId(1L);
        review.setText("Excellent work");

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        CreateReviewDto createdReview = reviewService.createReview(createDto);
        assertNotNull(createdReview);
        assertEquals("Excellent work", createdReview.getText());

        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testUpdateReview() {
        UpdateReviewDto updateDto = new UpdateReviewDto();
        updateDto.setText("Updated comment");

        Review existingReview = new Review();
        existingReview.setId(1L);
        existingReview.setText("Old comment");

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(existingReview);

        UpdateReviewDto updatedReview = reviewService.updateReview(1L, updateDto);
        assertNotNull(updatedReview);
        assertEquals("Updated comment", updatedReview.getText());

        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testDeleteReview() {
        Review review = new Review();
        review.setId(1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).deleteById(1L);

        assertDoesNotThrow(() -> reviewService.deleteReview(1L));

        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }
}
