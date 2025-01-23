package com.inf.Graduation.web.api;


import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.UpdateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.service.DiplomaAssignmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/DiplomaAssignment")
public class DiplomaAssignmentApiController {

    private final DiplomaAssignmentService diplomaAssignmentService;
    private final ModelMapper modelMapper;


    @GetMapping("/DiplomaAssignments")
    public List<DiplomaAssignmentDto> getDiplomaAssignment(){
        return diplomaAssignmentService.getDiplomaAssignment();
    }


    @GetMapping("/{id}")
    public DiplomaAssignmentDto getDiplomaAssignmentById(@PathVariable long id) {
        return this.diplomaAssignmentService.getDiplomaAssignmentById(id);
    }

    @PostMapping
    public CreateDiplomaAssignmentDto createDiplomaAssignment(@RequestBody CreateDiplomaAssignmentDto createDiplomaAssignmentDto) {
        return this.diplomaAssignmentService.createDiplomaAssignment(createDiplomaAssignmentDto);
    }

    @PutMapping("/{id}")
    public UpdateDiplomaAssignmentDto updateDiplomaAssignment(@PathVariable long id, @RequestBody UpdateDiplomaAssignmentDto updateDiplomaAssignmentDto) {
        // Map updated data from the DTO to the service layer
        return this.diplomaAssignmentService.updateDiplomaAssignment(id, updateDiplomaAssignmentDto);
    }


    //@GetMapping("/by-max-number-of-students/{numberOfStudents}")
    //public List<StudentsDto> getSchoolsByMaxNumberOfStudents(@PathVariable int numberOfStudents) {
    //    return this.studentsService.getSchoolsByMaxNumberOfStudents(numberOfStudents);
    //}
    @DeleteMapping("/{id}")
    public void deleteDiplomaAssignment(@PathVariable long id) {
        this.diplomaAssignmentService.deleteDiplomaAssignment(id);
    }

    @GetMapping("/approved-theses")
    public List<DiplomaAssignment> getAllApprovedThesisAssignments(@RequestParam ApplicationStatus approved) {
        return diplomaAssignmentService.findAllApprovedThesisAssignments(approved);
    }

    @GetMapping("/thesis-by-keyword")
    public List<DiplomaAssignment> getThesisByKeyword(@RequestParam String keyword) {
        return diplomaAssignmentService.findThesisByKeyword(keyword);
    }

    @GetMapping("/thesis-by-supervisor")
    public List<DiplomaAssignment> getThesisBySupervisorAndStatus(@RequestParam Long professorId, @RequestParam ApplicationStatus approved) {
        return diplomaAssignmentService.findThesisBySupervisorAndStatus(professorId, approved);
    }
}

