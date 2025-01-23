package com.inf.Graduation.data.dto.Students;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Thesis;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(name = "Students")
public class StudentsDto {
    private Long id;
    private String name;

    private String facultyNumber;










    //private List<Long> assignmentIds;
    //private List<DiplomaAssignment> assignmentIds;
    private DiplomaAssignment assignmentIds;
 //   //private List<Long> thesisIds;
    private Thesis thesisIds;
 //   //@OneToOne(mappedBy = "student")
 //   //private GraduationThesis graduationThesis;
 //   //private List<ThesisDefense> defenses;
    private ThesisDefense defenses;

}
