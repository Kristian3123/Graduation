package com.inf.Graduation.web.view.controller;


import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.ThesisDefense.CreateThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.UpdateThesisDefenseDto;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.service.StudentsService;
import com.inf.Graduation.service.TeachersService;
import com.inf.Graduation.service.ThesisDefenseService;
import com.inf.Graduation.web.view.model.ThesisDefense.CreateThesisDefenseViewModel;
import com.inf.Graduation.web.view.model.ThesisDefense.ThesisDefenseViewModel;
import com.inf.Graduation.web.view.model.ThesisDefense.UpdateThesisDefenseViewModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/ThesisDefenses")
public class ThesisDefenseController {

    private final ThesisDefenseService thesisDefenseService;
    private final StudentsService studentService;
    private final TeachersService  committeeService;
    private final ThesisDefenseService thesisDedenseService;
    private final ModelMapper modelMapper;

  // public ThesisDefenseController(ThesisDefenseService thesisDefenseService, ModelMapper modelMapper) {
  //     this.thesisDefenseService = thesisDefenseService;
  //     this.modelMapper = modelMapper;
  // }

    @GetMapping
    public String getThesisDefence(Model model) {
        final List<ThesisDefenseViewModel> thesisDefense = thesisDefenseService.getThesisDefense()
                .stream()
                .map(this::convertToThesisDefenseViewModel)
                .collect(Collectors.toList());
        model.addAttribute("ThesisDefenses", thesisDefense);
        return "/ThesisDefenses/ThesisDefenses.html";
    }

    @GetMapping("/create-ThesisDefense")
    public String showCreateThesisDefenseForm(Model model) {
        model.addAttribute("ThesisDefense", new CreateThesisDefenseViewModel());
        model.addAttribute("committeeIds", committeeService.getTeachers());
        model.addAttribute("studentIds", studentService.getStudents());
//        model.addAttribute("thesisId", thesisDedenseService.getThesisDefense());
        return "/ThesisDefenses/create-ThesisDefense";
    }

    @PostMapping("/create")
    public String createThesisDefense(@Valid @ModelAttribute("ThesisDefense") CreateThesisDefenseViewModel thesisDefense,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ThesisDefense", new CreateThesisDefenseViewModel());
            model.addAttribute("committeeIds", committeeService.getTeachers());
            model.addAttribute("studentIds", studentService.getStudents());
//            model.addAttribute("thesisId", thesisDedenseService.getThesisDefense());
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "/ThesisDefenses/create-ThesisDefense";
        }
        CreateThesisDefenseDto createThesisDefenseDto = new CreateThesisDefenseDto();
        createThesisDefenseDto.setDate(thesisDefense.getDate());
        createThesisDefenseDto.setCommitteeIds(thesisDefense.getCommitteeIds());
        createThesisDefenseDto.setStudentIds(thesisDefense.getStudentIds());
//        createThesisDefenseDto.setThesisId(thesisDefense.getThesisId());
        createThesisDefenseDto.setGrade(thesisDefense.getGrade());


        thesisDefenseService.createThesisDefense(createThesisDefenseDto);


      //  thesisDefenseService.createThesisDefense(modelMapper.map(thesisDefense, CreateThesisDefenseDto.class));
        return "redirect:/ThesisDefenses";
    }

    @GetMapping("/edit-ThesisDefense/{id}")
    public String showEditThesisDefenseForm(Model model, @PathVariable Long id) {
        model.addAttribute("ThesisDefense", modelMapper.map(thesisDefenseService.getThesisDefenseById(id),
                UpdateThesisDefenseViewModel.class));
        model.addAttribute("committeeIds", committeeService.getTeachers());
        model.addAttribute("studentIds", studentService.getStudents());
//        model.addAttribute("thesisId", thesisDedenseService.getThesisDefense());
        return "/ThesisDefenses/edit-ThesisDefense";
    }

    @PostMapping("/update/{id}")
    public String updateThesisDefense(@PathVariable long id, @Valid @ModelAttribute("ThesisDefense") UpdateThesisDefenseViewModel thesisDefense,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("committeeIds", committeeService.getTeachers());
            model.addAttribute("studentIds", studentService.getStudents());
//            model.addAttribute("thesisId", thesisDedenseService.getThesisDefense());
            return "/ThesisDefenses/edit-ThesisDefense";
        }


        UpdateThesisDefenseDto dto = new UpdateThesisDefenseDto();
        dto.setDate(thesisDefense.getDate());
        dto.setCommitteeIds(thesisDefense.getCommitteeIds());
        dto.setStudentIds(thesisDefense.getStudentIds());
 //       dto.setThesisId(thesisDefense.getThesisId());
        dto.setGrade(thesisDefense.getGrade());


        thesisDefenseService.updateThesisDefense(id, dto);

        return "redirect:/ThesisDefenses";
    }


    //@PostMapping("/update/{id}")
    //public String updateThesisDefense(@PathVariable long id, @Valid @ModelAttribute("ThesisDefense") UpdateThesisDefenseViewModel thesisDefense,
    //                             BindingResult bindingResult, Model model) {
    //    if (bindingResult.hasErrors()) {
    //        model.addAttribute("committeeIds", committeeService.getTeachers());
    //        model.addAttribute("studentIds", studentService.getStudents());
    //        model.addAttribute("thesisId", thesisDedenseService.getThesisDefense());
    //        return "/ThesisDefenses/edit-ThesisDefense";
    //    }
    //    thesisDefenseService.updateThesisDefense(id, modelMapper.map(thesisDefense, UpdateThesisDefenseDto.class));
    //    return "redirect:/ThesisDefenses";
    //}

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        thesisDefenseService.deleteThesisDefense(id);
        return "redirect:/ThesisDefenses";
    }

  //  // Search school by name and foundation date model mapper
  //  @GetMapping("/search-ThesisDefense")
  //  public String processSearchThesisDefenseForm() {
  //      return "/ThesisDefenses/search-ThesisDefense";
  //  }


    private ThesisDefenseViewModel convertToThesisDefenseViewModel(ThesisDefenseDto thesisDefenseDto) {
        return modelMapper.map(thesisDefenseDto, ThesisDefenseViewModel.class);
    }

    @GetMapping("/defended-theses-form")
    public String showDefendedThesesForm() {
        return "ThesisDefenses/defended-theses-form";
    }


    @GetMapping("/defended-theses")
    public String findDefendedThesesByGrade(@RequestParam double minGrade,
                                            @RequestParam double maxGrade,
                                            Model model) {
        List<ThesisDefenseViewModel> defendedTheses = thesisDefenseService
                .findDefendedThesesByGrade(minGrade, maxGrade)
                .stream()
                .map(td -> modelMapper.map(td, ThesisDefenseViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("defendedTheses", defendedTheses);
        return "/ThesisDefenses/defended-theses";
    }

    @GetMapping("/average-defenses")
    public String findAverageDefensesByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                              Model model) {
        double average = thesisDefenseService.findAverageDefensesByPeriod(startDate, endDate);
        model.addAttribute("averageDefenses", average);
        return "/ThesisDefenses/average-defenses";
    }


    @GetMapping("/successful-defenses/{professorId}")
    public String countSuccessfulDefensesBySupervisor(@PathVariable Long professorId, Model model) {
        int count = thesisDefenseService.countSuccessfulDefensesBySupervisor(professorId);
        model.addAttribute("successfulDefenses", count);
        return "/ThesisDefenses/successful-defenses";
    }


}
