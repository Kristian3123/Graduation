package com.inf.Graduation.web.api;

import com.inf.Graduation.data.dto.Review.CreateReviewDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Review.UpdateReviewDto;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;
import com.inf.Graduation.service.ReviewService;
import com.inf.Graduation.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewApiController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;


    @GetMapping("/reviews")
    public List<ReviewDto> getReview(){

        return reviewService.getReview();
    }


    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable long id) {
        return this.reviewService.getReviewById(id);
    }

    @PostMapping
    public CreateReviewDto createReview(@RequestBody CreateReviewDto createReviewDto) {
        return this.reviewService.createReview(createReviewDto);
    }

    @PutMapping("/{id}")
    public UpdateReviewDto updateReview(@PathVariable long id, @RequestBody UpdateReviewDto updateReviewDto) {
        // Map updated data from the DTO to the service layer
        return this.reviewService.updateReview(id, updateReviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable long id) {
        this.reviewService.deleteReview(id);
    }

}
