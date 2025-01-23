package com.inf.Graduation.data.dto.ThesisDefense;

import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateThesisDefenseDto {

    @NotNull
    private Long id;

    @NotNull
    @Future(message = "Data can not be in the past")
    private LocalDate date;

    @NotNull
    @DecimalMin(value = "2.0")
    @DecimalMax(value = "6.0")
    private Double grade;





    //private List<Long> committeeIds;

    @NotNull
    private List<Teachers> committeeIds;

    @NotNull
    //private List<Long> studentIds;
    private List<Students> studentIds;

//   @NotNull
//   //private List<Long> thesisId;
//   //private List<Thesis> thesisId;
//   private  Thesis thesisId;

}
