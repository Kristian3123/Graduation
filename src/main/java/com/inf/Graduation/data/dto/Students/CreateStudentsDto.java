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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateStudentsDto {
   // @NotNull
   // private Long id;

    @NotNull//@NotBlank
    private String name;

    @NotNull
    //@Pattern(regexp = "\\d{6}")
    private String facultyNumber;




//    @NotNull
//   // private List<Long> assignmentIds;
//    private List<DiplomaAssignment> assignmentIds;
private DiplomaAssignment assignmentIds;
//    @NotNull
//    //private List<Long> thesisIds;
//    private List<Thesis> thesisIds;
private Thesis thesisIds;
//    @ManyToMany
//    private List<ThesisDefense> defenses;
private ThesisDefense defenses;

}
