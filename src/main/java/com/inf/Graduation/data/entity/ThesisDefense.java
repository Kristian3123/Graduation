package com.inf.Graduation.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ThesisDefenses")
public class ThesisDefense extends BaseEntity{

    //private Date date;
    private LocalDate date;

    private Double grade;






    @ManyToMany(fetch = FetchType.EAGER)//(mappedBy = "defenses") //@OneToMany
    @JoinTable(
            name = "thesis_defense_committee",
            joinColumns = @JoinColumn(name = "thesis_defense_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teachers> committee;

    // @ManyToMany(fetch = FetchType.EAGER) //(mappedBy = "defenses")
    @OneToMany(mappedBy = "defenses", cascade = CascadeType.ALL, orphanRemoval = true)//
    //@JoinColumn(name = "thesis_defense_id")
    private List<Students> defendingStudents;

    //@OneToMany
    // private List<Thesis> theses;

    //@OneToOne(mappedBy = "defense",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // private Thesis theses;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "thesis_id")
    private Thesis theses;

}
