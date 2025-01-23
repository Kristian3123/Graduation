package com.inf.Graduation.repository;

import com.inf.Graduation.GraduationApplication;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.repository.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

public class StudentsRepositoryTest {

    @Autowired
    private StudentsRepository studentsRepository;

    @BeforeEach
    public void setUp() {
        Students student1 = Students.builder().name("John Doe").facultyNumber("12345").build();
        Students student2 = Students.builder().name("Jane Doe").facultyNumber("67890").build();
        studentsRepository.save(student1);
        studentsRepository.save(student2);
    }

    @Test
    public void testFindByFacultyNumber() {
        List<Students> students = studentsRepository.findByFacultyNumber("12345");
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("John Doe");
    }

    @Test
    public void testFindAllByName() {
        List<Students> students = studentsRepository.findAllByName("Jane Doe");
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getFacultyNumber()).isEqualTo("67890");
    }

    @Test
    public void testFindStudentsByFacultyNumber() {
        List<Students> students = studentsRepository.findStudentsByFacultyNumber("12345");
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("John Doe");
    }

    @Test
    public void testFindGraduatesByPeriod() {
        List<Students> graduates = studentsRepository.findGraduatesByPeriod();

        assertThat(graduates).isNotNull();
    }
}

