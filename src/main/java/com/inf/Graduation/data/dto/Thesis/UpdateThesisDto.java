package com.inf.Graduation.data.dto.Thesis;
import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateThesisDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    @Future(message = "Data can not be in the past")
    private LocalDate uploadDate;




//    @NotNull
//    // private List<Long> studentId;
//    private Students studentId;

//    @NotNull
//    //private List<Long> applicationId;
//    private DiplomaAssignment applicationId;

    //@NotNull
    private Review reviewId;

  //  @NotNull
  //  private ThesisDefense defense;
}
