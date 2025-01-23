package com.inf.Graduation.web.view.controller;

import com.inf.Graduation.data.dto.Review.CreateReviewDto;
import com.inf.Graduation.data.dto.Review.ReviewDto;
import com.inf.Graduation.data.dto.Review.UpdateReviewDto;

import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.service.ReviewService;
import com.inf.Graduation.service.TeachersService;
import com.inf.Graduation.service.ThesisService;
import com.inf.Graduation.web.view.model.Review.CreateReviewViewModel;
import com.inf.Graduation.web.view.model.Review.ReviewViewModel;
import com.inf.Graduation.web.view.model.Review.UpdateReviewViewModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
private final TeachersService teacherService;
private final ThesisService thesisService;


    @GetMapping
    public String getReview(Model model) {
        final List<ReviewViewModel> student = reviewService.getReview()
                .stream()
                .map(this::convertToReviewViewModel)
                .collect(Collectors.toList());
        model.addAttribute("review", student);
        return "/review/review.html";
    }

    @GetMapping("/create-review")
    public String showCreateReviewForm(Model model) {
        model.addAttribute("review", new CreateReviewViewModel());
        model.addAttribute("conclusion", ReviewConclusion.values());
        model.addAttribute("reviewerIds", teacherService.getTeachers());

    //    model.addAttribute("thesis", thesisService.getThesis());

        return "/review/create-review";
    }
    @PostMapping("/create")
    public String createReview(@Valid @ModelAttribute("review") CreateReviewViewModel review,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("conclusion", ReviewConclusion.values());
            model.addAttribute("reviewerIds", teacherService.getTeachers());
    //       model.addAttribute("thesis", thesisService.getThesis());
            return "/review/create-review";
        }

        CreateReviewDto createReviewDto = new CreateReviewDto();
        createReviewDto.setUploadDate(review.getUploadDate());
        createReviewDto.setText(review.getText());
        createReviewDto.setConclusion(review.getConclusion());
        createReviewDto.setReviewerIds(review.getReviewerIds());
    //    createReviewDto.setThesis(review.getThesis());

        reviewService.createReview(createReviewDto);

        return "redirect:/review";
    }


    //@PostMapping("/create")
    //public String createReview(@Valid @ModelAttribute("review") CreateReviewViewModel review,
    //                             BindingResult bindingResult, Model model) {
    //    if (bindingResult.hasErrors()) {
    //        model.addAttribute("conclusion", ReviewConclusion.values());
    //        model.addAttribute("reviewerIds", teacherService.getTeachers());
    //        model.addAttribute("thesis", thesisService.getThesis());
//
    //        return "/review/create-review";
    //    }
    //    reviewService.createReview(modelMapper.map(review, CreateReviewDto.class));
    //    return "redirect:/review";
    //}

    @GetMapping("/edit-review/{id}")
    public String showEditReviewForm(Model model, @PathVariable Long id) {
        model.addAttribute("review", modelMapper.map(reviewService.getReviewById(id),
                UpdateReviewViewModel.class));
        model.addAttribute("conclusion", ReviewConclusion.values());
        model.addAttribute("reviewerIds", teacherService.getTeachers());
        model.addAttribute("thesis", thesisService.getThesis());

        return "/review/edit-review";
    }

    @PostMapping("/update/{id}")
    public String updateReview(@PathVariable long id, @Valid @ModelAttribute("review") UpdateReviewViewModel review,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("conclusion", ReviewConclusion.values());
            model.addAttribute("reviewerIds", teacherService.getTeachers());
            model.addAttribute("thesis", thesisService.getThesis());
            return "/review/edit-review";
        }


        UpdateReviewDto updateReviewDto = new UpdateReviewDto();
        updateReviewDto.setUploadDate(review.getUploadDate());
        updateReviewDto.setText(review.getText());
        updateReviewDto.setConclusion(review.getConclusion());
        updateReviewDto.setReviewerIds(review.getReviewerIds());
 //       updateReviewDto.setThesis(review.getThesis());


        reviewService.updateReview(id, updateReviewDto);

        return "redirect:/review";
    }

    // @PostMapping("/update/{id}")
   // public String updateReview(@PathVariable long id, @Valid @ModelAttribute("review") UpdateReviewViewModel review,
   //                             BindingResult bindingResult, Model model) {
   //     if (bindingResult.hasErrors()) {
   //         model.addAttribute("conclusion", ReviewConclusion.values());
   //         model.addAttribute("reviewerIds", teacherService.getTeachers());
   //         model.addAttribute("thesis", thesisService.getThesis());
//
   //         return "/review/edit-review";
   //     }
   //     reviewService.updateReview(id, modelMapper.map(review, UpdateReviewDto.class));
   //     return "redirect:/review";
   // }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        reviewService.deleteReview(id);
        return "redirect:/review";
    }



    private ReviewViewModel convertToReviewViewModel(ReviewDto reviewDto) {
        return modelMapper.map(reviewDto, ReviewViewModel.class);
    }
}
