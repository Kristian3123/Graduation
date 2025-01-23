package com.inf.Graduation.serviceimpl;



import com.inf.Graduation.data.dto.DiplomaAssignment.CreateDiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.DiplomaAssignmentDto;
import com.inf.Graduation.data.dto.DiplomaAssignment.UpdateDiplomaAssignmentDto;
import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.data.repository.DiplomaAssignmentRepository;
import com.inf.Graduation.data.repository.ThesisDefenseRepository;
import com.inf.Graduation.service.impl.DiplomaAssignmentServiceImpl;
import com.inf.Graduation.service.impl.ThesisDefenseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiplomaAssignmentImplServiceTest {
    @Mock
    private DiplomaAssignmentRepository diplomaAssignmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DiplomaAssignmentServiceImpl diplomaAssignmentService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDiplomaAssignment() {
        // Arrange
        DiplomaAssignment diplomaAssignment = new DiplomaAssignment();
        diplomaAssignment.setId(1L);
        diplomaAssignment.setTitle("Test Title");

        when(diplomaAssignmentRepository.findAll()).thenReturn(List.of(diplomaAssignment));

        DiplomaAssignmentDto diplomaAssignmentDto = new DiplomaAssignmentDto();
        diplomaAssignmentDto.setId(1L);
        diplomaAssignmentDto.setTitle("Test Title");

        when(modelMapper.map(diplomaAssignment, DiplomaAssignmentDto.class)).thenReturn(diplomaAssignmentDto);


        List<DiplomaAssignmentDto> result = diplomaAssignmentService.getDiplomaAssignment();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
        verify(diplomaAssignmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDiplomaAssignmentById() {

        DiplomaAssignment diplomaAssignment = new DiplomaAssignment();
        diplomaAssignment.setId(1L);
        diplomaAssignment.setTitle("Test Title");

        when(diplomaAssignmentRepository.findById(1L)).thenReturn(Optional.of(diplomaAssignment));

        DiplomaAssignmentDto diplomaAssignmentDto = new DiplomaAssignmentDto();
        diplomaAssignmentDto.setId(1L);
        diplomaAssignmentDto.setTitle("Test Title");

        when(modelMapper.map(diplomaAssignment, DiplomaAssignmentDto.class)).thenReturn(diplomaAssignmentDto);


        DiplomaAssignmentDto result = diplomaAssignmentService.getDiplomaAssignmentById(1L);


        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        verify(diplomaAssignmentRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateDiplomaAssignment() {

        CreateDiplomaAssignmentDto createDto = new CreateDiplomaAssignmentDto();
        createDto.setTitle("Test Title");

        DiplomaAssignment diplomaAssignment = new DiplomaAssignment();
        diplomaAssignment.setTitle("Test Title");

        when(diplomaAssignmentRepository.save(any(DiplomaAssignment.class))).thenReturn(diplomaAssignment);


        CreateDiplomaAssignmentDto result = diplomaAssignmentService.createDiplomaAssignment(createDto);


        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        verify(diplomaAssignmentRepository, times(1)).save(any(DiplomaAssignment.class));
    }

    @Test
    void testUpdateDiplomaAssignment() {

        DiplomaAssignment existingDiplomaAssignment = new DiplomaAssignment();
        existingDiplomaAssignment.setId(1L);
        existingDiplomaAssignment.setTitle("Old Title");

        when(diplomaAssignmentRepository.findById(1L)).thenReturn(Optional.of(existingDiplomaAssignment));

        DiplomaAssignment updatedDiplomaAssignment = new DiplomaAssignment();
        updatedDiplomaAssignment.setId(1L);
        updatedDiplomaAssignment.setTitle("Updated Title");

        when(diplomaAssignmentRepository.save(existingDiplomaAssignment)).thenReturn(updatedDiplomaAssignment);


       UpdateDiplomaAssignmentDto updateDto = new UpdateDiplomaAssignmentDto();
        updateDto.setTitle("Updated Title");
        UpdateDiplomaAssignmentDto result = diplomaAssignmentService.updateDiplomaAssignment(1L, updateDto);


        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(diplomaAssignmentRepository, times(1)).findById(1L);
        verify(diplomaAssignmentRepository, times(1)).save(existingDiplomaAssignment);
    }

    @Test
    void testDeleteDiplomaAssignment() {

        long id = 1L;

        doNothing().when(diplomaAssignmentRepository).deleteById(id);


        diplomaAssignmentService.deleteDiplomaAssignment(id);


        verify(diplomaAssignmentRepository, times(1)).deleteById(id);
    }


    @Test
    void testFindAllApprovedThesisAssignments() {

        ApplicationStatus approvedStatus = ApplicationStatus.APPROVED;
        DiplomaAssignment diplomaAssignment1 = new DiplomaAssignment();
        diplomaAssignment1.setId(1L);
        diplomaAssignment1.setStatus(approvedStatus);

        DiplomaAssignment diplomaAssignment2 = new DiplomaAssignment();
        diplomaAssignment2.setId(2L);
        diplomaAssignment2.setStatus(approvedStatus);

        when(diplomaAssignmentRepository.findAllApprovedThesisAssignments(approvedStatus))
                .thenReturn(List.of(diplomaAssignment1, diplomaAssignment2));


        List<DiplomaAssignment> result = diplomaAssignmentService.findAllApprovedThesisAssignments(approvedStatus);


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(approvedStatus, result.get(0).getStatus());
        verify(diplomaAssignmentRepository, times(1)).findAllApprovedThesisAssignments(approvedStatus);
    }
    @Test
    void testFindThesisByKeyword() {

        String keyword = "Machine Learning";
        DiplomaAssignment diplomaAssignment = new DiplomaAssignment();
        diplomaAssignment.setId(1L);
        diplomaAssignment.setTitle("Thesis on Machine Learning");

        when(diplomaAssignmentRepository.findThesisByKeyword(keyword))
                .thenReturn(List.of(diplomaAssignment));


        List<DiplomaAssignment> result = diplomaAssignmentService.findThesisByKeyword(keyword);


        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getTitle().contains(keyword));
        verify(diplomaAssignmentRepository, times(1)).findThesisByKeyword(keyword);
    }


    @Test
    void testFindThesisBySupervisorAndStatus() {

        Long supervisorId = 10L;
        ApplicationStatus approvedStatus = ApplicationStatus.APPROVED;

        DiplomaAssignment diplomaAssignment = new DiplomaAssignment();
        diplomaAssignment.setId(1L);
        diplomaAssignment.setStatus(approvedStatus);
        diplomaAssignment.setSupervisorId(new Teachers());
        diplomaAssignment.getSupervisorId().setId(supervisorId);

        when(diplomaAssignmentRepository.findThesisBySupervisorAndStatus(supervisorId, approvedStatus))
                .thenReturn(List.of(diplomaAssignment));


        List<DiplomaAssignment> result = diplomaAssignmentService.findThesisBySupervisorAndStatus(supervisorId, approvedStatus);


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(supervisorId, result.get(0).getSupervisorId().getId());
        assertEquals(approvedStatus, result.get(0).getStatus());
        verify(diplomaAssignmentRepository, times(1)).findThesisBySupervisorAndStatus(supervisorId, approvedStatus);
    }


}
