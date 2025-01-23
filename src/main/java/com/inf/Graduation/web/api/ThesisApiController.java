package com.inf.Graduation.web.api;

import com.inf.Graduation.data.dto.Thesis.CreateThesisDto;
import com.inf.Graduation.data.dto.Thesis.ThesisDto;
import com.inf.Graduation.data.dto.Thesis.UpdateThesisDto;
import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.service.ThesisService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Thesis")
public class ThesisApiController {


    private final ThesisService thesisService;
    private final ModelMapper modelMapper;

    @GetMapping()//"/Thesis"
    public List<ThesisDto> getThesis() {

        return thesisService.getThesis();
    }

    @GetMapping("/{id}")
    public ThesisDto getThesisDefenseById(@PathVariable long id) {
        return this.thesisService.getThesisById(id);
    }

    @PostMapping
    public CreateThesisDto createThesis(@RequestBody CreateThesisDto createThesisDefenseDto) {
        return this.thesisService.createThesis(createThesisDefenseDto);
    }

    @PutMapping("/{id}")
    public UpdateThesisDto updateThesis(@PathVariable long id, @RequestBody UpdateThesisDto updateThesisDto) {
        // Map updated data from the DTO to the service layer
        return this.thesisService.updateThesis(id, updateThesisDto);
    }

    @DeleteMapping("/{id}")
    public void deleteThesis(@PathVariable long id) {
        this.thesisService.deleteThesis(id);
    }


    @GetMapping("/negative-reviews")
    public int getNegativeReviewsCount(@RequestParam ReviewConclusion negativeReview) {
        return thesisService.countNegativeReviews(negativeReview);
    }
}