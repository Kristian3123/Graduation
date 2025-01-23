package com.inf.Graduation.web.api;

import com.inf.Graduation.data.dto.GraduationDTO;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;
import com.inf.Graduation.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentsApiController {
    private final StudentsService studentsService;
    private final ModelMapper modelMapper;
   //public StudentsController(StudentsService studentsService) {
   //    this.studentsService = studentsService;

   //}


 //  @GetMapping("/procedures")
 //  @PreAuthorize("hasRole('STUDENT')")
 //  public ResponseEntity<List<DiplomaAssignment>> getStudentProcedures(Principal principal) {
 //      // Вземете задания, свързани със студента
 //      return ResponseEntity.ok(studentsService.getStudentProcedures(principal.getName()));
 //  }

 //  @PostMapping("/theses")
 //  @PreAuthorize("hasRole('STUDENT')")
 //  public ResponseEntity<Void> uploadThesis(@RequestBody ThesisDto thesisDto, Principal principal) {
 //      studentsService.uploadThesis(thesisDto, principal.getName());
 //      return ResponseEntity.status(HttpStatus.CREATED).build();
 //  }



   @GetMapping()
   public List<StudentsDto> getStudents(){

       return studentsService.getStudents();
   }


    @GetMapping("/{id}")
    public StudentsDto getStudentsById(@PathVariable long id) {
        return this.studentsService.getStudentById(id);
    }

    @PostMapping
    public CreateStudentsDto createStudents(@RequestBody CreateStudentsDto createStudentsDto) {
        return this.studentsService.createStudent(createStudentsDto);
    }

    @PutMapping("/{id}")
    public UpdateStudentsDto updateStudents(@PathVariable long id, @RequestBody UpdateStudentsDto updateStudentsDto) {
        // Map updated data from the DTO to the service layer
        return this.studentsService.updateStudent(id, updateStudentsDto);
    }


    //@GetMapping("/by-max-number-of-students/{numberOfStudents}")
    //public List<StudentsDto> getSchoolsByMaxNumberOfStudents(@PathVariable int numberOfStudents) {
    //    return this.studentsService.getSchoolsByMaxNumberOfStudents(numberOfStudents);
    //}

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        this.studentsService.deleteStudent(id);
    }



    @GetMapping("/graduates")
    public ResponseEntity<List<GraduationDTO>> getGraduatesByPeriod(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<GraduationDTO> graduates = studentsService.findGraduatesByPeriod(startDate, endDate);
        return new ResponseEntity<>(graduates, HttpStatus.OK);
    }


}
