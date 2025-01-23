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
public class UpdateTeachersViewModel {

    @NotNull//@NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String name;

    @NotNull
    private String email;

    @NotNull
    private Position position;

//   @NotNull
//   private List<DiplomaAssignment> supervisedAssignmentIds;

//   @NotNull
//   private List<ThesisDefense> defensesIds;

//   @NotNull
//   private List<Review> review;
}
