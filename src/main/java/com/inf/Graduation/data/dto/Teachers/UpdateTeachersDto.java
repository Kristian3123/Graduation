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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateTeachersDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private Position position;
    //private List<Long> supervisedAssignments;
    //private List<Long> defenses;





 //   @NotNull
 //   private List<DiplomaAssignment> supervisedAssignmentIds;
 //   //private Set<DiplomaAssignment> supervisedAssignments;
//
 //   @NotNull
 //   private List<ThesisDefense> defensesIds;
 //   //private Set<ThesisDefense> defenses;
//
 //   @NotNull
 //   private List<Review> review;
}
