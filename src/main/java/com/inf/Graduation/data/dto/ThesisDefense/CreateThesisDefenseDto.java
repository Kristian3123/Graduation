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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateThesisDefenseDto {
    //private LocalDate date;
    //private List<Long> committeeIds;
    //private List<Long> studentIds;
    //private List<Long> thesisId;
    //private Double grade;
    @NotNull
    @Future(message = "Data can not be in the past")
    private LocalDate date;

    @NotNull
    @DecimalMin(value = "2.0")
    @DecimalMax(value = "6.0")
    private Double grade;





    @NotNull
    //private List<@Positive Long> committeeIds;
    private List< Teachers> committeeIds;

    @NotNull//@NotEmpty
    //private List<@Positive Long> studentIds;
    private List< Students> studentIds;

//    @NotEmpty
//   // private List<@Positive Long> thesisId;
//    // private List< Thesis> thesisId;
//    private Thesis thesisId;

}
