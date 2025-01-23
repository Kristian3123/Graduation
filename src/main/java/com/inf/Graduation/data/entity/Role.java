package com.inf.Graduation.data.entity;//package com.inf.Graduation.data.entity;//package com.inf.Graduation.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.Set;

public enum Role {
    ADMIN,
    TEACHERS,
    STUDENTS
}
