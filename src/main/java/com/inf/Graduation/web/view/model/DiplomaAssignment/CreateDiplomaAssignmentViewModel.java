package com.inf.Graduation.web.view.model.DiplomaAssignment;

import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiplomaAssignmentViewModel {

    @NotNull
    private String title;

    @NotNull
    private String goal;

    @NotNull//@NotEmpty
    private String tasks;

    @NotNull//@NotEmpty
    private String technologies;

    @NotNull
    private ApplicationStatus status;




    @NotNull
    //private Long studentId;
    private Students studentId;


    //private Long supervisorId;
    private Teachers supervisorId;


  //  @NotNull
  //  private Thesis thesis;
}
