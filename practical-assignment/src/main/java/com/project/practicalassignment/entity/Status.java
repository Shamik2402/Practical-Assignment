package com.project.practicalassignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Status")
public class Status {
    @Id
    @Column(name = "Status_Id")
    private int statusId;
    @Column(name = "Name")
    private String statusName;
}
