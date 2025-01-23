package com.inf.Graduation.web.view.model.ThesisDefense;

import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateThesisDefenseViewModel {

    @NotNull
    @FutureOrPresent(message = "Data can not be in the past")
    private LocalDate date;

    @NotNull
    private List< Teachers> committeeIds;

    @NotNull
    private List< Students> studentIds;

 //   @NotNull
 //   //private List< Thesis> thesisId;
 //   private Thesis thesisId;

    @NotNull
    @DecimalMin(value = "2.0")
    @DecimalMax(value = "6.0")
    private Double grade;
}
