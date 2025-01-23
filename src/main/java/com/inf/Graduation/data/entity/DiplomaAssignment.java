package com.inf.Graduation.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "DiplomaAssignments")
public class DiplomaAssignment extends BaseEntity {

    private String title;
    private String goal;
    private String tasks;
    private String technologies;






   // @ManyToOne(fetch = FetchType.EAGER)
    @OneToOne(fetch = FetchType.EAGER)//mappedBy = "assignmentsIds",
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Students studentId;

    @ManyToOne( fetch = FetchType.EAGER)//mappedBy = "supervisedAssignmentIds",
    // @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private Teachers supervisorId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    //fetch = FetchType.LAZY
    @OneToOne( cascade = CascadeType.ALL) //mappedBy = "applicationIds",  //fetch = FetchType.EAGER,
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    private Thesis thesis;
}
