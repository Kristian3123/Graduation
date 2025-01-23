package com.inf.Graduation.data.dto.DiplomaAssignment;

import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateDiplomaAssignmentDto {
 @NotNull
   private String title;

 @NotNull
    private String goal;

 @NotNull//@NotEmpty
    private String tasks;

 @NotNull//@NotBlank//@NotEmpty
    private String technologies;

    @NotNull
    private ApplicationStatus status;



   @NotNull
   //private Long studentId;
   private Students studentIds;


    //private Long supervisorId;
    private Teachers  supervisorIds;


  // @NotNull
  // private Thesis thesis;
}
