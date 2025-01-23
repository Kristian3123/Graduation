package com.inf.Graduation.data.dto.Teachers;


import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateTeachersDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private Position position;







//   @NotNull
//   //private List<Long> supervisedAssignmentIds;
//   private List<DiplomaAssignment> supervisedAssignmentIds;
//   // private Set<DiplomaAssignment> supervisedAssignmentIds;
//
//   @NotNull
//   //private List<Long> defenseIds;
//   private List<ThesisDefense> defensesIds;
//
//   @NotNull
//   private List<Review> review;
}
