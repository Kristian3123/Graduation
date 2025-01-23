package com.inf.Graduation.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Thesiss")
public class Thesis extends BaseEntity{

    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate uploadDate;









    //fetch = FetchType.LAZY
    //@ManyToOne(fetch = FetchType.EAGER)//(mappedBy = "thesis") @OneToOne
    @OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "student_id", referencedColumnName = "id")
    private Students studentIdis;

    //fetch = FetchType.LAZY
    @OneToOne(mappedBy = "thesis", fetch = FetchType.EAGER) //@ManyToOne , cascade = CascadeType.ALL
   // @JoinColumn(name = "diploma_assignment_id")
    private DiplomaAssignment applicationIds;

    //fetch = FetchType.LAZY
    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//(mappedBy = "thesis")
    //@JoinColumn(name = "review_id")
    //@OneToMany(mappedBy = "thesis", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL)
    private Review reviewIds;

   // @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   // @JoinColumn(name = "defense_id")
   // private ThesisDefense defense;
   @OneToOne(mappedBy = "theses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private ThesisDefense defense;
}
