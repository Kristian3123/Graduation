package com.inf.Graduation.data.dto.ThesisDefense;

import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThesisDefenseDto {
    private Long id;

    private LocalDate date;

    private Double grade;



   // private List<Long> committeeIds;
   private List<Teachers> committeeIds;

    //private List<Long> studentIds;
    private List<Students> studentIds;

   // private List<Long> thesisId;
   //private List<Thesis> thesisId;
 //  private  Thesis thesisId;


}
