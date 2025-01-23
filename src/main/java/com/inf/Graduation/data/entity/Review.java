package com.inf.Graduation.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Reviews")
public class Review extends BaseEntity {
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate uploadDate;

    private String text;

    @Enumerated(EnumType.STRING)
    //  private List<ReviewConclusion> conclusion;
   private ReviewConclusion conclusion;






    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "reviewer_id")
    private Teachers reviewerIds;

   // fetch = FetchType.LAZY
    @OneToOne(mappedBy = "reviewIds", fetch = FetchType.EAGER)
  // @OneToOne
  // @JoinColumn(name = "thesis_id")
    private Thesis thesis;

}
