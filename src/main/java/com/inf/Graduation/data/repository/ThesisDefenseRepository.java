package com.inf.Graduation.data.repository;

import com.inf.Graduation.data.entity.ThesisDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ThesisDefenseRepository extends JpaRepository<ThesisDefense,Long> {



    @Query("SELECT t FROM ThesisDefense t WHERE t.grade BETWEEN :minGrade AND :maxGrade")
    List<ThesisDefense> findDefendedThesesByGrade(@Param("minGrade") double minGrade, @Param("maxGrade") double maxGrade);


    @Query("SELECT AVG(size(t.defendingStudents)) FROM ThesisDefense t WHERE t.date BETWEEN :startDate AND :endDate")
    double findAverageDefensesByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("SELECT COUNT(t) FROM ThesisDefense t JOIN t.committee c WHERE c.id = :professorId AND t.grade >= 3.0")
    int countSuccessfulDefensesBySupervisor(@Param("professorId") Long professorId);
}


