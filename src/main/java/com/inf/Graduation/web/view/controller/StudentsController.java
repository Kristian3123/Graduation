package com.inf.Graduation.web.view.controller;

import com.inf.Graduation.data.dto.GraduationDTO;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;
import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.service.*;
import com.inf.Graduation.web.view.model.Student.CreateStudentsViewModel;
import com.inf.Graduation.web.view.model.Student.StudentsViewModel;
import com.inf.Graduation.web.view.model.Student.UpdateStudentsViewModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
//@PreAuthorize("hasAuthority('STUDENT')")
public class StudentsController {

    private final StudentsService studentService;
    private final DiplomaAssignmentService supervisedAssignmentsService;
    private final ThesisDefenseService defensesService;
    private final ThesisService thesisService;
    private final ModelMapper modelMapper;


    @GetMapping
    public String getStudents(Model model) {
        final List<StudentsViewModel> student = studentService.getStudents()
                .stream()
                .map(this::convertToStudentsViewModel)
                .collect(Collectors.toList());
        model.addAttribute("students", student);
        return "/students/students.html";
    }

    @GetMapping("/create-student")
    public String showCreateStudentsForm(Model model) {
        model.addAttribute("student", new CreateStudentsViewModel());
        model.addAttribute("assignmentsIds", supervisedAssignmentsService.getDiplomaAssignment());
        model.addAttribute("thesisIds", thesisService.getThesis());
        model.addAttribute("defenses", defensesService.getThesisDefense());

        return "/students/create-student";
    }
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") CreateStudentsViewModel student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
           model.addAttribute("assignmentIds", supervisedAssignmentsService.getDiplomaAssignment());
           model.addAttribute("thesisIds", thesisService.getThesis());
           model.addAttribute("defenses", defensesService.getThesisDefense());
            return "/students/create-student";
        }


        CreateStudentsDto createStudentsDto = new CreateStudentsDto();
        createStudentsDto.setName(student.getName());

        createStudentsDto.setFacultyNumber(student.getFacultyNumber());
//        createStudentsDto.setAssignmentIds(student.getAssignmentsIds());
//        createStudentsDto.setThesisIds(student.getThesisIds());
//        createStudentsDto.setDefenses(student.getDefenses());


        studentService.createStudent(createStudentsDto);

        return "redirect:/students";
    }

   // @PostMapping("/create")
   // public String createStudents(@Valid @ModelAttribute("/student") CreateStudentsViewModel student,
   //                            BindingResult bindingResult, Model model) {
   //     if (bindingResult.hasErrors()) {
   //         model.addAttribute("assignmentsIds", supervisedAssignmentsService.getDiplomaAssignment());
   //         model.addAttribute("thesisIds", thesisService.getThesis());
   //         model.addAttribute("defenses", defensesService.getThesisDefense());
   //         System.out.println("Validation errors: " + bindingResult.getAllErrors());
   //         return "/students/create-student";
   //     }
   //     studentService.createStudent(modelMapper.map(student, CreateStudentsDto.class));
   //     return "redirect:/students";
   // }
//
//
   // @PostMapping
   // public String createStudentDirectly(@Valid @ModelAttribute("student") CreateStudentsViewModel student,
   //                                     BindingResult bindingResult) {
   //     if (bindingResult.hasErrors()) {
   //         return "/students/create-student";
   //     }
   //     studentService.createStudent(modelMapper.map(student, CreateStudentsDto.class));
   //     return "redirect:/students";
   // }

    @GetMapping("/edit-student/{id}")
    public String showEditStudentsForm(Model model, @PathVariable Long id) {
        model.addAttribute("student", modelMapper.map(studentService.getStudentById(id), UpdateStudentsViewModel.class));
 //       model.addAttribute("assignmentsIds", supervisedAssignmentsService.getDiplomaAssignment());
 //       model.addAttribute("thesisIds", thesisService.getThesis());
        model.addAttribute("defenses", defensesService.getThesisDefense());
        return "/students/edit-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable long id, @Valid @ModelAttribute("student") UpdateStudentsViewModel student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//           model.addAttribute("assignmentsIds", supervisedAssignmentsService.getDiplomaAssignment());
//           model.addAttribute("thesisIds", thesisService.getThesis());
           model.addAttribute("defenses", defensesService.getThesisDefense());
            return "/students/edit-student";
        }

        UpdateStudentsDto updateStudentsDto = new UpdateStudentsDto();
        updateStudentsDto.setName(student.getName());

        updateStudentsDto.setFacultyNumber(student.getFacultyNumber());
 //       updateStudentsDto.setAssignmentIds(student.getAssignmentsIds());
 //       updateStudentsDto.setThesisIds(student.getThesisIds());
        updateStudentsDto.setDefenses(student.getDefenses());

        studentService.updateStudent(id, updateStudentsDto);

        return "redirect:/students";
    }

   // @PostMapping("/update/{id}")
   // public String updateStudent(@PathVariable long id, @Valid @ModelAttribute("student") UpdateStudentsViewModel  student,
   //                              BindingResult bindingResult, Model model) {
   //     if (bindingResult.hasErrors()) {
   //         model.addAttribute("assignmentsIds", supervisedAssignmentsService.getDiplomaAssignment());
   //         model.addAttribute("thesisIds", thesisService.getThesis());
   //         model.addAttribute("defenses", defensesService.getThesisDefense());
   //         return "/students/edit-student";
   //     }
   //     studentService.updateStudent(id, modelMapper.map(student, UpdateStudentsDto.class));
   //     return "redirect:/students";
   // }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

  //  // Search school by name and foundation date model mapper
  //  @GetMapping("/search-students")
  //  public String processSearchStudentsForm() {
  //      return "/students/search-students";
  //  }

    // Search school by name and high school
    //@GetMapping("/names-highschool")
    //public String getSchoolsByNameAndIsHighSchool(Model model, @RequestParam String schoolName, @RequestParam(defaultValue = "false") boolean isHighSchool) {
    //    List<StudentsViewModel> schools = studentService
    //            .getSchoolsByNameAndIsHighSchool(schoolName, isHighSchool)
    //            .stream()
    //            .map(this::convertToSchoolViewModel)
    //            .collect(Collectors.toList());
//
    //    model.addAttribute("schools", schools);
    //    return "/schools/schools";
    //}


    // Search school by name and founded after given date
   // @GetMapping("/names-foundation-date")
   // public String getSchoolsByNameAndFoundationDate(Model model, @RequestParam String schoolName,
   //                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundationDate) {
   //     List<StudentsViewModel> schools = studentService
   //             .getSchoolsByNameAndFoundationDate(schoolName, foundationDate)
   //             .stream()
   //             .map(this::convertToSchoolViewModel)
   //             .collect(Collectors.toList());
   //     model.addAttribute("schools", schools);
   //     return "/schools/schools";
   // }

    private StudentsViewModel convertToStudentsViewModel(StudentsDto studentDto) {
        return modelMapper.map(studentDto, StudentsViewModel.class);
    }


    @GetMapping("/graduates")
    public String showGraduatesForm(Model model) {
        model.addAttribute("graduates", Collections.emptyList());
        return "/students/graduates";
    }

    @GetMapping("/graduates/search")
    public String getGraduatesByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        List<GraduationDTO> graduates = studentService.findGraduatesByPeriod(startDate, endDate);
        model.addAttribute("graduates", graduates);
        return "/students/graduates";
    }




}
