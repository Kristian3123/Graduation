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
@Table(name = "Students")
public class Students extends BaseEntity {

    private String name;

    private String facultyNumber;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "studentIds")
    // private List<DiplomaAssignment> assignmentsIds;
    @OneToOne(mappedBy = "studentId", fetch = FetchType.EAGER)//
    private DiplomaAssignment assignmentsIds;


    //@OneToMany
    @OneToOne(mappedBy = "studentIdis", fetch = FetchType.EAGER )
    private Thesis thesisIds;


    //@ManyToMany(fetch = FetchType.EAGER, mappedBy = "defendingStudents")
    //private List<ThesisDefense> defenses;

    @ManyToOne(fetch = FetchType.EAGER)//, mappedBy = "defendingStudents"
    @JoinColumn(name = "thesis_defense_id", referencedColumnName = "id")
    private ThesisDefense defenses;
}
