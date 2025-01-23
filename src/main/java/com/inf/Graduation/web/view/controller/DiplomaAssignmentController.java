package com.inf.Graduation.web.view.controller;

import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.UpdateDiplomaAssignmentDto;
import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.service.DiplomaAssignmentService;
import com.inf.Graduation.service.StudentsService;
import com.inf.Graduation.service.TeachersService;
import com.inf.Graduation.service.ThesisService;
import com.inf.Graduation.web.view.model.DiplomaAssignment.CreateDiplomaAssignmentViewModel;
import com.inf.Graduation.web.view.model.DiplomaAssignment.DiplomaAssignmentViewModel;
import com.inf.Graduation.web.view.model.DiplomaAssignment.UpdateDiplomaAssignmentViewModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/DiplomaAssignment")
public class DiplomaAssignmentController {

    private final DiplomaAssignmentService diplomaAssignmentService;
    private final StudentsService studentService;
    private final TeachersService teacherService;
    private final ThesisService thesisService;
    private final ModelMapper modelMapper;

   // public DiplomaAssignmentController(DiplomaAssignmentService diplomaAssignmentService, ModelMapper modelMapper) {
   //     this.diplomaAssignmentService = diplomaAssignmentService;
   //     this.modelMapper = modelMapper;
   // }

    @GetMapping
    public String getDiplomaAssignment(Model model) {
        final List<DiplomaAssignmentViewModel> diplomaAssignment = diplomaAssignmentService.getDiplomaAssignment()
                .stream()
                .map(this::convertToDiplomaAssignmentViewModel)
                .collect(Collectors.toList());
        System.out.println("DiplomaAssignment List: " + diplomaAssignment);
        model.addAttribute("DiplomaAssignment", diplomaAssignment);

        return "/DiplomaAssignment/DiplomaAssignment.html";
    }

    @GetMapping("/create-DiplomaAssignment")
    public String showCreateDiplomaAssignmentForm(Model model) {
        model.addAttribute("DiplomaAssignment", new CreateDiplomaAssignmentViewModel());
        model.addAttribute("studentId", studentService.getStudents());
        model.addAttribute("supervisorId", teacherService.getTeachers());

     //   model.addAttribute("thesis", thesisService.getThesis());
        model.addAttribute("status", ApplicationStatus.values());
        return "/DiplomaAssignment/create-DiplomaAssignment";
    }


  // @PostMapping("/create")
  // public String createDiplomaAssignment(@Valid @ModelAttribute("DiplomaAssignment") CreateDiplomaAssignmentViewModel diplomaAssignment,
  //                              BindingResult bindingResult, Model model) {
  //     if (bindingResult.hasErrors()) {
  //         model.addAttribute("studentId", studentService.getStudents());
  //         model.addAttribute("supervisorId", teacherService.getTeachers());
  //         model.addAttribute("status", ApplicationStatus.values());
  //         return "/DiplomaAssignment/create-DiplomaAssignment";
  //     }
  //     diplomaAssignmentService.createDiplomaAssignment(modelMapper.map(diplomaAssignment, CreateDiplomaAssignmentDto.class));
  //     return "redirect:/DiplomaAssignment";
  // }
  @PostMapping("/create")
  public String createDiplomaAssignment(@Valid @ModelAttribute("DiplomaAssignment") CreateDiplomaAssignmentViewModel diplomaAssignment,
                                        BindingResult bindingResult, Model model) {
      if (bindingResult.hasErrors()) {
          model.addAttribute("studentId", studentService.getStudents());
          model.addAttribute("supervisorId", teacherService.getTeachers());
  //        model.addAttribute("thesis", thesisService.getThesis());
          model.addAttribute("status", ApplicationStatus.values());
          return "/DiplomaAssignment/create-DiplomaAssignment";
      }


      CreateDiplomaAssignmentDto createDiplomaAssignmentDto = new CreateDiplomaAssignmentDto();
      createDiplomaAssignmentDto.setTitle(diplomaAssignment.getTitle());
      createDiplomaAssignmentDto.setGoal(diplomaAssignment.getGoal());
      createDiplomaAssignmentDto.setTasks(diplomaAssignment.getTasks());
      createDiplomaAssignmentDto.setTechnologies(diplomaAssignment.getTechnologies());
  //    createDiplomaAssignmentDto.setStudentIds(diplomaAssignment.getStudentId());
      createDiplomaAssignmentDto.setSupervisorIds(diplomaAssignment.getSupervisorId());
    //  createDiplomaAssignmentDto.setThesis(diplomaAssignment.getThesis());
      createDiplomaAssignmentDto.setStatus(diplomaAssignment.getStatus());


      diplomaAssignmentService.createDiplomaAssignment(createDiplomaAssignmentDto);

      return "redirect:/DiplomaAssignment";
  }


    @GetMapping("/edit-DiplomaAssignment/{id}")
    public String showEditDiplomaAssignmentForm(Model model, @PathVariable Long id) {
        model.addAttribute("DiplomaAssignment", modelMapper.map(diplomaAssignmentService.getDiplomaAssignmentById(id),
                UpdateDiplomaAssignmentViewModel.class));
        model.addAttribute("studentId", studentService.getStudents());
        model.addAttribute("supervisorId", teacherService.getTeachers());
 //       model.addAttribute("thesis", thesisService.getThesis());
        model.addAttribute("status", ApplicationStatus.values());
        return "/DiplomaAssignment/edit-DiplomaAssignment";
    }
    @PostMapping("/update/{id}")
    public String updateDiplomaAssignment(@PathVariable long id, @Valid @ModelAttribute("DiplomaAssignment") UpdateDiplomaAssignmentViewModel diplomaAssignment,
                                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentId", studentService.getStudents());
            model.addAttribute("supervisorId", teacherService.getTeachers());
  //          model.addAttribute("thesis", thesisService.getThesis());
            model.addAttribute("status", ApplicationStatus.values());
            return "/DiplomaAssignment/edit-DiplomaAssignment";
        }

        UpdateDiplomaAssignmentDto updateDiplomaAssignmentDto = new UpdateDiplomaAssignmentDto();
        updateDiplomaAssignmentDto.setTitle(diplomaAssignment.getTitle());
        updateDiplomaAssignmentDto.setGoal(diplomaAssignment.getGoal());
        updateDiplomaAssignmentDto.setTasks(diplomaAssignment.getTasks());
        updateDiplomaAssignmentDto.setTechnologies(diplomaAssignment.getTechnologies());
    //    updateDiplomaAssignmentDto.setStudentIds(diplomaAssignment.getStudentId());
        updateDiplomaAssignmentDto.setSupervisorIds(diplomaAssignment.getSupervisorId());
 //       updateDiplomaAssignmentDto.setThesis(diplomaAssignment.getThesis());
        updateDiplomaAssignmentDto.setStatus(diplomaAssignment.getStatus());


        diplomaAssignmentService.updateDiplomaAssignment(id, updateDiplomaAssignmentDto);

        return "redirect:/DiplomaAssignment";
    }

   // @PostMapping("/update/{id}")
   // public String updateDiplomaAssignment(@PathVariable long id, @Valid @ModelAttribute("DiplomaAssignment") UpdateDiplomaAssignmentViewModel diplomaAssignment,
   //                             BindingResult bindingResult, Model model) {
   //     if (bindingResult.hasErrors()) {
   //         model.addAttribute("studentId", studentService.getStudents());
   //         model.addAttribute("supervisorId", teacherService.getTeachers());
   //         model.addAttribute("status", ApplicationStatus.values());
   //         return "/DiplomaAssignment/edit-DiplomaAssignment";
   //     }
   //     diplomaAssignmentService.updateDiplomaAssignment(id, modelMapper.map(diplomaAssignment, UpdateDiplomaAssignmentDto.class));
   //     return "redirect:/DiplomaAssignment";
   // }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        diplomaAssignmentService.deleteDiplomaAssignment(id);
        return "redirect:/DiplomaAssignment";
    }

 //   // Search school by name and foundation date model mapper
 //   @GetMapping("/search-DiplomaAssignment")
 //   public String processSearchDiplomaAssignmentForm() {
 //       return "/DiplomaAssignment/search-DiplomaAssignment";
 //   }

    private DiplomaAssignmentViewModel convertToDiplomaAssignmentViewModel(DiplomaAssignmentDto diplomaAssignmentDto) {
        return modelMapper.map(diplomaAssignmentDto, DiplomaAssignmentViewModel.class);
    }

   // @GetMapping("/approved")
   // public ResponseEntity<List<DiplomaAssignment>> getApprovedAssignments() {
   //     return ResponseEntity.ok(diplomaAssignmentService.findByApprovedTrue());
   // }
//
   // @GetMapping("/search")
   // public ResponseEntity<List<DiplomaAssignment>> searchAssignments(@RequestParam String keyword) {
   //     return ResponseEntity.ok(diplomaAssignmentService.findByTitleContaining(keyword));
   // }
//
   // @GetMapping("/by-teacher")
   // public ResponseEntity<List<DiplomaAssignment>> getAssignmentsByTeacher(@RequestParam Long teacherId) {
   //     return ResponseEntity.ok(diplomaAssignmentService.findByTeacherIdAndApprovedTrue(teacherId));
   // }



    @GetMapping("/approved-assignments")
    public String getApprovedAssignments(@RequestParam ApplicationStatus status, Model model) {
        List<DiplomaAssignment> approvedAssignments = diplomaAssignmentService.findAllApprovedThesisAssignments(status);
        model.addAttribute("approvedAssignments", approvedAssignments);
        return "/DiplomaAssignment/approved-assignments";
    }


    @GetMapping("/assignments-by-keyword")
    public String getAssignmentsByKeyword(@RequestParam String keyword, Model model) {
        List<DiplomaAssignment> assignmentsByKeyword = diplomaAssignmentService.findThesisByKeyword(keyword);
        model.addAttribute("assignmentsByKeyword", assignmentsByKeyword);
        return "/DiplomaAssignment/assignments-by-keyword";
    }


    @GetMapping("/assignments-by-supervisor-and-status")
    public String getAssignmentsBySupervisorAndStatus(@RequestParam Long supervisorIds, @RequestParam ApplicationStatus status, Model model) {
        List<DiplomaAssignment> assignmentsBySupervisorAndStatus = diplomaAssignmentService.findThesisBySupervisorAndStatus(supervisorIds, status);
        model.addAttribute("assignmentsBySupervisorAndStatus", assignmentsBySupervisorAndStatus);
        return "/DiplomaAssignment/assignments-by-supervisor-and-status";
    }



}
