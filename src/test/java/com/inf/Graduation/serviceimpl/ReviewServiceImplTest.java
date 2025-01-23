package com.inf.Graduation.serviceimpl;


import com.inf.Graduation.data.dto.Review.CreateReviewDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Review.UpdateReviewDto;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.data.repository.ReviewRepository;
import com.inf.Graduation.exception.ReviewNotFoundException;

import com.inf.Graduation.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReview() {
        // Arrange
        Review review = new Review();
        review.setId(1L);
        review.setText("Sample review text");
        review.setUploadDate(LocalDate.now());

        when(reviewRepository.findAll()).thenReturn(List.of(review));

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setText("Sample review text");
        reviewDto.setUploadDate(LocalDate.now());

        when(modelMapper.map(review, ReviewDto.class)).thenReturn(reviewDto);

        // Act
        List<ReviewDto> result = reviewService.getReview();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sample review text", result.get(0).getText());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetReviewById() {
        // Arrange
        Review review = new Review();
        review.setId(1L);
        review.setText("Sample review text");

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setText("Sample review text");

        when(modelMapper.map(review, ReviewDto.class)).thenReturn(reviewDto);

        // Act
        ReviewDto result = reviewService.getReviewById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Sample review text", result.getText());
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateReview() {
        // Arrange
        CreateReviewDto createReviewDto = new CreateReviewDto();
        createReviewDto.setText("New review text");
        createReviewDto.setUploadDate(LocalDate.now());

        Review review = new Review();
        review.setText("New review text");
        review.setUploadDate(LocalDate.now());

        ReviewConclusion conclusion = ReviewConclusion.NEGATIVE;
        review.setConclusion(conclusion);

        Teachers reviewer = new Teachers();
        review.setReviewerIds(reviewer);

        Thesis thesis = new Thesis();
        review.setThesis(thesis);

        when(reviewRepository.save(any(Review.class))).thenReturn(review);


        CreateReviewDto result = reviewService.createReview(createReviewDto);


        assertNotNull(result);
        assertEquals("New review text", result.getText());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testUpdateReview() {

        Review existingReview = new Review();
        existingReview.setId(1L);
        existingReview.setText("Existing review text");

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));

        UpdateReviewDto updateReviewDto = new UpdateReviewDto();
        updateReviewDto.setText("Updated review text");

        existingReview.setText("Updated review text");

        when(reviewRepository.save(existingReview)).thenReturn(existingReview);


        UpdateReviewDto result = reviewService.updateReview(1L, updateReviewDto);


        assertNotNull(result);
        assertEquals("Updated review text", result.getText());
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(existingReview);
    }

    @Test
    void testDeleteReview() {

        long reviewId = 1L;

        doNothing().when(reviewRepository).deleteById(reviewId);


        reviewService.deleteReview(reviewId);


        verify(reviewRepository, times(1)).deleteById(reviewId);
    }

    @Test
    void testGetReviewById_NotFound() {

        long invalidId = 999L;

        when(reviewRepository.findById(invalidId)).thenReturn(Optional.empty());


        assertThrows(ReviewNotFoundException.class, () -> reviewService.getReviewById(invalidId));
    }

    @Test
    void testCreateReview_ExceptionHandling() {

        CreateReviewDto createReviewDto = new CreateReviewDto();
        createReviewDto.setText("New review with error");


        when(reviewRepository.save(any(Review.class))).thenThrow(new RuntimeException("Database error"));


        assertThrows(RuntimeException.class, () -> reviewService.createReview(createReviewDto));
    }

    @Test
    void testUpdateReview_NotFound() {

        long invalidId = 999L;
        UpdateReviewDto updateReviewDto = new UpdateReviewDto();
        updateReviewDto.setText("Updated review text");

        when(reviewRepository.findById(invalidId)).thenReturn(Optional.empty());


        assertThrows(ReviewNotFoundException.class, () -> reviewService.updateReview(invalidId, updateReviewDto));
    }
}
