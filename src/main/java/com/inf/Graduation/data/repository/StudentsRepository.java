package com.inf.Graduation.data.repository;

import com.inf.Graduation.data.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentsRepository extends JpaRepository<Students,Long>{

    List<Students> findByFacultyNumber(String facultyNumber);
    List<Students> findAllByName(String name);


    List<Students> findStudentsByFacultyNumber(String facultyNumber);

    @Query("SELECT DISTINCT s FROM Students s JOIN s.defenses td WHERE td.grade IS NOT NULL")
    List<Students> findGraduatesByPeriod();
}
