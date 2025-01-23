package com.inf.Graduation.serviceimpl;


import com.inf.Graduation.data.dto.Thesis.CreateThesisDto;
import com.inf.Graduation.data.dto.Thesis.ThesisDto;
import com.inf.Graduation.data.dto.Thesis.UpdateThesisDto;
import com.inf.Graduation.data.entity.*;
import com.inf.Graduation.data.repository.ThesisRepository;
import com.inf.Graduation.exception.ThesisNotFoundException;
import com.inf.Graduation.service.impl.ThesisServiceImpl;
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

public class ThesisServiceImplTest {

    @Mock
    private ThesisRepository thesisRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ThesisServiceImpl thesisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetThesis() {

        Thesis thesis = new Thesis();
        thesis.setId(1L);
        thesis.setTitle("Test Thesis");

        when(thesisRepository.findAll()).thenReturn(List.of(thesis));

        ThesisDto thesisDto = new ThesisDto();
        thesisDto.setId(1L);
        thesisDto.setTitle("Test Thesis");

        when(modelMapper.map(thesis, ThesisDto.class)).thenReturn(thesisDto);


        List<ThesisDto> result = thesisService.getThesis();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Thesis", result.get(0).getTitle());
        verify(thesisRepository, times(1)).findAll();
    }

    @Test
    void testGetThesisById() {

        Thesis thesis = new Thesis();
        thesis.setId(1L);
        thesis.setTitle("Test Thesis");

        when(thesisRepository.findById(1L)).thenReturn(Optional.of(thesis));

        ThesisDto thesisDto = new ThesisDto();
        thesisDto.setId(1L);
        thesisDto.setTitle("Test Thesis");

        when(modelMapper.map(thesis, ThesisDto.class)).thenReturn(thesisDto);


        ThesisDto result = thesisService.getThesisById(1L);


        assertNotNull(result);
        assertEquals("Test Thesis", result.getTitle());
        verify(thesisRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateThesis() {

        CreateThesisDto createDto = new CreateThesisDto();
        createDto.setTitle("New Thesis");

        Thesis thesis = new Thesis();
        thesis.setTitle("New Thesis");

        when(thesisRepository.save(any(Thesis.class))).thenReturn(thesis);


        CreateThesisDto result = thesisService.createThesis(createDto);


        assertNotNull(result);
        assertEquals("New Thesis", result.getTitle());
        verify(thesisRepository, times(1)).save(any(Thesis.class));
    }

    @Test
    void testUpdateThesis() {

        Thesis existingThesis = new Thesis();
        existingThesis.setId(1L);
        existingThesis.setTitle("Old Thesis");

        when(thesisRepository.findById(1L)).thenReturn(Optional.of(existingThesis));

        Thesis updatedThesis = new Thesis();
        updatedThesis.setId(1L);
        updatedThesis.setTitle("Updated Thesis");

        when(thesisRepository.save(existingThesis)).thenReturn(updatedThesis);


        UpdateThesisDto updateDto = new UpdateThesisDto();
        updateDto.setTitle("Updated Thesis");
        UpdateThesisDto result = thesisService.updateThesis(1L, updateDto);


        assertNotNull(result);
        assertEquals("Updated Thesis", result.getTitle());
        verify(thesisRepository, times(1)).findById(1L);
        verify(thesisRepository, times(1)).save(existingThesis);
    }

    @Test
    void testDeleteThesis() {

        long id = 1L;

        doNothing().when(thesisRepository).deleteById(id);


        thesisService.deleteThesis(id);


        verify(thesisRepository, times(1)).deleteById(id);
    }

    @Test
    void testCountNegativeReviews() {

        ReviewConclusion negativeReview = ReviewConclusion.NEGATIVE;
        int count = 5;

        when(thesisRepository.countNegativeReviews(negativeReview)).thenReturn(count);


        int result = thesisService.countNegativeReviews(negativeReview);


        assertEquals(count, result);
        verify(thesisRepository, times(1)).countNegativeReviews(negativeReview);
    }

    @Test
    void testGetThesisById_NotFound() {

        long invalidId = 999L;

        when(thesisRepository.findById(invalidId)).thenReturn(Optional.empty());


        assertThrows(ThesisNotFoundException.class, () -> thesisService.getThesisById(invalidId));
    }

    @Test
    void testCreateThesis_ExceptionHandling() {

        CreateThesisDto createDto = new CreateThesisDto();
        createDto.setTitle("Test Thesis");


        when(thesisRepository.save(any(Thesis.class))).thenThrow(new RuntimeException("Database error"));


        assertThrows(RuntimeException.class, () -> thesisService.createThesis(createDto));
    }
}

