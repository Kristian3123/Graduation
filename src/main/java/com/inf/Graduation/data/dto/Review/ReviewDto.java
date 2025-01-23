package com.inf.Graduation.data.dto.Review;

import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private LocalDate uploadDate;
    private String text;
    private ReviewConclusion conclusion;




    //private List< ReviewConclusion> conclusion;

    private Teachers reviewerIds;
    //private Thesis thesisId;
    //private Thesis thesis;
}
