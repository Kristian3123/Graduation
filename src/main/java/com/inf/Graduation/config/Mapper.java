package com.inf.Graduation.config;

import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.UpdateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.web.view.model.DiplomaAssignment.DiplomaAssignmentViewModel;
import com.inf.Graduation.web.view.model.DiplomaAssignment.UpdateDiplomaAssignmentViewModel;
import com.inf.Graduation.web.view.model.ThesisDefense.ThesisDefenseViewModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class Mapper {



     @Bean
    public ModelMapper modelMapper() {

         ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration()
                 .setFieldMatchingEnabled(true)
                 .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);


         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);




         return new ModelMapper();
    }











    // modelMapper.typeMap(DiplomaAssignmentDto.class, DiplomaAssignmentViewModel.class)
    //         .addMappings(mapper -> {
    //             mapper.using(ctx -> {
    //                 // Custom logic to determine the appropriate value
    //                 return ctx.getSource() != null ? ctx.getSource().getStudentById() : null;
    //             }).map(DiplomaAssignmentDto::getStudentId, DiplomaAssignmentViewModel::setStudentIds);
    //         });
//
    //  modelMapper.typeMap(DiplomaAssignmentDto.class, DiplomaAssignmentViewModel.class)
    //          .addMappings(mapper -> {
    //              mapper.map(DiplomaAssignmentDto::getStudentId, DiplomaAssignmentViewModel::setStudentIds);
    //          });
//
    //  modelMapper.typeMap(DiplomaAssignmentDto.class, DiplomaAssignmentViewModel.class)
    //          .addMappings(mapper -> {
    //              mapper.skip(DiplomaAssignmentViewModel::setStudentIds); // Skip conflicting field
    //          });











  // public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
  //     return source
  //             .stream()
  //             .map(element -> new ModelMapper().map(element, targetClass))
  //             .collect(Collectors.toList());
  // }



    // @Bean
  // public ModelMapper modelMapper() {
  //     ModelMapper modelMapper = new ModelMapper();
//
  //     // Може да зададеш MatchingStrategy ако е необходимо
  //     modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
  //     return modelMapper;
  // }
}
