package com.inf.Graduation.data.dto;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraduationDTO {
    private Long studentId;
    private String studentName;
    private LocalDate GraduationDate;
}
