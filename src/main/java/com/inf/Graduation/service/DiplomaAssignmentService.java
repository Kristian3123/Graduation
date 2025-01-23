package com.inf.Graduation.service;

import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.UpdateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.DiplomaAssignment;

import java.util.List;

public interface DiplomaAssignmentService {

    List<DiplomaAssignmentDto> getDiplomaAssignment();

    //List<DiplomaAssignmentDto> getStudentsByFacultyNumber(String facultyNumber);

    DiplomaAssignmentDto getDiplomaAssignmentById(long id);
    // StudentDto getStudentByFacultyNumber(String facultyNumber);
    CreateDiplomaAssignmentDto createDiplomaAssignment(CreateDiplomaAssignmentDto create);

    UpdateDiplomaAssignmentDto updateDiplomaAssignment(long id, UpdateDiplomaAssignmentDto update);

    // UpdateStudentDto updateStudent(long id, UpdateStudentDto updateStudentDto);
    void deleteDiplomaAssignment(long id);



   // List<DiplomaAssignment> findByApprovedTrue();
   // List<DiplomaAssignment> findByTitleContaining(String keyword);
   // List<DiplomaAssignment> findByTeacherIdAndApprovedTrue(Long teacherId);
// List<DiplomaAssignmentDto> getApprovedAssignments();
//  void addAssignment(DiplomaAssignmentDto assignmentDTO);
//  List<DiplomaAssignmentDto> getAllAssignments();
   List<DiplomaAssignment> findAllApprovedThesisAssignments(ApplicationStatus approved);
    List<DiplomaAssignment> findThesisByKeyword(String keyword);
    List<DiplomaAssignment> findThesisBySupervisorAndStatus(Long professorId, ApplicationStatus approved);
}
