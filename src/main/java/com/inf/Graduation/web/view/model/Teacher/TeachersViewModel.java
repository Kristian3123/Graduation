package com.inf.Graduation.web.view.model.Teacher;

import com.inf.Graduation.data.entity.DiplomaAssignment;
import com.inf.Graduation.data.entity.Position;
import com.inf.Graduation.data.entity.Review;
import com.inf.Graduation.data.entity.ThesisDefense;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeachersViewModel {
    private Long id;
    private String name;
    private String email;
    private Position position;
//    private  List<DiplomaAssignment> supervisedAssignmentIds ;
//    private List<ThesisDefense> defensesIds;
//    private List<Review> review;
}
