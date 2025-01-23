package com.inf.Graduation.web.view.model.Thesis;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThesisViewModel {
    private long id;
    private String title;
    private String content;
    private LocalDate uploadDate;

    // private List<Long> studentId;
 //   private Students studentId;
    //private List<Long> applicationId;
//    private DiplomaAssignment applicationId;
    private Review review;
//    private ThesisDefense defense;
}

