package com.inf.Graduation.service;

import com.inf.Graduation.data.dto.Teachers.CreateTeachersDto;
import com.inf.Graduation.data.dto.Teachers.TeachersDto;
import com.inf.Graduation.data.dto.Teachers.UpdateTeachersDto;

import java.util.List;

public interface TeachersService {


    List<TeachersDto> getTeachers();

    List<TeachersDto> getTeachersByEmail(String email);

    TeachersDto getTeacherById(long id);
    // StudentDto getStudentByFacultyNumber(String facultyNumber);

    CreateTeachersDto createTeacher(CreateTeachersDto create);

    UpdateTeachersDto updateTeacher(long id, UpdateTeachersDto update);

    // UpdateStudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);

    void deleteTeacher(long id);
}
