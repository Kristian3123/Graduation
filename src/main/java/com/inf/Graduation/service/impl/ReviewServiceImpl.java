package com.inf.Graduation.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inf.Graduation.data.dto.Review.CreateReviewDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Review.UpdateReviewDto;

import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.data.repository.ReviewRepository;
import com.inf.Graduation.exception.ReviewNotFoundException;
import com.inf.Graduation.service.ReviewService;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getReview() {
        return reviewRepository.findAll().stream()
                .map(this::convertToReviewDto)
                .collect(Collectors.toList());
    }
    private ReviewDto convertToReviewDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public ReviewDto getReviewById(long id) {
        //return null;
        return modelMapper.map(reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Invalid review Id:" + id)), ReviewDto.class);
    }

    //!!!! Ako dode problem s mapvaneto
   //@Override
   //public List<ReviewDto> getReviewBy(String facultyNumber) {
   //    //return this.studentsRepository.findAll(facultyNumber);
   //    // return List.of();
   //    return reviewRepository.findStudentsByFacultyNumber(facultyNumber).stream()
   //            .map(this::convertToStudentsDto)
   //            .collect(Collectors.toList());
   //}

  // @Override
  // public CreateReviewDto createReview(CreateReviewDto create) {
  //     // Създаване на нов обект Review
  //     Review review = new Review();

  //     // Прехвърляне на стойности от DTO към Entity
  //     review.setUploadDate(create.getUploadDate());
  //     review.setText(create.getText());

  //     // Прехвърляне на заключенията (възможно е да се наложи да бъдат преобразувани или обработени)
  //     List<ReviewConclusion> conclusions = create.getConclusion(); // Получаваме списъка с заключения от DTO
  //     review.setConclusion(conclusions); // Присвояваме заключенията към обекта Review

  //     // Присвояваме рецензент (преподавател) на рецензията
  //     Teachers reviewer = create.getReviewerId(); // Получаваме преподавателския обект от DTO
  //     review.setReviewer(reviewer);

  //     // Присвояваме тезата на рецензията
  //     Thesis thesis = create.getThesisId(); // Получаваме тезата от DTO
  //     review.setThesis(thesis);

  //     // Записване на новия обект в базата данни
  //     review = reviewRepository.save(review);

  //     // Връщане на DTO за създадения обект
  //     CreateReviewDto resultDto = new CreateReviewDto();
  //     resultDto.setUploadDate(review.getUploadDate());
  //     resultDto.setText(review.getText());
  //     resultDto.setConclusion(review.getConclusion());
  //     resultDto.setReviewerId(review.getReviewer()); // Целия преподавателски обект
  //     resultDto.setThesisId(review.getThesis()); // Цялата теза

  //     return resultDto;
  // }


    //@Override
    //public CreateReviewDto createReview(CreateReviewDto reviewStudent) {
    //    //return null;
    //    return modelMapper
    //            .map(reviewRepository.save(modelMapper.map(reviewStudent, Review.class)), CreateReviewDto.class);
    //}
    @Override
    public CreateReviewDto createReview(CreateReviewDto create) {

        Review review = new Review();

        review.setUploadDate(create.getUploadDate());
        review.setText(create.getText());

    //    ReviewConclusion conclusion = create.getConclusion();
    //    if (conclusion != null) {
    //        review.setConclusion(conclusion);
    //    }
        review.setConclusion(create.getConclusion());


    //    Teachers reviewer = create.getReviewerIds();
    //    if (reviewer == null) {
    //        throw new IllegalArgumentException("ReviewerIds cannot be null");
    //    }
    //    review.setReviewerIds(reviewer);
        review.setReviewerIds(create.getReviewerIds());


     //   Thesis thesis = create.getThesis();
     //   if (thesis == null) {
     //       throw new IllegalArgumentException("Thesis cannot be null");
     //   }
     //   review.setThesis(thesis);
        // Thesis thesis = createReviewDto.getThesisId();
        // review.setThesis(thesis);

        review = reviewRepository.save(review);


        CreateReviewDto resultDto = new CreateReviewDto();
        resultDto.setUploadDate(review.getUploadDate());
        resultDto.setText(review.getText());
        resultDto.setConclusion(review.getConclusion());
        resultDto.setReviewerIds(review.getReviewerIds());
    //    resultDto.setThesis(review.getThesis());

        return resultDto;
    }


    @Override
    public UpdateReviewDto updateReview(long id, UpdateReviewDto update) {
        // Извличане на съществуващия обект Review от базата данни
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with ID " + id + " not found"));

        // Актуализиране на полетата от DTO
        if (update.getUploadDate() != null) {
            review.setUploadDate(update.getUploadDate());
        }

        if (update.getText() != null) {
            review.setText(update.getText());
        }

        if (update.getConclusion() != null) {
            review.setConclusion(update.getConclusion());
        }

        if (update.getReviewerIds() != null) {
            review.setReviewerIds(update.getReviewerIds());
        }

//        if (update.getThesis() != null) {
//            review.setThesis(update.getThesis());
//        }

        review = reviewRepository.save(review);

        UpdateReviewDto resultDto = new UpdateReviewDto();
        resultDto.setId(review.getId());
        resultDto.setUploadDate(review.getUploadDate());
        resultDto.setText(review.getText());
        resultDto.setConclusion(review.getConclusion());
        resultDto.setReviewerIds(review.getReviewerIds());
//        resultDto.setThesis(review.getThesis());

        return resultDto;
    }



    //@Override
    //public UpdateReviewDto updateReview(long id, UpdateReviewDto updateReview) {
    //    Review review = modelMapper.map(getReviewById(id), Review.class);
    //    review.setUploadDate(updateReview.getUploadDate());
    //    review.setText(updateReview.getText());
//
    //    review.setConclusion(updateReview.getConclusion());
//
    //    review.setReviewerIds(updateReview.getReviewerId());
    //    //review.setThesisIds(updateReview.getThesisId());
//
//
    //    return modelMapper.map(reviewRepository.save(review), UpdateReviewDto.class);
    //}

    @Override
    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }

}
