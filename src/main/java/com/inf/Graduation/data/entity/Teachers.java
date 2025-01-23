package com.inf.Graduation.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Teachers")
public class Teachers extends BaseEntity{

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Position position;




    //,cascade = CascadeType.ALL
    //@OneToMany(mappedBy = "supervisorIds", fetch = FetchType.EAGER)//, orphanRemoval = true
    @OneToMany(mappedBy = "supervisorId", fetch = FetchType.EAGER)
    private List<DiplomaAssignment> supervisedAssignmentIds;
    //  @OneToMany//(mappedBy = "supervisor")
    //private Set<DiplomaAssignment> supervisedAssignments;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "committee")
    private List<ThesisDefense> defensesIds;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "reviewerIds")
    private List<Review> review;


}
