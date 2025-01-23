package com.inf.Graduation.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Department")
public class Department extends BaseEntity {
    private String name;
    // mappedBy = "department",

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Students> students;
}
