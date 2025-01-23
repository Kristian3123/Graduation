package com.inf.Graduation.web.view.model.Student;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentsViewModel {


    @Size(min = 5, max=30)
    @NotNull
    private String name;



    @NotNull
    @Pattern(regexp = "^[A-Z]\\d{6}$", message = "Faculty number must start with an uppercase letter followed by six digits")
    private String facultyNumber;

//   @NotNull
//   private List<DiplomaAssignment> assignmentsIds;

//   @NotNull
//   private List<Thesis> thesisIds;


  // private List<ThesisDefense> defenses;
  private ThesisDefense defenses;
}
