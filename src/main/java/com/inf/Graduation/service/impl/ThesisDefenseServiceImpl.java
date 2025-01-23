package com.inf.Graduation.service.impl;

import com.inf.Graduation.data.dto.ThesisDefense.CreateThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.UpdateThesisDefenseDto;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.data.entity.ThesisDefense;
import com.inf.Graduation.data.repository.ThesisDefenseRepository;
import com.inf.Graduation.exception.ThesisDefenseNotFoundException;
import com.inf.Graduation.service.ThesisDefenseService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

private final ThesisDefenseRepository thesisDefenseRepository;
 private final ModelMapper modelMapper;

   //public StudentsServiceImpl(StudentsRepository studentsRepository, ModelMapper modelMapper) {
   //    this.studentsRepository = studentsRepository;
   //    this.modelMapper = modelMapper;
   //}

    @Override
    public List<ThesisDefenseDto> getThesisDefense() {
        return thesisDefenseRepository.findAll().stream()
                .map(this::convertToThesisDefenseDto)
                .collect(Collectors.toList());
    }
    private ThesisDefenseDto convertToThesisDefenseDto(ThesisDefense thesisDefense) {
        return modelMapper.map(thesisDefense, ThesisDefenseDto.class);
    }

    @Override
    public ThesisDefenseDto getThesisDefenseById(long id) {
        //return null;
        return modelMapper.map(thesisDefenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ThesisDefense Id:" + id)), ThesisDefenseDto.class);
    }

    @Override
    public CreateThesisDefenseDto createThesisDefense(CreateThesisDefenseDto createThesisDefense) {


        ThesisDefense thesisDefense = new ThesisDefense();
        thesisDefense.setDate(createThesisDefense.getDate());
        thesisDefense.setGrade(createThesisDefense.getGrade());
        thesisDefense.setCommittee(createThesisDefense.getCommitteeIds());
        thesisDefense.setDefendingStudents(createThesisDefense.getStudentIds());
 //      thesisDefense.setTheses(createThesisDefense.getThesisId());




        thesisDefense = thesisDefenseRepository.save(thesisDefense);


        CreateThesisDefenseDto dto = new CreateThesisDefenseDto();
        dto.setDate(thesisDefense.getDate());
        dto.setCommitteeIds(thesisDefense.getCommittee());
        dto.setStudentIds(thesisDefense.getDefendingStudents());
 //       dto.setThesisId(thesisDefense.getTheses());
        dto.setGrade(thesisDefense.getGrade());

        return dto;
    }


   // @Override
   // public CreateThesisDefenseDto createThesisDefense(CreateThesisDefenseDto createThesisDefense) {
   //     //return null;
   //     return modelMapper
   //     .map(thesisDefenseRepository.save(modelMapper.map(createThesisDefense, ThesisDefense.class)), CreateThesisDefenseDto.class);
   // }
   @Override
   public UpdateThesisDefenseDto updateThesisDefense(long id, UpdateThesisDefenseDto updateThesisDefense) {
       // Получаване на ThesisDefense от базата данни по ID
       ThesisDefense thesisDefense = thesisDefenseRepository.findById(id)
               .orElseThrow(() -> new ThesisDefenseNotFoundException("Thesis Defense not found for ID: " + id));

       // Актуализиране на полетата на ThesisDefense
       thesisDefense.setDate(updateThesisDefense.getDate());
       thesisDefense.setCommittee(updateThesisDefense.getCommitteeIds());
       thesisDefense.setDefendingStudents(updateThesisDefense.getStudentIds());
  //     thesisDefense.setTheses(updateThesisDefense.getThesisId());
       thesisDefense.setGrade(updateThesisDefense.getGrade());

       // Записване на актуализирания обект в базата данни
       thesisDefense = thesisDefenseRepository.save(thesisDefense);

       // Създаване на DTO от актуализирания ThesisDefense
       UpdateThesisDefenseDto dto = new UpdateThesisDefenseDto();
       dto.setDate(thesisDefense.getDate());
       dto.setCommitteeIds(thesisDefense.getCommittee());
       dto.setStudentIds(thesisDefense.getDefendingStudents());
  //     dto.setThesisId(thesisDefense.getTheses());
       dto.setGrade(thesisDefense.getGrade());

       return dto;
   }

   // @Override
   // public UpdateThesisDefenseDto updateThesisDefense(long id, UpdateThesisDefenseDto updateThesisDefense) {
   //     ThesisDefense thesisDefense = modelMapper.map(getThesisDefenseById(id), ThesisDefense.class);
   //     thesisDefense.setDate(updateThesisDefense.getDate());
   //     thesisDefense.setCommittee(updateThesisDefense.getCommitteeIds());
//
//
   //     thesisDefense.setDefendingStudents(updateThesisDefense.getStudentIds());
   //     thesisDefense.setTheses(updateThesisDefense.getThesisId());
//
   //     thesisDefense.setGrade(updateThesisDefense.getGrade());
   //     return modelMapper.map(thesisDefenseRepository.save(thesisDefense), UpdateThesisDefenseDto.class);
   // }

    @Override
    public void deleteThesisDefense(long id) {
        thesisDefenseRepository.deleteById(id);
    }

    @Override
    public List<ThesisDefense> findDefendedThesesByGrade(double minGrade, double maxGrade) {
        return thesisDefenseRepository.findDefendedThesesByGrade(minGrade, maxGrade);
    }

    //  @Override
    //  public int countNegativeReviews() {
    //      return queriesRepository.countNegativeReviews();
    //  }

    @Override
    public double findAverageDefensesByPeriod(LocalDate startDate, LocalDate endDate) {
        return thesisDefenseRepository.findAverageDefensesByPeriod(startDate, endDate);
    }

    @Override
    public int countSuccessfulDefensesBySupervisor(Long professorId) {
        return thesisDefenseRepository.countSuccessfulDefensesBySupervisor(professorId);
    }


}
