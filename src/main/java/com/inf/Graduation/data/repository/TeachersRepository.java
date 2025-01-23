package com.inf.Graduation.data.repository;

import com.inf.Graduation.data.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {

    List<Teachers> findAllByName(String name);
    List<Teachers> findByEmail(String email);



}
