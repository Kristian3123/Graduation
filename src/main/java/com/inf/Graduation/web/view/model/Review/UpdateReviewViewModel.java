package com.inf.Graduation.web.view.model.Review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inf.Graduation.data.entity.ReviewConclusion;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateReviewViewModel {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future(message = "Data can not be in the past")
    private LocalDate uploadDate;
    @NotNull
    private String text;
    //private List<ReviewConclusion> conclusion;
    @NotNull
    private ReviewConclusion conclusion;
    @NotNull
    private Teachers reviewerIds;
 //   @NotNull
 //   private Thesis thesis;
}
