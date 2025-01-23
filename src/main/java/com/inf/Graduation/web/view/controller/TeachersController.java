package com.inf.Graduation.web.view.controller;

import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.Teachers.CreateTeachersDto;
import com.inf.Graduation.data.dto.Teachers.TeachersDto;
import com.inf.Graduation.data.dto.Teachers.UpdateTeachersDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.ThesisDefense;
import com.inf.Graduation.service.DiplomaAssignmentService;
import com.inf.Graduation.service.ReviewService;
import com.inf.Graduation.service.TeachersService;
import com.inf.Graduation.service.ThesisDefenseService;
import com.inf.Graduation.web.view.model.DiplomaAssignment.DiplomaAssignmentViewModel;
import com.inf.Graduation.web.view.model.Teacher.CreateTeachersViewModel;
import com.inf.Graduation.web.view.model.Teacher.TeachersViewModel;
import com.inf.Graduation.web.view.model.Teacher.UpdateTeachersViewModel;
import com.inf.Graduation.web.view.model.ThesisDefense.ThesisDefenseViewModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/teachers")
//@PreAuthorize("hasAuthority('TEACHER')")
public class TeachersController {

    private final TeachersService teachersService;
    private final DiplomaAssignmentService supervisedAssignmentsService;
    private final ThesisDefenseService defensesService;
    private final ModelMapper modelMapper;
    private  final ReviewService reviewService;

    @GetMapping
    public String getTeachers(Model model) {
        final List<TeachersViewModel> teacher = teachersService.getTeachers()
                .stream()
                .map(this::convertToTeachersViewModel)
                .collect(Collectors.toList());
        model.addAttribute("teachers", teacher);
        return "/teachers/teachers.html";
    }

    private TeachersViewModel convertToTeachersViewModel(TeachersDto teachersDto) {
        return modelMapper.map(teachersDto, TeachersViewModel.class);
    }

    @GetMapping("/create-teacher")
    public String showCreateTeachersForm(Model model) {
        model.addAttribute("teacher", new CreateTeachersViewModel());
        //model.addAttribute("position", List.of("Assistant", "Professor", "Senior Lecturer"));
        model.addAttribute("position", Position.values());
//       model.addAttribute("supervisedAssignmentIds", supervisedAssignmentsService.getDiplomaAssignment());
//       model.addAttribute("defensesIds", defensesService.getThesisDefense());
//       model.addAttribute("review", reviewService.getReview());

        return "/teachers/create-teacher";
    }

    @PostMapping("/create")
    public String createTeachers(@Valid @ModelAttribute("teacher") CreateTeachersViewModel teacher,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("position", Position.values());
            model.addAttribute("supervisedAssignmentIds", supervisedAssignmentsService.getDiplomaAssignment());
            model.addAttribute("defensesIds", defensesService.getThesisDefense());
            model.addAttribute("review", reviewService.getReview());
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "/teachers/create-teacher";
        }


        CreateTeachersDto teacherDto = new CreateTeachersDto();
        teacherDto.setName(teacher.getName());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setPosition(teacher.getPosition());


//        teacherDto.setSupervisedAssignmentIds(teacher.getSupervisedAssignmentIds());
//
//
//        teacherDto.setDefensesIds(teacher.getDefensesIds());
//
//        teacherDto.setReview(teacher.getReview());


        teachersService.createTeacher(teacherDto);

        return "redirect:/teachers";
    }




    // @PostMapping("/create")
   // public String createTeachers(@Valid @ModelAttribute("teacher") CreateTeachersViewModel teacher,
   //                            BindingResult bindingResult, Model model) {
   //     if (bindingResult.hasErrors()) {
   //         model.addAttribute("position", Position.values());
   //         model.addAttribute("supervisedAssignmentIds", supervisedAssignmentsService.getDiplomaAssignment());
   //         model.addAttribute("defensesIds", defensesService.getThesisDefense());
   //         System.out.println("Validation errors: " + bindingResult.getAllErrors());
   //         return "/teachers/create-teacher";
   //     }
//
//
   //     teachersService.createTeacher(modelMapper.map(teacher, CreateTeachersDto.class));
   //     return "redirect:/teachers";
   // }



    @GetMapping("/edit-teacher/{id}")
    public String showEditTeachersForm(Model model, @PathVariable Long id) {
        model.addAttribute("teacher", modelMapper.map(teachersService.getTeacherById(id),
                UpdateTeachersViewModel.class));
        model.addAttribute("position", Position.values());
 //       model.addAttribute("supervisedAssignmentIds", supervisedAssignmentsService.getDiplomaAssignment());
 //       model.addAttribute("defensesIds", defensesService.getThesisDefense());
 //       model.addAttribute("review", reviewService.getReview());
        return "/teachers/edit-teacher";
    }

    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable long id, @Valid @ModelAttribute("teacher") UpdateTeachersViewModel teacher,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("position", Position.values());
//           model.addAttribute("supervisedAssignmentIds", supervisedAssignmentsService.getDiplomaAssignmentById(id));
//           model.addAttribute("defensesIds", defensesService.getThesisDefenseById(id));
//           model.addAttribute("review", reviewService.getReview());
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "/teachers/edit-teacher";
        }

        // Ръчно прехвърляне на данните
        UpdateTeachersDto updateTeachersDto = new UpdateTeachersDto();
        updateTeachersDto.setName(teacher.getName());
        updateTeachersDto.setEmail(teacher.getEmail());
        updateTeachersDto.setPosition(teacher.getPosition());
//       updateTeachersDto.setSupervisedAssignmentIds(teacher.getSupervisedAssignmentIds());
//       updateTeachersDto.setDefensesIds(teacher.getDefensesIds());
//       updateTeachersDto.setReview(teacher.getReview());

        teachersService.updateTeacher(id, updateTeachersDto);
        return "redirect:/teachers";
    }




    //  @PostMapping("/update/{id}")
 //  public String updateTeacher(@PathVariable long id, @Valid @ModelAttribute("teacher") UpdateTeachersViewModel teacher,
 //                               BindingResult bindingResult, Model model) {
 //      if (bindingResult.hasErrors()) {
 //          model.addAttribute("position", Position.values());
 //          model.addAttribute("supervisedAssignmentIds", supervisedAssignmentsService.getDiplomaAssignmentById(id));
 //          model.addAttribute("defensesIds", defensesService.getThesisDefenseById(id));
 //          System.out.println("Validation errors: " + bindingResult.getAllErrors());
 //          return "/teachers/edit-teacher";
 //      }
//
 //      teachersService.updateTeacher(id, modelMapper.map(teacher, UpdateTeachersDto.class));
 //      return "redirect:/teachers";
 //  }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        teachersService.deleteTeacher(id);
        return "redirect:/teachers";
    }
 //   @GetMapping("/search-teacher")
 //   public String processSearchTeachersForm() {
 //       return "/teachers/search-teacher";
 //   }






}
