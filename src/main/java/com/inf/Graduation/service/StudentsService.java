package com.inf.Graduation.service;

import com.inf.Graduation.data.dto.GraduationDTO;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;

import java.time.LocalDate;
import java.util.List;

public interface StudentsService {


    List<StudentsDto> getStudents();

    List<StudentsDto> getStudentsByFacultyNumber(String facultyNumber);

    StudentsDto getStudentById(long id);
    // StudentDto getStudentByFacultyNumber(String facultyNumber);
    CreateStudentsDto createStudent(CreateStudentsDto create);

    UpdateStudentsDto updateStudent(long id, UpdateStudentsDto update);

    // UpdateStudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);
    void deleteStudent(long id);

    List<GraduationDTO> findGraduatesByPeriod(LocalDate startDate, LocalDate endDate);
}
