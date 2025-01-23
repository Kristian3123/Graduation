package com.inf.Graduation.service.impl;


import com.inf.Graduation.data.dto.Thesis.CreateThesisDto;
import com.inf.Graduation.data.dto.Thesis.ThesisDto;
import com.inf.Graduation.data.dto.Thesis.UpdateThesisDto;
import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.data.repository.ThesisRepository;
import com.inf.Graduation.exception.ThesisNotFoundException;
import com.inf.Graduation.service.ThesisService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThesisServiceImpl implements ThesisService {
    private final ThesisRepository thesisRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ThesisDto> getThesis() {
        return thesisRepository.findAll().stream()
                .map(this::convertToThesisDto)
                .collect(Collectors.toList());
    }
    private ThesisDto convertToThesisDto(Thesis thesis) {
        return modelMapper.map(thesis, ThesisDto.class);
    }

    @Override
    public ThesisDto getThesisById(long id) {
        //return null;
        return modelMapper.map(thesisRepository.findById(id)
                .orElseThrow(() -> new ThesisNotFoundException("Invalid Thesis Id:" + id)), ThesisDto.class);
    }

    //!!!! Ako dode problem s mapvaneto
   // @Override
   // public CreateThesisDto createThesis(CreateThesisDto create) {
   //     // Създаване на нов обект Thesis
   //     Thesis thesis = new Thesis();
//
   //     // Прехвърляне на стойности от DTO към Entity
   //     thesis.setTitle(create.getTitle());
   //     thesis.setContent(create.getContent());
   //     thesis.setUploadDate(create.getUploadDate());
//
   //     // Получаваме студентския обект от DTO
   //     Students student = create.getStudentId(); // Получаваме студента от DTO
   //     thesis.setStudent(student);
//
   //     // Получаваме дипломното задание (DiplomaAssignment) от DTO
   //     DiplomaAssignment application = create.getApplicationId(); // Получаваме дипломната работа от DTO
   //     thesis.setApplication(application);
//
   //     // Получаваме рецензията от DTO
   //     Review review = create.getReview(); // Получаваме рецензията от DTO
   //     thesis.setReview(review);
//
   //     // Записване на новия обект Thesis в базата данни
   //     thesis = thesisRepository.save(thesis);
//
   //     // Връщане на DTO за създадения обект Thesis
   //     CreateThesisDto resultDto = new CreateThesisDto();
   //     resultDto.setTitle(thesis.getTitle());
   //     resultDto.setContent(thesis.getContent());
   //     resultDto.setUploadDate(thesis.getUploadDate());
   //     resultDto.setStudentId(thesis.getStudent()); // Целия студентски обект
   //     resultDto.setApplicationId(thesis.getApplication()); // Целия дипломен обект
   //     resultDto.setReview(thesis.getReview()); // Цялата рецензия
//
   //     return resultDto;
   // }


    @Override
    public CreateThesisDto createThesis(CreateThesisDto create) {
        Thesis thesis = new Thesis();

        thesis.setTitle(create.getTitle());
        thesis.setContent(create.getContent());
        thesis.setUploadDate(create.getUploadDate());

        // Assuming the studentId and applicationId are already the objects themselves
//        Students student = create.getStudentId();
//        thesis.setStudentIdis(student);
//
//        DiplomaAssignment application = create.getApplicationId();
//        thesis.setApplicationIds(application);

  //     Review review = create.getReview();
  //     thesis.setReviewIds(review);
        thesis.setReviewIds(create.getReview());



 //       ThesisDefense defense = create.getDefense();
 //       thesis.setDefense(defense);


        thesis = thesisRepository.save(thesis);


        CreateThesisDto resultDto = new CreateThesisDto();
        resultDto.setTitle(thesis.getTitle());
        resultDto.setContent(thesis.getContent());
        resultDto.setUploadDate(thesis.getUploadDate());

        resultDto.setReview(thesis.getReviewIds());
 //       resultDto.setStudentId(thesis.getStudentIdis());
 //       resultDto.setApplicationId(thesis.getApplicationIds());
 //       resultDto.setDefense(thesis.getDefense());

        return resultDto;
    }



    // @Override
   // public CreateThesisDto createThesis(CreateThesisDto createThesis) {
   //     //return null;
   //     return modelMapper
   //             .map(thesisRepository.save(modelMapper.map(createThesis, Thesis.class)), CreateThesisDto.class);
   // }
   @Override
   public UpdateThesisDto updateThesis(long id, UpdateThesisDto updateThesisDto) {
       // Извличане на съществуващия обект Thesis от базата данни
       Thesis thesis = thesisRepository.findById(id)
               .orElseThrow(() -> new ThesisNotFoundException("Thesis with ID " + id + " not found"));


           thesis.setTitle(updateThesisDto.getTitle());
           thesis.setContent(updateThesisDto.getContent());
           thesis.setUploadDate(updateThesisDto.getUploadDate());


//       if (updateThesisDto.getStudentId() != null) {
//           thesis.setStudentIdis(updateThesisDto.getStudentId());
//       }
//
//       if (updateThesisDto.getApplicationId() != null) {
//           thesis.setApplicationIds(updateThesisDto.getApplicationId());
//       }


           thesis.setReviewIds(updateThesisDto.getReviewId());


//       if (updateThesisDto.getDefense() != null) {
//           thesis.setDefense(updateThesisDto.getDefense());
//       }


       thesis = thesisRepository.save(thesis);


       UpdateThesisDto resultDto = new UpdateThesisDto();
       //resultDto.setId(thesis.getId());
       resultDto.setTitle(thesis.getTitle());
       resultDto.setContent(thesis.getContent());
       resultDto.setUploadDate(thesis.getUploadDate());
//       resultDto.setStudentId(thesis.getStudentIdis());
//       resultDto.setApplicationId(thesis.getApplicationIds());
       resultDto.setReviewId(thesis.getReviewIds());
//       resultDto.setDefense(thesis.getDefense());

       return resultDto;
   }


   // @Override
   // public UpdateThesisDto updateThesis(long id, UpdateThesisDto updateThesis) {
   //     Thesis thesis = modelMapper.map(getThesisById(id), Thesis.class);
//
   //     thesis.setTitle(updateThesis.getTitle());
   //     thesis.setContent(updateThesis.getContent());
//
//
   //     thesis.setUploadDate(updateThesis.getUploadDate());
   //     thesis.setStudentIds(updateThesis.getStudentId());
//
   //     thesis.setApplicationIds(updateThesis.getApplicationId());
   //     thesis.setReviewIds(updateThesis.getReviewId());
   //     return modelMapper.map(thesisRepository.save(thesis), UpdateThesisDto.class);
   // }

    @Override
    public void deleteThesis(long id) {
        thesisRepository.deleteById(id);
    }

    @Override
    public int countNegativeReviews(ReviewConclusion negativeReview) {
        return thesisRepository.countNegativeReviews(negativeReview);
    }

}

