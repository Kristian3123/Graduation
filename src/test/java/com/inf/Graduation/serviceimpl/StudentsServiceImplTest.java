package com.inf.Graduation.serviceimpl;


import com.inf.Graduation.data.dto.GraduationDTO;
import com.inf.Graduation.data.dto.Students.CreateStudentsDto;
import com.inf.Graduation.data.dto.Students.StudentsDto;
import com.inf.Graduation.data.dto.Students.UpdateStudentsDto;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.repository.StudentsRepository;
import com.inf.Graduation.exception.StudentNotFoundException;
import com.inf.Graduation.service.impl.StudentsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentsServiceImplTest {

    @Mock
    private StudentsRepository studentsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentsServiceImpl studentsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStudents() {

        Students student = new Students();
        student.setId(1L);
        student.setName("John Doe");

        when(studentsRepository.findAll()).thenReturn(List.of(student));

        StudentsDto studentsDto = new StudentsDto();
        studentsDto.setId(1L);
        studentsDto.setName("John Doe");

        when(modelMapper.map(student, StudentsDto.class)).thenReturn(studentsDto);


        List<StudentsDto> result = studentsService.getStudents();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(studentsRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {

        Students student = new Students();
        student.setId(1L);
        student.setName("John Doe");

        when(studentsRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentsDto studentsDto = new StudentsDto();
        studentsDto.setId(1L);
        studentsDto.setName("John Doe");

        when(modelMapper.map(student, StudentsDto.class)).thenReturn(studentsDto);


        StudentsDto result = studentsService.getStudentById(1L);


        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(studentsRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateStudent() {

        CreateStudentsDto createDto = new CreateStudentsDto();
        createDto.setName("John Doe");
       // createDto.setEmail("john.doe@example.com");
        createDto.setFacultyNumber("12345");

        Students student = new Students();
        student.setName("John Doe");
     //   student.setEmail("john.doe@example.com");
        student.setFacultyNumber("12345");

        when(studentsRepository.save(any(Students.class))).thenReturn(student);


        CreateStudentsDto result = studentsService.createStudent(createDto);


        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(studentsRepository, times(1)).save(any(Students.class));
    }

    @Test
    void testUpdateStudent() {

        Students existingStudent = new Students();
        existingStudent.setId(1L);
        existingStudent.setName("John Doe");
     //   existingStudent.setEmail("john.doe@example.com");
        existingStudent.setFacultyNumber("12345");

        when(studentsRepository.findById(1L)).thenReturn(Optional.of(existingStudent));

        Students updatedStudent = new Students();
        updatedStudent.setId(1L);
        updatedStudent.setName("John Doe Updated");
    //    updatedStudent.setEmail("john.updated@example.com");
        updatedStudent.setFacultyNumber("54321");

        when(studentsRepository.save(existingStudent)).thenReturn(updatedStudent);


        UpdateStudentsDto updateDto = new UpdateStudentsDto();
        updateDto.setName("John Doe Updated");
     //   updateDto.setEmail("john.updated@example.com");
        updateDto.setFacultyNumber("54321");

        UpdateStudentsDto result = studentsService.updateStudent(1L, updateDto);


        assertNotNull(result);
        assertEquals("John Doe Updated", result.getName());
        verify(studentsRepository, times(1)).findById(1L);
        verify(studentsRepository, times(1)).save(existingStudent);
    }

    @Test
    void testDeleteStudent() {

        long id = 1L;

        doNothing().when(studentsRepository).deleteById(id);


        studentsService.deleteStudent(id);


        verify(studentsRepository, times(1)).deleteById(id);
    }


    @Test
    void testGetStudentById_NotFound() {

        long invalidId = 999L;

        when(studentsRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentsService.getStudentById(invalidId));
    }

    @Test
    void testCreateStudent_ExceptionHandling() {

        CreateStudentsDto createDto = new CreateStudentsDto();
        createDto.setName("John Doe");
    //    createDto.setEmail("john.doe@example.com");
        createDto.setFacultyNumber("12345");

        when(studentsRepository.save(any(Students.class))).thenThrow(new RuntimeException("Database error"));


        assertThrows(RuntimeException.class, () -> studentsService.createStudent(createDto));
    }

    @Test
    void testUpdateStudent_NotFound() {

        long invalidId = 999L;
        UpdateStudentsDto updateDto = new UpdateStudentsDto();
        updateDto.setName("John Doe Updated");

        when(studentsRepository.findById(invalidId)).thenReturn(Optional.empty());


        assertThrows(StudentNotFoundException.class, () -> studentsService.updateStudent(invalidId, updateDto));
    }

    @Test
    void testFindGraduatesByPeriod() {

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Students student = new Students();
        student.setId(1L);
        student.setName("John Doe");

        when(studentsRepository.findGraduatesByPeriod()).thenReturn(List.of(student));


        List<GraduationDTO> result = studentsService.findGraduatesByPeriod(startDate, endDate);


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getStudentName());
        verify(studentsRepository, times(1)).findGraduatesByPeriod();
    }

}



