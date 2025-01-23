package com.inf.Graduation.service.impl;

import com.inf.Graduation.data.dto.Teachers.CreateTeachersDto;
import com.inf.Graduation.data.dto.Teachers.TeachersDto;
import com.inf.Graduation.data.dto.Teachers.UpdateTeachersDto;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.repository.TeachersRepository;
import com.inf.Graduation.exception.TeacherNotFoundException;
import com.inf.Graduation.service.TeachersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeachersService {

    private final TeachersRepository teacherRepository;
    private final ModelMapper modelMapper;

   // public TeacherServiceImpl(TeachersRepository teacherRepository, ModelMapper modelMapper) {
   //     this.teacherRepository = teacherRepository;
   //     this.modelMapper = modelMapper;
   // }

    public List<TeachersDto> getTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToTeachersDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeachersDto> getTeachersByEmail(String email) {
        return teacherRepository.findByEmail(email).stream()
                .map(this::convertToTeachersDto)
                .collect(Collectors.toList());
    }

    private TeachersDto convertToTeachersDto(Teachers teacher) {
        return modelMapper.map(teacher, TeachersDto.class);
    }

    @Override
    public TeachersDto getTeacherById(long id) {
        //return null;
        return modelMapper.map(teacherRepository.findById(id)
              //  .orElseThrow(() -> new RuntimeException("Invalid teacher Id:" + id)), TeachersDto.class);
                .orElseThrow(() -> new TeacherNotFoundException("Invalid teacher Id:" + id)), TeachersDto.class);
    }
    @Override
    public CreateTeachersDto createTeacher(CreateTeachersDto createTeacher) {

        Teachers teacher = new Teachers();
        teacher.setName(createTeacher.getName());
        teacher.setEmail(createTeacher.getEmail());
        teacher.setPosition(createTeacher.getPosition());
//teacher.setSupervisedAssignmentIds(createTeacher.getSupervisedAssignmentIds());
//       teacher.setDefensesIds(createTeacher.getDefensesIds());
//       teacher.setReview(createTeacher.getReview());


        teacher = teacherRepository.save(teacher);


        CreateTeachersDto resultDto = new CreateTeachersDto();
        resultDto.setName(teacher.getName());
        resultDto.setEmail(teacher.getEmail());
        resultDto.setPosition(teacher.getPosition());
//        resultDto.setSupervisedAssignmentIds(teacher.getSupervisedAssignmentIds());
//        resultDto.setDefensesIds(teacher.getDefensesIds());
//        resultDto.setReview(teacher.getReview());

        return resultDto;
    }

   // @Override
   // public CreateTeachersDto createTeacher(CreateTeachersDto createTeacher) {
   //    // return teacherRepository.save(modelMapper.map(createTeacher, Teachers.class));
   //     return modelMapper
   //             .map(teacherRepository.save(modelMapper.map(createTeacher, Teachers.class)), CreateTeachersDto.class);
   // }

    @Override
    public UpdateTeachersDto updateTeacher(long id, UpdateTeachersDto updateTeachersDto) {
        // Извличане на съществуващия обект Teachers от базата данни
        Teachers teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with ID " + id + " not found"));

        // Актуализиране на полетата от DTO
        if (updateTeachersDto.getName() != null) {
            teacher.setName(updateTeachersDto.getName());
        }

        if (updateTeachersDto.getEmail() != null) {
            teacher.setEmail(updateTeachersDto.getEmail());
        }

        if (updateTeachersDto.getPosition() != null) {
            teacher.setPosition(updateTeachersDto.getPosition());
        }

//        if (updateTeachersDto.getSupervisedAssignmentIds() != null) {
//            teacher.setSupervisedAssignmentIds(updateTeachersDto.getSupervisedAssignmentIds());
//        }
//
//        if (updateTeachersDto.getDefensesIds() != null) {
//            teacher.setDefensesIds(updateTeachersDto.getDefensesIds());
//        }
//        if (updateTeachersDto.getReview() != null) {
//            teacher.setReview(updateTeachersDto.getReview());
//        }

        // Запазване на актуализирания обект в базата данни
        teacher = teacherRepository.save(teacher);

        // Създаване на DTO за връщане с актуализираните стойности
        UpdateTeachersDto resultDto = new UpdateTeachersDto();
        resultDto.setName(teacher.getName());
        resultDto.setEmail(teacher.getEmail());
        resultDto.setPosition(teacher.getPosition());
//        resultDto.setSupervisedAssignmentIds(teacher.getSupervisedAssignmentIds());
//        resultDto.setDefensesIds(teacher.getDefensesIds());
//        resultDto.setReview(teacher.getReview());

        return resultDto;
    }


    //@Override
   //public UpdateTeachersDto updateTeacher(long id, UpdateTeachersDto updateTeachersDto) {
   //    Teachers teacher = modelMapper.map(getTeacherById(id), Teachers.class);
   //    teacher.setName(updateTeachersDto.getName());
   //    teacher.setEmail(updateTeachersDto.getEmail());

   //    teacher.setSupervisedAssignmentIds(updateTeachersDto.getSupervisedAssignmentIds());
   //    teacher.setDefensesIds(updateTeachersDto.getDefensesIds());
   //    return modelMapper.map(teacherRepository.save(teacher), UpdateTeachersDto.class);
   //}

    @Override
    public void deleteTeacher(long id) {
        teacherRepository.deleteById(id);
    }





}
