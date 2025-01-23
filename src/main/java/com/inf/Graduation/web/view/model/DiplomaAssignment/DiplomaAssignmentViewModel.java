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
public class DiplomaAssignmentViewModel {
    private long id;
    private String title;
    private String goal;
    private String tasks;
    private String technologies;
    private ApplicationStatus status;

    private Students studentIds;
    private Teachers supervisorIds;


  //  private Thesis thesis;
}
