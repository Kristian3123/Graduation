package com.inf.Graduation.repository;

import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.repository.DiplomaAssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest

public class DiplomaAssignmentRepositoryTest {

    @Autowired
    private DiplomaAssignmentRepository diplomaAssignmentRepository;

    @BeforeEach
    void setUp() {

        DiplomaAssignment assignment1 = new DiplomaAssignment();
        assignment1.setTitle("Sample Thesis 1");
        assignment1.setStatus(ApplicationStatus.APPROVED);
        diplomaAssignmentRepository.save(assignment1);

        DiplomaAssignment assignment2 = new DiplomaAssignment();
        assignment2.setTitle("Keyword Thesis");
        assignment2.setStatus(ApplicationStatus.REJECTED);
        diplomaAssignmentRepository.save(assignment2);

        Teachers teacher = new Teachers();
        teacher.setId(1L);  // Manually setting the ID for testing

        DiplomaAssignment assignment3 = new DiplomaAssignment();
        assignment3.setTitle("Sample Thesis 2");
        assignment3.setStatus(ApplicationStatus.APPROVED);
        assignment3.setSupervisorId(teacher);
        diplomaAssignmentRepository.save(assignment3);
    }

    @Test
    void testFindAllApprovedThesisAssignments() {
        List<DiplomaAssignment> approvedAssignments = diplomaAssignmentRepository.findAllApprovedThesisAssignments(ApplicationStatus.APPROVED);
        assertNotNull(approvedAssignments);
        assertEquals(2, approvedAssignments.size());
    }

    @Test
    void testFindThesisByKeyword() {
        List<DiplomaAssignment> keywordAssignments = diplomaAssignmentRepository.findThesisByKeyword("Keyword");
        assertNotNull(keywordAssignments);
        assertEquals(1, keywordAssignments.size());
    }

    @Test
    void testFindThesisBySupervisorAndStatus() {
        List<DiplomaAssignment> supervisorAssignments = diplomaAssignmentRepository.findThesisBySupervisorAndStatus(1L, ApplicationStatus.APPROVED);
        assertNotNull(supervisorAssignments);
        assertEquals(1, supervisorAssignments.size());
    }
}

