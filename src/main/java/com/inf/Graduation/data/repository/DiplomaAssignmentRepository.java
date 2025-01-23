package com.inf.Graduation.data.repository;

import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.DiplomaAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomaAssignmentRepository extends JpaRepository<DiplomaAssignment,Long> {

  @Query("SELECT d FROM DiplomaAssignment d WHERE d.status = :approved")
  List<DiplomaAssignment> findAllApprovedThesisAssignments(@Param("approved") ApplicationStatus approved);


    @Query("SELECT d FROM DiplomaAssignment d WHERE d.title LIKE %:keyword%")
    List<DiplomaAssignment> findThesisByKeyword(@Param("keyword") String keyword);


    @Query("SELECT d FROM DiplomaAssignment d WHERE d.supervisorId.id = :professorId AND d.status = :approved")
    List<DiplomaAssignment> findThesisBySupervisorAndStatus(@Param("professorId") Long professorId, @Param("approved") ApplicationStatus approved);

}
