package com.inf.Graduation.data.dto.Review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReviewDto {
    //private Long id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future(message = "Data can not be in the past")
    private LocalDate uploadDate;
    @NotNull
    private String text;
    @NotNull
    //private List< ReviewConclusion> conclusion;
    private  ReviewConclusion conclusion;



    @NotNull
    private Teachers reviewerIds;
   //@NotNull
   //private Thesis thesisId;
  // @NotNull
  // private Thesis thesis;
}
