package com.inf.Graduation.web.view.model.Review;


import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewViewModel {
    private long id;

    private LocalDate uploadDate;
    private String text;
    //private List<ReviewConclusion> conclusion;
    private ReviewConclusion conclusion;
    private Teachers reviewerIds;

 //   private Thesis thesis;
}
