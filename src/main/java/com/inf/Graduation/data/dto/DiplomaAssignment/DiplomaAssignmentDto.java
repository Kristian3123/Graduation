package com.inf.Graduation.data.dto.DiplomaAssignment;

import com.inf.Graduation.data.entity.ApplicationStatus;
import com.inf.Graduation.data.entity.Students;
import com.inf.Graduation.data.entity.Teachers;
import com.inf.Graduation.data.entity.Thesis;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiplomaAssignmentDto {
private long id;
    private String title;
    private String goal;
    private String tasks;
    private String technologies;
    private ApplicationStatus status;



    //private List<Long> studentId;
    //private Long studentId;
    private Students studentIds;
    //private List<Long>  supervisorId;
    //private Long  supervisorId;
    private  Teachers supervisorIds;
    //private String status;

    //private boolean approved;
    private Thesis thesis;
}

