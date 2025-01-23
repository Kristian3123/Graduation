package com.inf.Graduation.data.repository;

import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.data.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {


    @Query("SELECT COUNT(t) FROM Thesis t WHERE t.reviewIds.conclusion = :negativeReview")
    int countNegativeReviews(@Param("negativeReview") ReviewConclusion negativeReview);

}
