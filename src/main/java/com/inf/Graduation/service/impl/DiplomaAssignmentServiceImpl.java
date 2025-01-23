package com.inf.Graduation.service.impl;

import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.UpdateDiplomaAssignmentDto;

import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.data.repository.DiplomaAssignmentRepository;
import com.inf.Graduation.exception.DiplomaAssignmentNotFoundException;
import com.inf.Graduation.service.DiplomaAssignmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiplomaAssignmentServiceImpl implements DiplomaAssignmentService {

    private final DiplomaAssignmentRepository diplomaAssignmentRepository;



    private final ModelMapper modelMapper;

    @Override
    public List<DiplomaAssignmentDto> getDiplomaAssignment() {
        return diplomaAssignmentRepository.findAll().stream()
                .map(this::convertToDiplomaAssignmentDto)
                .collect(Collectors.toList());
    }

    private DiplomaAssignmentDto convertToDiplomaAssignmentDto(DiplomaAssignment diplomaAssignment) {

        DiplomaAssignmentDto dto = new DiplomaAssignmentDto();
        dto.setId(diplomaAssignment.getId());
        dto.setTitle(diplomaAssignment.getTitle());
        dto.setGoal(diplomaAssignment.getGoal());
        dto.setTasks(diplomaAssignment.getTasks());
        dto.setTechnologies(diplomaAssignment.getTechnologies());
        dto.setStatus(diplomaAssignment.getStatus());


        dto.setSupervisorIds(diplomaAssignment.getSupervisorId());


        if (diplomaAssignment.getThesis() != null) {
            dto.setThesis(diplomaAssignment.getThesis());
        }

        return dto;
    }

    @Override
    public DiplomaAssignmentDto getDiplomaAssignmentById(long id) {
        DiplomaAssignment diplomaAssignment = diplomaAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid DiplomaAssignment Id:" + id));

        // Ръчно преобразуване на полетата
        DiplomaAssignmentDto dto = new DiplomaAssignmentDto();
        dto.setId(diplomaAssignment.getId());
        dto.setTitle(diplomaAssignment.getTitle());
        dto.setGoal(diplomaAssignment.getGoal());
        dto.setTasks(diplomaAssignment.getTasks());
        dto.setTechnologies(diplomaAssignment.getTechnologies());
        dto.setStatus(diplomaAssignment.getStatus());
        dto.setSupervisorIds(diplomaAssignment.getSupervisorId());

        // Преобразуване на Thesis обект (ако е необходимо)
        if (diplomaAssignment.getThesis() != null) {
            dto.setThesis(diplomaAssignment.getThesis());
        }

        return dto;
    }


    // @Override
   // public CreateDiplomaAssignmentDto createDiplomaAssignment(CreateDiplomaAssignmentDto createDiplomaAssignment) {
   //     //return null;
   //     return modelMapper
   //             .map(diplomaAssignmentRepository.save(modelMapper.map(createDiplomaAssignment, DiplomaAssignment.class)), CreateDiplomaAssignmentDto.class);
   // }


    // public CreateDiplomaAssignmentDto createDiplomaAssignment(CreateDiplomaAssignmentDto createDiplomaAssignment) {
    //     // Локално мапиране на DTO към Entity
    //     DiplomaAssignment diplomaAssignment = mapToEntity(createDiplomaAssignment);

    //     // Записване на дипломната работа в базата данни
    //     diplomaAssignment = diplomaAssignmentRepository.save(diplomaAssignment);

    //     // Връщане на резултата като DTO
    //     return modelMapper.map(diplomaAssignment, CreateDiplomaAssignmentDto.class);
    // }

    @Override
    public CreateDiplomaAssignmentDto createDiplomaAssignment(CreateDiplomaAssignmentDto create) {

        DiplomaAssignment diplomaAssignment = new DiplomaAssignment();

        diplomaAssignment.setTitle(create.getTitle());
        diplomaAssignment.setGoal(create.getGoal());
        diplomaAssignment.setTasks(create.getTasks());
        diplomaAssignment.setTechnologies(create.getTechnologies());


        Students student = create.getStudentIds();
        Teachers supervisor = create.getSupervisorIds();
       // Thesis thesis = create.getThesis();

        diplomaAssignment.setStudentId(student);
        diplomaAssignment.setSupervisorId(supervisor);
      //  diplomaAssignment.setThesis(thesis);


        diplomaAssignment.setStatus(create.getStatus());


        diplomaAssignment = diplomaAssignmentRepository.save(diplomaAssignment);


        CreateDiplomaAssignmentDto resultDto = new CreateDiplomaAssignmentDto();
        resultDto.setTitle(diplomaAssignment.getTitle());
        resultDto.setGoal(diplomaAssignment.getGoal());
        resultDto.setTasks(diplomaAssignment.getTasks());
        resultDto.setTechnologies(diplomaAssignment.getTechnologies());


        resultDto.setStudentIds(diplomaAssignment.getStudentId());
        resultDto.setSupervisorIds(diplomaAssignment.getSupervisorId());
     //   resultDto.setThesis(diplomaAssignment.getThesis());
        resultDto.setStatus(diplomaAssignment.getStatus());

        return resultDto;
    }



// @Override
// public CreateDiplomaAssignmentDto createDiplomaAssignment(CreateDiplomaAssignmentDto createDiplomaAssignment) {
//     //return null;
//     return modelMapper
//             .map(diplomaAssignmentRepository.save(modelMapper.map(createDiplomaAssignment, DiplomaAssignment.class)), CreateDiplomaAssignmentDto.class);
// }
 //  private DiplomaAssignment mapToEntity(CreateDiplomaAssignmentDto createDiplomaAssignment) {
 //      DiplomaAssignment diplomaAssignment = modelMapper.map(createDiplomaAssignment, DiplomaAssignment.class);

 //      // Ръчно мапиране за специфични полета
 //      diplomaAssignment.getStudent().setId(createDiplomaAssignment.getStudentId().getId());
 //      diplomaAssignment.getSupervisor().setId(createDiplomaAssignment.getSupervisorId().getId());

 //      return diplomaAssignment;
 //  }
 @Override
 public UpdateDiplomaAssignmentDto updateDiplomaAssignment(long id, UpdateDiplomaAssignmentDto update) {

     DiplomaAssignment diplomaAssignment = diplomaAssignmentRepository.findById(id)
             .orElseThrow(() -> new DiplomaAssignmentNotFoundException("DiplomaAssignment with ID " + id + " not found"));


     if (update.getTitle() != null) {
         diplomaAssignment.setTitle(update.getTitle());
     }

     if (update.getGoal() != null) {
         diplomaAssignment.setGoal(update.getGoal());
     }

     if (update.getTasks() != null) {
         diplomaAssignment.setTasks(update.getTasks());
     }

     if (update.getTechnologies() != null) {
         diplomaAssignment.setTechnologies(update.getTechnologies());
     }

 //    if (update.getStudentIds() != null) {
 //        diplomaAssignment.setStudentId(update.getStudentIds());
 //    }

     if (update.getSupervisorIds() != null) {
         diplomaAssignment.setSupervisorId(update.getSupervisorIds());
     }

 //   if (update.getThesis() != null) {
 //       diplomaAssignment.setThesis(update.getThesis());
 //   }

     if (update.getStatus() != null) {
         diplomaAssignment.setStatus(update.getStatus());
     }

     // Записване на актуализирания обект в базата данни
     diplomaAssignment = diplomaAssignmentRepository.save(diplomaAssignment);

     // Създаване на резултата DTO
     UpdateDiplomaAssignmentDto resultDto = new UpdateDiplomaAssignmentDto();
     resultDto.setId(diplomaAssignment.getId());
     resultDto.setTitle(diplomaAssignment.getTitle());
     resultDto.setGoal(diplomaAssignment.getGoal());
     resultDto.setTasks(diplomaAssignment.getTasks());
     resultDto.setTechnologies(diplomaAssignment.getTechnologies());
 //    resultDto.setStudentIds(diplomaAssignment.getStudentId());
     resultDto.setSupervisorIds(diplomaAssignment.getSupervisorId());
 //    resultDto.setThesis(diplomaAssignment.getThesis());
     resultDto.setStatus(diplomaAssignment.getStatus());


     return resultDto;
 }





    //  @Override
  //  public UpdateDiplomaAssignmentDto updateDiplomaAssignment(long id, UpdateDiplomaAssignmentDto updateDiplomaAssignment) {
  //      DiplomaAssignment diplomaAssignment = modelMapper.map(getDiplomaAssignmentById(id), DiplomaAssignment.class);
  //      diplomaAssignment.setTitle(updateDiplomaAssignment.getTitle());
  //      diplomaAssignment.setGoal(updateDiplomaAssignment.getGoal());
  //      diplomaAssignment.setTasks(updateDiplomaAssignment.getTasks());
//
  //      diplomaAssignment.setTechnologies(updateDiplomaAssignment.getTechnologies());
  //      diplomaAssignment.setStudentIds(updateDiplomaAssignment.getStudentId());
  //      diplomaAssignment.setSupervisorIds(updateDiplomaAssignment.getSupervisorId());
//
  //      diplomaAssignment.setStatus(updateDiplomaAssignment.getStatus());
//
  //      return modelMapper.map(diplomaAssignmentRepository.save(diplomaAssignment), UpdateDiplomaAssignmentDto.class);
  //  }

    @Override
    public void deleteDiplomaAssignment(long id) {
        diplomaAssignmentRepository.deleteById(id);
    }

  // @Override
  // public List<DiplomaAssignment> findByApprovedTrue() {
  //     return diplomaAssignmentRepository.findByApprovedTrue();
  // }

  // @Override
  // public List<DiplomaAssignment> findByTitleContaining(String keyword) {
  //     return diplomaAssignmentRepository.findByTitleContaining(keyword);
  // }

  // @Override
  // public List<DiplomaAssignment> findByTeacherIdAndApprovedTrue(Long teacherId) {
  //     return diplomaAssignmentRepository.findByTeacherIdAndApprovedTrue(teacherId);
  // }

 //   @Override
 //   public List<DiplomaAssignmentDto> getApprovedAssignments() {
 //       // Връща одобрените задания
 //       return List.of(); // Примерен код, свържи с база данни
 //   }
//
 //   @Override
 //   public void addAssignment(DiplomaAssignmentDto assignmentDTO) {
 //       // Добавяне на ново задание
 //   }
//
 //   @Override
 //   public List<DiplomaAssignmentDto> getAllAssignments() {
 //       // Връща всички задания
 //       return List.of(); // Примерен код, свържи с база данни
 //   }


    @Override
    public List<DiplomaAssignment> findAllApprovedThesisAssignments(ApplicationStatus approved) {
        return diplomaAssignmentRepository.findAllApprovedThesisAssignments(approved);
    }

    @Override
    public List<DiplomaAssignment> findThesisByKeyword(String keyword) {
        return diplomaAssignmentRepository.findThesisByKeyword(keyword);
    }

    @Override
    public List<DiplomaAssignment> findThesisBySupervisorAndStatus(Long professorId, ApplicationStatus approved) {
        return diplomaAssignmentRepository.findThesisBySupervisorAndStatus(professorId, approved);
    }
}
