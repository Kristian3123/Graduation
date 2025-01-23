package com.inf.Graduation.data.dto.Thesis;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateThesisDto {
    //private long id;
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    @Future(message = "Data can not be in the past")
    private LocalDate uploadDate;





    // private List<Long> studentId;
//    @NotNull
//    private Students studentId;

    //private List<Long> applicationId;
//    @NotNull
//    private DiplomaAssignment applicationId;


    private Review review;

 //   @NotNull
 //   private ThesisDefense defense;
}
