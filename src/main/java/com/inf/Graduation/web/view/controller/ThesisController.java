package com.inf.Graduation.web.view.controller;


import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Thesis.CreateThesisDto;
import com.inf.Graduation.data.dto.Thesis.ThesisDto;
import com.inf.Graduation.data.dto.Thesis.UpdateThesisDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.service.*;
import com.inf.Graduation.web.view.model.Thesis.CreateThesisViewModel;
import com.inf.Graduation.web.view.model.Thesis.ThesisViewModel;
import com.inf.Graduation.web.view.model.Thesis.UpdateThesisViewModel;
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
@RequestMapping("/thesis")
public class ThesisController {
    private final ThesisService thesissService;
    private final DiplomaAssignmentService assignmentsService;
    private final StudentsService studentService;
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    private final ThesisDefenseService defenseService;
    // public TeachersController(TeachersService teachersService, ModelMapper modelMapper) {
    //     this.teachersService = teachersService;
    //     this.modelMapper = modelMapper;
    // }

    @GetMapping
    public String getThesis(Model model) {
        final List<ThesisViewModel> thesis = thesissService.getThesis()
                .stream()
                .map(this::convertToThesisViewModel)
                .collect(Collectors.toList());
        model.addAttribute("thesis", thesis);
        return "/thesis/thesis.html";
    }

    @GetMapping("/create-thesis")
    public String showCreateThesisForm(Model model) {
        model.addAttribute("thesis", new CreateThesisViewModel());
     //   model.addAttribute("studentId", studentService.getStudents());
     //   model.addAttribute("applicationId", assignmentsService.getDiplomaAssignment());
        model.addAttribute("review", reviewService.getReview());
     //   model.addAttribute("defense", defenseService.getThesisDefense());
        return "/thesis/create-thesis";
    }

    @PostMapping("/create")
    public String createThesis(@Valid @ModelAttribute("thesis") CreateThesisViewModel thesis,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        //    model.addAttribute("studentId", studentService.getStudents());
        //    model.addAttribute("applicationId", assignmentsService.getDiplomaAssignment());
            model.addAttribute("review", reviewService.getReview());
        //    model.addAttribute("defense", defenseService.getThesisDefense());
            return "/thesis/create-thesis";
        }


        CreateThesisDto thesisDto = new CreateThesisDto();
        thesisDto.setTitle(thesis.getTitle());
        thesisDto.setContent(thesis.getContent());
        thesisDto.setUploadDate(thesis.getUploadDate());
        thesisDto.setReview(thesis.getReview());


//        StudentsDto studentDto = studentService.getStudentById(thesis.getStudentId().getId());
//        Students student = new Students();
//        student.setId(studentDto.getId());
//        student.setName(studentDto.getName());


 //       DiplomaAssignmentDto diplomaAssignmentDto = assignmentsService.getDiplomaAssignmentById(thesis.getApplicationId().getId());
 //       DiplomaAssignment diplomaAssignment = new DiplomaAssignment();
 //       diplomaAssignment.setId(diplomaAssignmentDto.getId());
 //       diplomaAssignment.setTitle(diplomaAssignmentDto.getTitle());

  //      ReviewDto reviewDto = reviewService.getReviewById(thesis.getReview().getId());
  //      Review review = new Review();
  //      review.setId(reviewDto.getId());
  //      review.setConclusion(reviewDto.getConclusion());

 //       ThesisDefenseDto defenseDto = defenseService.getThesisDefenseById(thesis.getDefense().getId());
 //       ThesisDefense defense = new ThesisDefense();
 //       defense.setId(defenseDto.getId());
 //       defense.setDate(defenseDto.getDate());


 //       thesisDto.setStudentId(student);
 //       thesisDto.setApplicationId(diplomaAssignment);
    //    thesisDto.setReview(review);
 //       thesisDto.setDefense(defense);



        thesissService.createThesis(thesisDto);

        return "redirect:/thesis";
    }



    // @PostMapping("/create")
   // public String createThesis(@Valid @ModelAttribute("thesis") CreateThesisViewModel thesis,
   //                              BindingResult bindingResult,Model model) {
   //     if (bindingResult.hasErrors()) {
   //         model.addAttribute("studentId", studentService.getStudents());
   //         model.addAttribute("applicationId", assignmentsService.getDiplomaAssignment());
   //         model.addAttribute("review", reviewService.getReview());
   //         return "/thesis/create-thesis";
   //     }
   //     thesissService.createThesis(modelMapper.map(thesis, CreateThesisDto.class));
   //     return "redirect:/thesis";
   // }

    @GetMapping("/edit-thesis/{id}")
    public String showEditThesisForm(Model model, @PathVariable Long id) {
        model.addAttribute("thesis", modelMapper.map(thesissService.getThesisById(id),
                UpdateThesisViewModel.class));
    //    model.addAttribute("studentId", studentService.getStudents());
    //    model.addAttribute("applicationId", assignmentsService.getDiplomaAssignment());
        model.addAttribute("review", reviewService.getReview());
    //    model.addAttribute("defense", defenseService.getThesisDefense());
        return "/thesis/edit-thesis";
    }

    @PostMapping("/update/{id}")
    public String updateThesis(@PathVariable long id, @Valid @ModelAttribute("thesis") UpdateThesisViewModel thesis,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        //    model.addAttribute("studentId", studentService.getStudents());
        //    model.addAttribute("applicationId", assignmentsService.getDiplomaAssignment());
            model.addAttribute("reviewId", reviewService.getReview());
        //    model.addAttribute("defense", defenseService.getThesisDefense());
            return "/thesis/edit-thesis";
        }


        UpdateThesisDto updateThesisDto = new UpdateThesisDto();
        //updateThesisDto.setId(id);
        updateThesisDto.setTitle(thesis.getTitle());
        updateThesisDto.setContent(thesis.getContent());
        updateThesisDto.setUploadDate(thesis.getUploadDate());
//        updateThesisDto.setStudentId(thesis.getStudentId());
//        updateThesisDto.setApplicationId(thesis.getApplicationId());
        updateThesisDto.setReviewId(thesis.getReview());
//        updateThesisDto.setDefense(thesis.getDefense());


        thesissService.updateThesis(id, updateThesisDto);

        return "redirect:/thesis";
    }


    //  @PostMapping("/update/{id}")
  //  public String updateThesis(@PathVariable long id, @Valid @ModelAttribute("thesis") UpdateThesisViewModel thesis,
  //                              BindingResult bindingResult, Model model) {
  //      if (bindingResult.hasErrors()) {
  //          model.addAttribute("studentId", studentService.getStudents());
  //          model.addAttribute("applicationId", assignmentsService.getDiplomaAssignment());
  //          model.addAttribute("review", reviewService.getReview());
  //          return "/thesis/edit-thesis";
  //      }
  //      thesissService.updateThesis(id, modelMapper.map(thesis, UpdateThesisDto.class));
  //      return "redirect:/thesis";
  //  }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        thesissService.deleteThesis(id);
        return "redirect:/thesis";
    }



    private ThesisViewModel convertToThesisViewModel(ThesisDto thesisDto) {
        return modelMapper.map(thesisDto, ThesisViewModel.class);
    }

    @GetMapping("/negative-reviews")
    public String countNegativeReviews(Model model) {
        int negativeReviewsCount = thesissService.countNegativeReviews(ReviewConclusion.NEGATIVE);

        model.addAttribute("negativeReviews", negativeReviewsCount);

        return "/thesis/negative-reviews";
    }


}
