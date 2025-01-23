package com.inf.Graduation.service;

import com.inf.Graduation.data.dto.ThesisDefense.CreateThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.UpdateThesisDefenseDto;
import com.inf.Graduation.data.entity.ThesisDefense;

import java.time.LocalDate;
import java.util.List;

public interface ThesisDefenseService {


    List<ThesisDefenseDto> getThesisDefense();

    //List<ThesisDefenseDto> getStudentsByFacultyNumber(String facultyNumber);

    ThesisDefenseDto getThesisDefenseById(long id);
    // StudentDto getStudentByFacultyNumber(String facultyNumber);
    CreateThesisDefenseDto createThesisDefense(CreateThesisDefenseDto create);

    UpdateThesisDefenseDto updateThesisDefense(long id, UpdateThesisDefenseDto update);

    // UpdateStudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);
    void deleteThesisDefense(long id);

    List<ThesisDefense> findDefendedThesesByGrade(double minGrade, double maxGrade);

    double findAverageDefensesByPeriod(LocalDate startDate, LocalDate endDate);
    int countSuccessfulDefensesBySupervisor(Long professorId);


}
