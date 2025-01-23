package com.inf.Graduation.serviceimpl;

import com.inf.Graduation.data.dto.ThesisDefense.CreateThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.ThesisDefenseDto;
import com.inf.Graduation.data.dto.ThesisDefense.UpdateThesisDefenseDto;
import com.inf.Graduation.data.entity.ThesisDefense;
import com.inf.Graduation.data.repository.ThesisDefenseRepository;
import com.inf.Graduation.exception.ThesisDefenseNotFoundException;
import com.inf.Graduation.service.impl.ThesisDefenseServiceImpl;
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

public class ThesisDefenseServiceImplTest {

    @Mock
    private ThesisDefenseRepository thesisDefenseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ThesisDefenseServiceImpl thesisDefenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetThesisDefense() {

        ThesisDefense thesisDefense = new ThesisDefense();
        thesisDefense.setId(1L);
        thesisDefense.setGrade(5.5);

        when(thesisDefenseRepository.findAll()).thenReturn(List.of(thesisDefense));

        ThesisDefenseDto thesisDefenseDto = new ThesisDefenseDto();
        thesisDefenseDto.setId(1L);
        thesisDefenseDto.setGrade(5.5);

        when(modelMapper.map(thesisDefense, ThesisDefenseDto.class)).thenReturn(thesisDefenseDto);


        List<ThesisDefenseDto> result = thesisDefenseService.getThesisDefense();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5.5, result.get(0).getGrade());
        verify(thesisDefenseRepository, times(1)).findAll();
    }

    @Test
    void testGetThesisDefenseById() {

        ThesisDefense thesisDefense = new ThesisDefense();
        thesisDefense.setId(1L);
        thesisDefense.setGrade(5.5);

        when(thesisDefenseRepository.findById(1L)).thenReturn(Optional.of(thesisDefense));

        ThesisDefenseDto thesisDefenseDto = new ThesisDefenseDto();
        thesisDefenseDto.setId(1L);
        thesisDefenseDto.setGrade(5.5);

        when(modelMapper.map(thesisDefense, ThesisDefenseDto.class)).thenReturn(thesisDefenseDto);


        ThesisDefenseDto result = thesisDefenseService.getThesisDefenseById(1L);


        assertNotNull(result);
        assertEquals(5.5, result.getGrade());
        verify(thesisDefenseRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateThesisDefense() {

        CreateThesisDefenseDto createDto = new CreateThesisDefenseDto();
        createDto.setGrade(6.0);

        ThesisDefense thesisDefense = new ThesisDefense();
        thesisDefense.setGrade(6.0);

        when(thesisDefenseRepository.save(any(ThesisDefense.class))).thenReturn(thesisDefense);


        CreateThesisDefenseDto result = thesisDefenseService.createThesisDefense(createDto);


        assertNotNull(result);
        assertEquals(6.0, result.getGrade());
        verify(thesisDefenseRepository, times(1)).save(any(ThesisDefense.class));
    }

    @Test
    void testUpdateThesisDefense() {

        ThesisDefense existingThesisDefense = new ThesisDefense();
        existingThesisDefense.setId(1L);
        existingThesisDefense.setGrade(5.0);

        when(thesisDefenseRepository.findById(1L)).thenReturn(Optional.of(existingThesisDefense));

        ThesisDefense updatedThesisDefense = new ThesisDefense();
        updatedThesisDefense.setId(1L);
        updatedThesisDefense.setGrade(6.0);

        when(thesisDefenseRepository.save(existingThesisDefense)).thenReturn(updatedThesisDefense);


        UpdateThesisDefenseDto updateDto = new UpdateThesisDefenseDto();
        updateDto.setGrade(6.0);
        UpdateThesisDefenseDto result = thesisDefenseService.updateThesisDefense(1L, updateDto);


        assertNotNull(result);
        assertEquals(6.0, result.getGrade());
        verify(thesisDefenseRepository, times(1)).findById(1L);
        verify(thesisDefenseRepository, times(1)).save(existingThesisDefense);
    }

    @Test
    void testDeleteThesisDefense() {

        long id = 1L;

        doNothing().when(thesisDefenseRepository).deleteById(id);


        thesisDefenseService.deleteThesisDefense(id);


        verify(thesisDefenseRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindDefendedThesesByGrade() {

        double minGrade = 5.0;
        double maxGrade = 6.0;
        ThesisDefense thesisDefense1 = new ThesisDefense();
        thesisDefense1.setId(1L);
        thesisDefense1.setGrade(5.5);

        ThesisDefense thesisDefense2 = new ThesisDefense();
        thesisDefense2.setId(2L);
        thesisDefense2.setGrade(5.8);

        when(thesisDefenseRepository.findDefendedThesesByGrade(minGrade, maxGrade))
                .thenReturn(List.of(thesisDefense1, thesisDefense2));


        List<ThesisDefense> result = thesisDefenseService.findDefendedThesesByGrade(minGrade, maxGrade);


        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(td -> td.getGrade() >= minGrade && td.getGrade() <= maxGrade));
        verify(thesisDefenseRepository, times(1)).findDefendedThesesByGrade(minGrade, maxGrade);
    }

    @Test
    void testFindAverageDefensesByPeriod() {

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        double average = 5.75;

        when(thesisDefenseRepository.findAverageDefensesByPeriod(startDate, endDate)).thenReturn(average);


        double result = thesisDefenseService.findAverageDefensesByPeriod(startDate, endDate);


        assertEquals(average, result);
        verify(thesisDefenseRepository, times(1)).findAverageDefensesByPeriod(startDate, endDate);
    }

    @Test
    void testCountSuccessfulDefensesBySupervisor() {

        long professorId = 1L;
        int successfulDefensesCount = 3;

        when(thesisDefenseRepository.countSuccessfulDefensesBySupervisor(professorId))
                .thenReturn(successfulDefensesCount);


        int result = thesisDefenseService.countSuccessfulDefensesBySupervisor(professorId);


        assertEquals(successfulDefensesCount, result);
        verify(thesisDefenseRepository, times(1)).countSuccessfulDefensesBySupervisor(professorId);
    }

    @Test
    void testGetThesisDefenseById_NotFound() {

        long invalidId = 999L;

        when(thesisDefenseRepository.findById(invalidId)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> thesisDefenseService.getThesisDefenseById(invalidId));
    }

    @Test
    void testCreateThesisDefense_ExceptionHandling() {

        CreateThesisDefenseDto createDto = new CreateThesisDefenseDto();
        createDto.setGrade(4.5);

        // Simulating database error
        when(thesisDefenseRepository.save(any(ThesisDefense.class))).thenThrow(new RuntimeException("Database error"));


        assertThrows(RuntimeException.class, () -> thesisDefenseService.createThesisDefense(createDto));
    }

    @Test
    void testUpdateThesisDefense_NotFound() {

        long invalidId = 999L;
        UpdateThesisDefenseDto updateDto = new UpdateThesisDefenseDto();
        updateDto.setGrade(6.0);

        when(thesisDefenseRepository.findById(invalidId)).thenReturn(Optional.empty());


        assertThrows(ThesisDefenseNotFoundException.class, () -> thesisDefenseService.updateThesisDefense(invalidId, updateDto));
    }


}
