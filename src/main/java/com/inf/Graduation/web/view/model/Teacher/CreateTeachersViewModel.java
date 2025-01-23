package com.inf.Graduation.web.view.model.Teacher;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.ThesisDefense;
import com.inf.Graduation.web.view.model.DiplomaAssignment.DiplomaAssignmentViewModel;
import com.inf.Graduation.web.view.model.ThesisDefense.ThesisDefenseViewModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeachersViewModel {
    @NotNull//@NotBlank
    @Size(min = 5, max=30)
    private String name;
    @NotNull//@NotBlank
    private String email;
    @NotNull// @NotBlank
    private Position position;

//   @NotNull// @NotBlank
//   private List<DiplomaAssignment> supervisedAssignmentIds;
//   //private List<DiplomaAssignmentViewModel> supervisedAssignmentIds;
//   @NotNull// @NotBlank
//   private List<ThesisDefense> defensesIds;
//   //private List<ThesisDefenseViewModel> defensesIds;

//   @NotNull
//   private List<Review> review;
}
