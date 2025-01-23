package com.inf.Graduation.web.view.model.Student;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentsViewModel {

    private Long id;
    private String name;

    private String facultyNumber;
    // private Set<DiplomaAssignment> assignmentsIds;
    //private Set<Thesis> thesisIds;
    //private Set<ThesisDefense> defenses;

//    private List<DiplomaAssignment> assignmentsIds;
//    private List<Thesis> thesisIds;
//    private List<ThesisDefense> defenses;

  //  private DiplomaAssignment assignmentsIds;
  //  private Thesis thesisIds;
    private ThesisDefense defenses;
}
