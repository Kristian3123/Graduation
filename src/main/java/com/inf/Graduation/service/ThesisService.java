package com.inf.Graduation.service;

import com.inf.Graduation.data.dto.Thesis.CreateThesisDto;
import com.inf.Graduation.data.dto.Thesis.ThesisDto;
import com.inf.Graduation.data.dto.Thesis.UpdateThesisDto;
import com.inf.Graduation.data.entity.ReviewConclusion;

import java.util.List;

public interface ThesisService {
    List<ThesisDto> getThesis();

    //List<ThesisDefenseDto> getStudentsByFacultyNumber(String facultyNumber);

    ThesisDto getThesisById(long id);
    // StudentDto getStudentByFacultyNumber(String facultyNumber);
    CreateThesisDto createThesis(CreateThesisDto create);

    UpdateThesisDto updateThesis(long id, UpdateThesisDto update);

    // UpdateStudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);
    void deleteThesis(long id);

    int countNegativeReviews(ReviewConclusion negativeReview);
}
