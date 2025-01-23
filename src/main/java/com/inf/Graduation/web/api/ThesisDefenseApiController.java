package com.inf.Graduation.web.api;

import com.inf.Graduation.data.dto.ThesisDefense.CreateThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.UpdateThesisDefenseDto;
import com.inf.Graduation.data.entity.ThesisDefense;
import com.inf.Graduation.service.ThesisDefenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ThesisDefenses")
public class ThesisDefenseApiController {

    private final ThesisDefenseService thesisDefenseService;
    private final ModelMapper modelMapper;

    @GetMapping()//"/ThesisDefense"
    public List<ThesisDefenseDto> getThesisDefense(){

        return thesisDefenseService.getThesisDefense();
    }

    @GetMapping("/{id}")
    public ThesisDefenseDto getThesisDefenseById(@PathVariable long id) {
        return this.thesisDefenseService.getThesisDefenseById(id);
    }

    @PostMapping
    public CreateThesisDefenseDto createThesisDefense(@RequestBody CreateThesisDefenseDto createThesisDefenseDto) {
        return this.thesisDefenseService.createThesisDefense(createThesisDefenseDto);
    }

    @PutMapping("/{id}")
    public UpdateThesisDefenseDto updateThesisDefense(@PathVariable long id, @RequestBody UpdateThesisDefenseDto updateThesisDefenseDto) {

        return this.thesisDefenseService.updateThesisDefense(id, updateThesisDefenseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteThesisDefense(@PathVariable long id) {
        this.thesisDefenseService.deleteThesisDefense(id);
    }


    @GetMapping("/defended-theses")
    public List<ThesisDefense> getDefendedThesesByGrade(@RequestParam double minGrade, @RequestParam double maxGrade) {
        return thesisDefenseService.findDefendedThesesByGrade(minGrade, maxGrade);
    }

    @GetMapping("/average-defenses")
    public double getAverageDefensesByPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return thesisDefenseService.findAverageDefensesByPeriod(startDate, endDate);
    }

    @GetMapping("/successful-defenses")
    public int getSuccessfulDefensesBySupervisor(@RequestParam Long professorId) {
        return thesisDefenseService.countSuccessfulDefensesBySupervisor(professorId);
    }
}
