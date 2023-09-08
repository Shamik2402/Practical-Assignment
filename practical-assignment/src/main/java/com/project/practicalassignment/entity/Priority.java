package com.project.practicalassignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Priority")
public class Priority {
    @Id
    @Column(name = "Priority_Id")
    private int priorityId;
    @Column(name = "Severity")
    private String priorityLevel;
}
