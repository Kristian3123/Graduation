package com.inf.Graduation.data.dto.Teachers;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.ThesisDefense;
import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "Teachers")
public class TeachersDto {
    private Long id;
    private String name;
    private String email;

    //@Enumerated
    //private Position position;
    private Position position;





//    //private List<Long> supervisedAssignmentIds
//    private List<DiplomaAssignment> supervisedAssignmentIds ;
//    // private Set<DiplomaAssignment> supervisedAssignmentIds ;
//   //private List<Long> defenseIds;
//    private List<ThesisDefense> defensesIds;
//    //@OneToMany(mappedBy = "supervisor")
//    //private Set<DiplomaAssignment> assignments;
//
//    private List<Review> review;

}
