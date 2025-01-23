package com.inf.Graduation.web.api;

import com.inf.Graduation.data.dto.Teachers.CreateTeachersDto;
import com.inf.Graduation.data.dto.Teachers.TeachersDto;
import com.inf.Graduation.data.dto.Teachers.UpdateTeachersDto;
import com.inf.Graduation.service.TeachersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeachersApiController {
    private final TeachersService teachersService;
    private final ModelMapper modelMapper;
   //public StudentsController(StudentsService studentsService) {
   //    this.studentsService = studentsService;
   //}

   @GetMapping()//"/teachers"
   public List<TeachersDto> getTeachers(){

       return teachersService.getTeachers();
   }


    @GetMapping("/{id}")
    public TeachersDto getTeacherById(@PathVariable long id) {
        return this.teachersService.getTeacherById(id);
    }

    @PostMapping
    public CreateTeachersDto createTeachers(@RequestBody CreateTeachersDto createTeachersDto) {
        return this.teachersService.createTeacher(createTeachersDto);
    }

    @PutMapping("/{id}")
    public UpdateTeachersDto updateStudents(@PathVariable long id, @RequestBody UpdateTeachersDto updateTeachersDto) {
        // Map updated data from the DTO to the service layer
        return this.teachersService.updateTeacher(id, updateTeachersDto);
    }


    //@GetMapping("/by-max-number-of-students/{numberOfStudents}")
    //public List<StudentsDto> getSchoolsByMaxNumberOfStudents(@PathVariable int numberOfStudents) {
    //    return this.studentsService.getSchoolsByMaxNumberOfStudents(numberOfStudents);
    //}

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable long id) {
        this.teachersService.deleteTeacher(id);
    }

}
