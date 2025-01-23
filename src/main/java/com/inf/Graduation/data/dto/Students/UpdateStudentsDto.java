package com.inf.Graduation.data.dto.Students;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateStudentsDto {
    @NotNull
    private Long id;

    private String name;

    //@Pattern(regexp = "\\d{6}")
    private String facultyNumber;





    //private List<Long> assignmentIds;
 //   private List<DiplomaAssignment> assignmentIds;
 //   //private List<Long> thesis;
 //   private List<Thesis> thesisIds;
//
 //   private List<ThesisDefense> defenses;
    private ThesisDefense defenses;
}
