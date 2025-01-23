package com.inf.Graduation.service.impl;

import com.inf.Graduation.config.Mapper;
import com.inf.Graduation.data.dto.GraduationDTO;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.repository.StudentsRepository;
import com.inf.Graduation.exception.StudentNotFoundException;
import com.inf.Graduation.service.StudentsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentsServiceImpl implements StudentsService {

private final StudentsRepository studentsRepository;
 private final ModelMapper modelMapper;
    private final Mapper mapper;

    //public StudentsServiceImpl(StudentsRepository studentsRepository, ModelMapper modelMapper) {
   //    this.studentsRepository = studentsRepository;
   //    this.modelMapper = modelMapper;
   //}

    @Override
   // public List<StudentsDto> getStudents() {
//
   //     return
   //             this.mapper.mapList(
   //                             this.studentsRepository.findAll(), StudentsDto.class);
    // }
    public List<StudentsDto> getStudents() {
        return studentsRepository.findAll().stream()
                .map(this::convertToStudentsDto)
                .collect(Collectors.toList());
    }
    private StudentsDto convertToStudentsDto(Students student) {
        return modelMapper.map(student, StudentsDto.class);
    }

    @Override
    public StudentsDto getStudentById(long id) {
        //return null;
        return modelMapper.map(studentsRepository.findById(id)
                //.orElseThrow(() -> new RuntimeException("Invalid student Id:" + id)), StudentsDto.class);
                .orElseThrow(() -> new StudentNotFoundException("Invalid student ID: " + id)), StudentsDto.class);
    }

    @Override
    public List<StudentsDto> getStudentsByFacultyNumber(String facultyNumber) {
        //return this.studentsRepository.findAll(facultyNumber);
       // return List.of();
        return studentsRepository.findStudentsByFacultyNumber(facultyNumber).stream()
                .map(this::convertToStudentsDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreateStudentsDto createStudent(CreateStudentsDto create) {

        Students student = new Students();
        student.setName(create.getName());

        student.setFacultyNumber(create.getFacultyNumber());


    //    student.setAssignmentsIds(create.getAssignmentIds());
    //    student.setThesisIds(create.getThesisIds());

    //    student.setDefenses(create.getDefenses());


        student = studentsRepository.save(student);


        CreateStudentsDto resultDto = new CreateStudentsDto();
       // resultDto.setId(student.getId());
        resultDto.setName(student.getName());

        resultDto.setFacultyNumber(student.getFacultyNumber());
    //    resultDto.setAssignmentIds(student.getAssignmentsIds());
    //    resultDto.setThesisIds(student.getThesisIds());
 //       resultDto.setDefenses(student.getDefenses());

        return resultDto;
    }

   // @Override
   // public CreateStudentsDto createStudent(CreateStudentsDto createStudent) {
   //     //return null;
   //     return modelMapper
   //     .map(studentsRepository.save(modelMapper.map(createStudent, Students.class)), CreateStudentsDto.class);
   // }
   @Override
   public UpdateStudentsDto updateStudent(long id, UpdateStudentsDto updateStudent) {

       Students student = studentsRepository.findById(id)
               .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

       student.setName(updateStudent.getName());
       student.setFacultyNumber(updateStudent.getFacultyNumber());


//      student.setAssignmentsIds(updateStudent.getAssignmentIds());
//      student.setThesisIds(updateStudent.getThesisIds());
      student.setDefenses(updateStudent.getDefenses());


       student = studentsRepository.save(student);


       UpdateStudentsDto resultDto = new UpdateStudentsDto();
       //resultDto.setId(student.getId());
       resultDto.setName(student.getName());

       resultDto.setFacultyNumber(student.getFacultyNumber());
 //      resultDto.setAssignmentIds(student.getAssignmentsIds());
 //      resultDto.setThesisIds(student.getThesisIds());
       resultDto.setDefenses(student.getDefenses());

       return resultDto;
   }

    //@Override
    //public UpdateStudentsDto updateStudent(long id, UpdateStudentsDto updateStudent) {
    //    Students student = modelMapper.map(getStudentById(id), Students.class);
    //    student.setName(updateStudent.getName());
    //    student.setFacultyNumber(updateStudent.getFacultyNumber());
    //    student.setEmail(updateStudent.getEmail());
//
    //    student.setAssignmentsIds(updateStudent.getAssignmentIds());
    //    student.setThesisIds(updateStudent.getThesisIds());
//
    //    return modelMapper.map(studentsRepository.save(student), UpdateStudentsDto.class);
    //}
//
    @Override
    public void deleteStudent(long id) {
        studentsRepository.deleteById(id);
    }





    @Override
    public List<GraduationDTO> findGraduatesByPeriod(LocalDate startDate, LocalDate endDate) {

        List<Students> studentsWithGrade = studentsRepository.findGraduatesByPeriod();

        // Generate random graduation dates within the specified range and map to GraduationDTO
        return studentsWithGrade.stream()
                .map(student -> {
                    LocalDate randomGraduationDate = generateRandomGraduationDate(startDate, endDate);
                    return new GraduationDTO(student.getId(), student.getName(), randomGraduationDate);
                })
                .collect(Collectors.toList());
    }

    private LocalDate generateRandomGraduationDate(LocalDate startDate, LocalDate endDate) {

        long minDay = startDate.toEpochDay();
        long maxDay = endDate.toEpochDay();
        long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay);
    }
  // @Override
  // public List<GraduationDTO> findGraduatesByPeriod(LocalDate startDate, LocalDate endDate) {
  //     List<Students> studentsWithGrade = studentsRepository.findGraduatesByPeriod();

  //     // Генериране на случайна дата за дипломиране
  //     return studentsWithGrade.stream()
  //             .map(student -> {
  //                 LocalDate randomGraduationDate = generateRandomGraduationDate(startDate, endDate);
  //                 return new GraduationDTO(student.getId(), student.getName(), randomGraduationDate);
  //             })
  //             .collect(Collectors.toList());
  // }

  // private LocalDate generateRandomGraduationDate(LocalDate startDate, LocalDate endDate) {
  //     long minDay = startDate.toEpochDay();
  //     long maxDay = endDate.toEpochDay();
  //     long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));
  //     return LocalDate.ofEpochDay(randomDay);
  // }

}
