package com.inf.Graduation.service;

import com.inf.Graduation.data.dto.Review.CreateReviewDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Review.UpdateReviewDto;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;
import com.inf.Graduation.data.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getReview();

   // List<ReviewDto> getReviewBy(String facultyNumber);

    ReviewDto getReviewById(long id);
    // StudentDto getStudentByFacultyNumber(String facultyNumber);
    CreateReviewDto createReview(CreateReviewDto create);

    UpdateReviewDto updateReview(long id, UpdateReviewDto update);

    // UpdateStudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);
    void deleteReview(long id);
}
